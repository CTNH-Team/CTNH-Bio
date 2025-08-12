package com.moguang.ctnhbio.api.block;

import com.google.common.collect.ImmutableMap;
import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.moguang.ctnhbio.api.ILivingEntityHostBlock;
import com.moguang.ctnhbio.api.ILivingMachine;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class LivingMultiMetaMachineBlock extends MetaMachineBlock implements ILivingEntityHostBlock<LivingMetaMachineEntity> {
    public LivingMultiMetaMachineBlock(Properties properties, MachineDefinition definition) {
        super(properties, definition);
    }

    //private LivingMetaMachineEntity machineEntity = null;
    @Override
    public LivingMetaMachineEntity getHostedEntity(Level level, BlockPos pos, BlockState state) {

        if(!level.isClientSide() &&
                getMachine(level, pos) instanceof ILivingMachine machine)
        {
            return machine.getMachineEntity();
        }
        return null;
    }


    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }


    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        super.attack(state, level, pos, player);
        onBlockAttacked(state, level, pos, player);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        InteractionResult result = onBlockUsed(state, level, pos, player);
        if(result.consumesAction())
        {
            return result;
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

    }


    @Override
    public String getDescriptionId() {
//        if (name != null) {
//            return name.getString();
//        }
        return super.getDescriptionId();
    }

}
