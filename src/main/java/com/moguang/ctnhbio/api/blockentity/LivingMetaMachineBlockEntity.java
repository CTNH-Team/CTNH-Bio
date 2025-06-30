package com.moguang.ctnhbio.api.blockentity;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.moguang.ctnhbio.api.ILivingEntityHost;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.registry.CBEntities;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.UUID;

@Getter
public class LivingMetaMachineBlockEntity extends MetaMachineBlockEntity implements ILivingEntityHost<LivingMetaMachineEntity> {

    @Persisted
    private LivingMetaMachineEntity livingMachine;
    private CompoundTag entityTag;
    private boolean spawned;

    protected LivingMetaMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static MetaMachineBlockEntity createLivingBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        return new LivingMetaMachineBlockEntity(type, pos, blockState);
    }

    @Override
    public void notifyBlockUpdate() {
        super.notifyBlockUpdate();

    }

    @Override
    public LivingMetaMachineEntity getHostedEntity() {
        return livingMachine;
    }

    @Override
    public void setHostedEntity(LivingMetaMachineEntity entity) {
        livingMachine = entity;
    }

    @Override
    public BlockPos getHostPos() {
        return getBlockPos();
    }

    @Override
    public void onHostedEntityRemoved(LivingMetaMachineEntity entity) {
        level.removeBlock(getBlockPos(), false);
    }

    @Override
    public LivingMetaMachineEntity createHostedEntity(Level level) {
        LivingMetaMachineEntity entity = LivingMetaMachineEntity.createLivingMetaMachineEntity(CBEntities.LIVING_META_MACHINE_ENTITY.get(), level);
        entity.setPos(getHostPos().getX() + 0.5, getHostPos().getY(), getHostPos().getZ() + 0.5);
        return entity;
    }

    @Override
    public Class<LivingMetaMachineEntity> getEntityClass() {
        return LivingMetaMachineEntity.class;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        saveHostedEntityData(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("HostedEntity")) {
            this.entityTag = tag.getCompound("HostedEntity");
        }
        //loadHostedEntityData(tag, this.getLevel());
    }

    // 生命周期挂钩
    @Override
    public void onLoad() {
        super.onLoad();
        if(getLevel().isClientSide()) return;
        if (livingMachine == null) {
            loadHostedEntityData(entityTag, level);
            spawnHostedEntity(this.getLevel());
        }
        if(!spawned)
        {
            level.addFreshEntity(livingMachine);
            spawned = true;
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        despawnHostedEntity();
    }
}
