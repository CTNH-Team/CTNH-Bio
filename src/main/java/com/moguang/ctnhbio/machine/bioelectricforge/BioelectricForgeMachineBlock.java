package com.moguang.ctnhbio.machine.bioelectricforge;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BioelectricForgeMachineBlock extends LivingMetaMachineBlock {
    public BioelectricForgeMachineBlock(Properties properties, MachineDefinition definition) {
        super(properties, definition);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.box(0, 0, 0, 1, 1.5, 1);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
