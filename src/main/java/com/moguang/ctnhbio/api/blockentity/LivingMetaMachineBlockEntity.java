package com.moguang.ctnhbio.api.blockentity;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.common.data.GTDamageTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.machine.braininavat.BrainInAVatMachine;
import lombok.Getter;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class LivingMetaMachineBlockEntity extends MetaMachineBlockEntity implements GeoBlockEntity {

    private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle");
    private static final RawAnimation WORKING_ANIMATION = RawAnimation.begin().thenLoop("working");
    private static final RawAnimation HURT_ANIMATION = RawAnimation.begin().thenPlay("hurt");
    private static final int HURT_ANIMATION_EVENT = 1;
    private static final int HURT_ANIMATION_TICKS = 10;

    private static final String HOSTED_ENTITY_UUID_TAG = "HostedEntityUuid";
    private static final int ENTITY_RESOLVE_INTERVAL = 20;
    private static final int ENTITY_RESPAWN_GRACE_TICKS = 100;
    private static final double ENTITY_SEARCH_RANGE = 2.0D;

    private final EntityType<? extends LivingMetaMachineEntity> entityType;

    private LivingMetaMachineEntity machineEntity;
    private UUID hostedEntityUuid;
    private long nextEntityResolveTick;
    private long missingEntitySinceTick = -1;
    private long hurtAnimationUntilTick = -1;
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
        if (level instanceof ServerLevel) {
            return resolveHostedEntity(false);
        }
        return machineEntity;
    }

    public void setHostedEntity(LivingMetaMachineEntity entity) {
        machineEntity = entity;
        hostedEntityUuid = entity == null ? null : entity.getUUID();
    }

    public BlockPos getHostPos() {
        return getBlockPos();
    }

    public MetaMachine getHostMachine() {
        return this.metaMachine;
    }

    public void onHostedEntityRemoved(LivingMetaMachineEntity entity, DamageSource source) {
        if (machineEntity == entity) {
            machineEntity = null;
            hostedEntityUuid = null;
        }
        level.getServer().submit(() -> level.destroyBlock(getBlockPos(), !source.is(GTDamageTypes.ELECTRIC.key)));
    }

    public void onHostedEntityHurt() {
        if (level == null) {
            return;
        }
        hurtAnimationUntilTick = level.getGameTime() + HURT_ANIMATION_TICKS;
        if (!level.isClientSide) {
            level.blockEvent(getBlockPos(), getBlockState().getBlock(), HURT_ANIMATION_EVENT, HURT_ANIMATION_TICKS);
        }
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
        applyMachineEntityState(entity);
        return entity;
    }

    private boolean isTrackedEntityValid(LivingMetaMachineEntity entity) {
        return entity != null && entity.isAlive() && !entity.isRemoved() &&
                entity.getType() == entityType &&
                (entity.hasStoredHostPos(getHostPos()) || entity.getHost() == this);
    }

    private void applyMachineEntityState(LivingMetaMachineEntity entity) {
        if (metaMachine instanceof BrainInAVatMachine vat && vat.getStoredMaxHealth() != 0) {
            entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(vat.getStoredMaxHealth());
            entity.setHealth(Math.min(entity.getHealth(), vat.getStoredMaxHealth()));
            if (entity.getHealth() <= 0) {
                entity.setHealth(vat.getStoredMaxHealth());
            }
        }
    }

    public void bindHostedEntity(LivingMetaMachineEntity entity) {
        setHostedEntity(entity);
        entity.bindToHost(this);
        entity.setPos(getHostPos(), entityOffset);
        applyMachineEntityState(entity);
        missingEntitySinceTick = -1;
    }

    public void removeHostedEntityImmediately() {
        LivingMetaMachineEntity entity = machineEntity;
        machineEntity = null;
        hostedEntityUuid = null;
        missingEntitySinceTick = -1;
        if (entity != null) {
            entity.setHost(null);
            entity.discard();
        }
    }

    private LivingMetaMachineEntity findHostedEntity(ServerLevel serverLevel) {
        if (hostedEntityUuid != null) {
            var entity = serverLevel.getEntity(hostedEntityUuid);
            if (entity instanceof LivingMetaMachineEntity livingEntity && isTrackedEntityValid(livingEntity)) {
                return livingEntity;
            }
        }

        List<LivingMetaMachineEntity> hostCandidates = new ArrayList<>();
        List<LivingMetaMachineEntity> orphanCandidates = new ArrayList<>();
        for (LivingMetaMachineEntity entity : serverLevel.getEntitiesOfClass(LivingMetaMachineEntity.class,
                new AABB(getHostPos()).inflate(ENTITY_SEARCH_RANGE),
                candidate -> candidate.getType() == entityType && candidate.isAlive() && !candidate.isRemoved())) {
            if (entity.hasStoredHostPos(getHostPos())) {
                hostCandidates.add(entity);
            } else if (hostedEntityUuid == null && entity.getStoredHostPos() == null) {
                orphanCandidates.add(entity);
            }
        }
        if (!hostCandidates.isEmpty()) {
            return hostCandidates.get(0);
        }
        if (orphanCandidates.size() == 1) {
            return orphanCandidates.get(0);
        }
        return null;
    }

    private void cleanupDuplicateEntities(ServerLevel serverLevel, LivingMetaMachineEntity primaryEntity) {
        for (LivingMetaMachineEntity entity : serverLevel.getEntitiesOfClass(LivingMetaMachineEntity.class,
                new AABB(getHostPos()).inflate(ENTITY_SEARCH_RANGE),
                candidate -> candidate != primaryEntity && candidate.getType() == entityType && candidate.isAlive() &&
                        !candidate.isRemoved() && candidate.hasStoredHostPos(getHostPos()))) {
            entity.discard();
        }
    }

    private LivingMetaMachineEntity resolveHostedEntity(boolean allowCreate) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return machineEntity;
        }
        if (isTrackedEntityValid(machineEntity)) {
            hostedEntityUuid = machineEntity.getUUID();
            return machineEntity;
        }
        machineEntity = null;

        long gameTime = serverLevel.getGameTime();
        if (gameTime < nextEntityResolveTick) {
            return null;
        }
        nextEntityResolveTick = gameTime + ENTITY_RESOLVE_INTERVAL;

        LivingMetaMachineEntity resolvedEntity = findHostedEntity(serverLevel);
        if (resolvedEntity != null) {
            bindHostedEntity(resolvedEntity);
            cleanupDuplicateEntities(serverLevel, resolvedEntity);
            return resolvedEntity;
        }

        if (missingEntitySinceTick < 0) {
            missingEntitySinceTick = gameTime;
        }
        if (allowCreate && gameTime - missingEntitySinceTick >= ENTITY_RESPAWN_GRACE_TICKS) {
            LivingMetaMachineEntity createdEntity = createHostedEntity(serverLevel);
            if (createdEntity != null) {
                bindHostedEntity(createdEntity);
                serverLevel.addFreshEntity(createdEntity);
                return createdEntity;
            }
        }
        return null;
    }

    public void refreshHostedEntityBinding(boolean allowCreate) {
        if (level instanceof ServerLevel) {
            resolveHostedEntity(allowCreate);
        }
    }

    public void createHostedEntityImmediately() {
        if (level instanceof ServerLevel serverLevel) {
            missingEntitySinceTick = serverLevel.getGameTime() - ENTITY_RESPAWN_GRACE_TICKS;
            nextEntityResolveTick = 0;
            resolveHostedEntity(true);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.hasUUID(HOSTED_ENTITY_UUID_TAG)) {
            hostedEntityUuid = tag.getUUID(HOSTED_ENTITY_UUID_TAG);
        } else {
            hostedEntityUuid = null;
        }
        machineEntity = null;
        nextEntityResolveTick = 0;
        missingEntitySinceTick = -1;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        if (machineEntity != null) {
            hostedEntityUuid = machineEntity.getUUID();
        }
        if (hostedEntityUuid != null) {
            tag.putUUID(HOSTED_ENTITY_UUID_TAG, hostedEntityUuid);
        }
        if (metaMachine instanceof BrainInAVatMachine vat && machineEntity != null) {
            vat.captureMaxHealthFromEntity();
        }
        onChanged();
        super.saveAdditional(tag);
    }

    // 生命周期挂钩
    @Override
    public void onLoad() {
        super.onLoad();
        if (getLevel().isClientSide()) return;
        machineEntity = null;
        nextEntityResolveTick = 0;
        missingEntitySinceTick = level.getGameTime();
    }

    @Override
    public void setRemoved() {
        machineEntity = null;
        super.setRemoved();
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == HURT_ANIMATION_EVENT && level != null) {
            hurtAnimationUntilTick = level.getGameTime() + type;
            return true;
        }
        return super.triggerEvent(id, type);
    }

    private String getAnimationKey() {
        String path = getDefinition().getId().getPath();
        if (path.contains("bioelectric_forge")) return "bioelectric_forge";
        if (path.contains("decomposer")) return "decomposer";
        if (path.contains("digester")) return "digester";
        if (path.contains("bioreactor")) return "bioreactor";
        return null;
    }

    private PlayState updateAnimation(AnimationState<LivingMetaMachineBlockEntity> state) {
        if (getAnimationKey() == null) {
            return PlayState.STOP;
        }
        if (level != null && level.getGameTime() < hurtAnimationUntilTick) {
            state.setAnimation(HURT_ANIMATION);
            return PlayState.CONTINUE;
        }
        boolean isWorking = metaMachine instanceof IRecipeLogicMachine recipeLogicMachine &&
                recipeLogicMachine.isActive();

        state.setAnimation(isWorking ? WORKING_ANIMATION : IDLE_ANIMATION);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "main", 0, this::updateAnimation));
    }

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
