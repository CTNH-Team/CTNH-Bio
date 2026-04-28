package com.moguang.ctnhbio.api.blockentity;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.common.data.GTDamageTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import com.moguang.ctnhbio.api.IHostAwareEntity;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.machine.braininavat.BrainInAVatMachine;
import lombok.Getter;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

@Getter
public class LivingMetaMachineBlockEntity extends MetaMachineBlockEntity implements GeoBlockEntity {

    private final EntityType<? extends LivingMetaMachineEntity> entityType;

    private LivingMetaMachineEntity machineEntity;

    @Getter
    private CompoundTag entityTag;
    private boolean spawned;
    public Vec3 entityOffset = new Vec3(0.5, 0, 0.5);

    public LivingMetaMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState,
                                        EntityType<? extends LivingMetaMachineEntity> entityType) {
        super(type, pos, blockState);
        this.entityType = entityType;
    }

    public static LivingMetaMachineBlockEntity create(BlockEntityType<?> type,
                                                      BlockPos pos,
                                                      BlockState state,
                                                      EntityType<? extends LivingMetaMachineEntity> entityType) {
        return new LivingMetaMachineBlockEntity(type, pos, state, entityType);
    }

    public LivingMetaMachineBlockEntity setEntityOffset(double x, double y, double z) {
        entityOffset = new Vec3(x, y, z);
        return this;
    }

    @Override
    public void notifyBlockUpdate() {
        super.notifyBlockUpdate();
    }

    public LivingMetaMachineEntity getHostedEntity() {
        return machineEntity;
    }

    public void setHostedEntity(LivingMetaMachineEntity entity) {
        machineEntity = entity;
    }

    public BlockPos getHostPos() {
        return getBlockPos();
    }

    public MetaMachine getHostMachine() {
        return this.metaMachine;
    }

    public void onHostedEntityRemoved(LivingMetaMachineEntity entity, DamageSource source) {
        level.getServer().submit(() -> level.destroyBlock(getBlockPos(), !source.is(GTDamageTypes.ELECTRIC.key)));
    }

    public LivingMetaMachineEntity createHostedEntity(Level level) {
        LivingMetaMachineEntity entity = entityType.create(level);
        if (entity == null) {
            return null;
        }
        entity.setPos(getHostPos(), entityOffset);
        if (getMetaMachine() instanceof SimpleTieredMachine tieredMachine) {
            var tier = tieredMachine.getTier();
            entity.initAttributes(tier * 20, tier * 4);
        } else if (getMetaMachine() instanceof WorkableMultiblockMachine) {
            entity.initAttributes(1000, 50);
        }
        return entity;
    }

    private boolean isEntityHostAware(LivingMetaMachineEntity entity) {
        return entity instanceof IHostAwareEntity;
    }

    public void spawnHostedEntity(Level level) {
        if (getHostedEntity() != null) {
            return;
        }
        LivingMetaMachineEntity entity = createHostedEntity(level);
        if (entity == null) {
            return;
        }
        setHostedEntity(entity);
        if (isEntityHostAware(entity)) {
            ((IHostAwareEntity) entity).bindToHost(this);
        }
    }

    public void despawnHostedEntity() {
        if (getHostedEntity() != null) {
            getHostedEntity().discard();
            setHostedEntity(null);
        }
    }

    public void saveHostedEntityData(CompoundTag nbt) {
        if (getHostedEntity() != null) {
            CompoundTag hostedEntityTag = new CompoundTag();
            getHostedEntity().save(hostedEntityTag);
            nbt.put("HostedEntity", hostedEntityTag);
        }
    }

    public void loadHostedEntityData(CompoundTag hostedEntityTag, Level level) {
        if (hostedEntityTag == null) {
            return;
        }
        Entity entity = EntityType.loadEntityRecursive(hostedEntityTag, level, loaded -> loaded);
        if (entity instanceof LivingMetaMachineEntity livingEntity) {
            setHostedEntity(livingEntity);
            if (isEntityHostAware(livingEntity)) {
                ((IHostAwareEntity) livingEntity).bindToHost(this);
            }
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        if (getPersistentData().contains("HostedEntity"))
            entityTag = getPersistentData().getCompound("HostedEntity");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        saveHostedEntityData(getPersistentData());
        if (metaMachine instanceof BrainInAVatMachine vat && machineEntity != null) {
            vat.maxHealth = machineEntity.getMaxHealth();
        }
        onChanged();
        super.saveAdditional(tag);
    }

    // 生命周期挂钩
    @Override
    public void onLoad() {
        super.onLoad();
        if (getLevel().isClientSide()) return;
        if (machineEntity == null) {
            loadHostedEntityData(entityTag, level);
            if (machineEntity == null) {
                spawnHostedEntity(this.getLevel());
            }
            if (metaMachine instanceof BrainInAVatMachine vat && vat.maxHealth != 0 && machineEntity != null) {
                machineEntity.setHealth(vat.maxHealth);
                machineEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(vat.maxHealth);
            }
        }
        if (!spawned && machineEntity != null) {
            level.addFreshEntity(machineEntity);
            spawned = true;
        }
    }

    @Override
    public void setRemoved() {
        saveHostedEntityData(getPersistentData());
        despawnHostedEntity();
        onChanged();
        super.setRemoved();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
