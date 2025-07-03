package com.moguang.ctnhbio.machine.braininavat;

import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BrainInAVatBlockEntity extends LivingMetaMachineBlockEntity<Brain> {
    protected BrainInAVatBlockEntity(BlockEntityType type, BlockPos pos, BlockState blockState, EntityType entityType) {
        super(type, pos, blockState, entityType);
    }


}
