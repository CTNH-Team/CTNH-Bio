package com.moguang.ctnhbio.api.blockentity;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.registry.CBEntities;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.UUID;

@Getter
public class LivingMetaMachineBlockEntity extends MetaMachineBlockEntity {

    private UUID livingEntityUUID;
    private LivingMetaMachineEntity cachedEntity;
    private CompoundTag cachedEntityData;

    protected LivingMetaMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static MetaMachineBlockEntity createLivingBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        return new LivingMetaMachineBlockEntity(type, pos, blockState);
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (livingEntityUUID == null && !level.isClientSide) {
            spawnAndBindEntity();
        }

    }

    @Override
    public void notifyBlockUpdate() {
        super.notifyBlockUpdate();
        if (cachedEntity == null) {
            cachedEntity = findEntityByUUID() ;
        }
    }

    private LivingMetaMachineEntity findEntityByUUID() {
//        return level.getEntitiesOfClass(LivingMetaMachineEntity.class, new AABB(worldPosition).inflate(16))
//                .stream()
//                .filter(e -> e.getUUID().equals(livingEntityUUID))
//                .findFirst()
//                .orElse(null);
        AABB searchBox = new AABB(worldPosition).inflate(16);
        //System.out.println("Searching AABB: " + searchBox);

        List<LivingMetaMachineEntity> candidates = level.getEntitiesOfClass(LivingMetaMachineEntity.class, searchBox);
        //System.out.println("Found " + candidates.size() + " candidate entities.");

        for (LivingMetaMachineEntity e : candidates) {
            //System.out.println("Checking entity at " + e.blockPosition() + " with UUID " + e.getUUID());
            if (e.getUUID().equals(livingEntityUUID)) {
                //System.out.println("Matched entity!");
                return e;
            }
        }

        //System.out.println("No matching entity found.");
        return null;
    }

    private void spawnAndBindEntity() {
        if (level == null || level.isClientSide) return;
        LivingMetaMachineEntity entity = LivingMetaMachineEntity.createLivingMetaMachineEntity(CBEntities.LIVING_META_MACHINE_ENTITY.get(), level);
        entity.bound(getBlockPos(), (BasicLivingMachine) getMetaMachine());
        this.cachedEntity = entity;
        this.livingEntityUUID = entity.getUUID();
        if (cachedEntityData != null) {
            cachedEntity.load(cachedEntityData);
        }


        level.addFreshEntity(entity);
        setChanged();

    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (livingEntityUUID != null)
            tag.putUUID("BoundEntityUUID", livingEntityUUID);
        cachedEntityData = new CompoundTag();
        if (cachedEntity != null) {
            cachedEntity.saveWithoutId(cachedEntityData);
            tag.put("LivingEntityData", cachedEntityData);
        }

    }

//    @Override
//    public void saveCustomPersistedData(CompoundTag tag, boolean forDrop) {
//        super.saveCustomPersistedData(tag, forDrop);
//    }

    @Override
    public void load(CompoundTag tag){
        super.load(tag);
        if (tag.hasUUID("BoundEntityUUID"))
            livingEntityUUID = tag.getUUID("BoundEntityUUID");
        if (tag.contains("LivingEntityData")) {
            cachedEntityData = tag.getCompound("LivingEntityData");
        }
    }
}
