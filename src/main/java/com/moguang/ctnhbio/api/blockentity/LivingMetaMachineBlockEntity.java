package com.moguang.ctnhbio.api.blockentity;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
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
    @Persisted
    private T machineEntity;
    @Persisted
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
        level.removeBlock(getBlockPos(), false);
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
        return entity;
    }

    @Override
    public Class<T> getEntityClass() {
        return (Class<T>) LivingMetaMachineEntity.class;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        saveHostedEntityData(tag);
    }

//    @Override
//    public void load(CompoundTag tag) {
//        super.load(tag);
//        if (tag.contains("HostedEntity")) {
//            this.entityTag = tag.getCompound("HostedEntity");
//        }
//        //loadHostedEntityData(tag, this.getLevel());
//    }

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
        super.setRemoved();
        despawnHostedEntity();
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
