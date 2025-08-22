package com.moguang.ctnhbio.api.blockentity;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.moguang.ctnhbio.api.ILivingEntityHost;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import static com.lowdragmc.lowdraglib.LDLib.isRemote;

@Getter
public class LivingMetaMachineBlockEntity<T extends LivingMetaMachineEntity> extends MetaMachineBlockEntity implements ILivingEntityHost<T>, GeoBlockEntity {

    private final EntityType<T> entityType;

    private T machineEntity;

    @Getter
    private CompoundTag entityTag;
    private boolean spawned;
    public Vec3 entityOffset = new Vec3(0.5, 0, 0.5);



    public LivingMetaMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, EntityType<T> entityType) {
        super(type, pos, blockState);
        this.entityType = entityType;
    }


    public static <T extends LivingMetaMachineEntity> LivingMetaMachineBlockEntity<T> create(BlockEntityType<?> type, BlockPos pos, BlockState state, EntityType<T> entityType) {
        return new LivingMetaMachineBlockEntity<>(type, pos, state, entityType);
    }

    public LivingMetaMachineBlockEntity setEntityOffset(double x, double y, double z){
        entityOffset = new Vec3(x, y, z);
        return this;
    }

    @Override
    public void notifyBlockUpdate() {
        super.notifyBlockUpdate();

    }

    @Override
    public T getHostedEntity() {
        return machineEntity;
    }

    @Override
    public void setHostedEntity(T entity) {
        machineEntity = entity;
    }

    @Override
    public BlockPos getHostPos() {
        return getBlockPos();
    }

    @Override
    public MetaMachine getHostMachine() {
        return this.metaMachine;
    }

    @Override
    public void onHostedEntityRemoved(LivingMetaMachineEntity entity) {
        level.getServer().submit(() ->
                level.destroyBlock(getBlockPos(), true)
        );
    }

    @Override
    public T createHostedEntity(Level level) {
        T entity = entityType.create(level);
        entity.setPos(getHostPos(), entityOffset);
        if(getMetaMachine() instanceof SimpleTieredMachine tieredMachine)
        {
            var tier = tieredMachine.getTier();
            entity.initAttributes(tier*20, tier*4);
        }
        else if(getMetaMachine() instanceof WorkableMultiblockMachine)
        {
            entity.initAttributes(1000, 50);
        }
        return entity;
    }

    @Override
    public Class<T> getEntityClass() {
        return (Class<T>) LivingMetaMachineEntity.class;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        if(getPersistentData().contains("HostedEntity"))
            entityTag = getPersistentData().getCompound("HostedEntity");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        saveHostedEntityData(getPersistentData());
        onChanged();
        super.saveAdditional(tag);

    }


    // 生命周期挂钩
    @Override
    public void onLoad() {
        super.onLoad();
        if(getLevel().isClientSide()) return;
        if (machineEntity == null) {
            loadHostedEntityData(entityTag, level);
            spawnHostedEntity(this.getLevel());
        }
        if(!spawned)
        {
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
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
