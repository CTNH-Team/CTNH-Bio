package com.moguang.ctnhbio.api.block;

import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.moguang.ctnhbio.api.ILivingEntityHostBlock;
import com.moguang.ctnhbio.api.ILivingMachine;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class

LivingMetaMachineBlock extends MetaMachineBlock implements ILivingEntityHostBlock<LivingMetaMachineEntity> {

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

    public LivingMetaMachineBlock(Properties properties, MachineDefinition definition) {
        super(properties, definition);
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

//    @Override
//    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState pNewState, boolean pIsMoving) {
//
//
//        if(!level.isClientSide() &&
//                getMachine(level, pos) instanceof BasicLivingMachine machine)
//        {
//            LivingEntity entity =  machine.getMachineEntity();
//            if (entity != null && entity.isAlive()) {
//                entity.discard();
//            }
//        }
//        super.onRemove(state, level, pos, pNewState, pIsMoving);
//    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_49928_, BlockGetter p_49929_, BlockPos p_49930_) {
        return true;
    }
    @Override
    public VoxelShape getVisualShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return Shapes.empty();
    }

    @Override
    public float getShadeBrightness(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return 1.0f;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return 0;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.empty();
    }

    @Override
    public String getDescriptionId() {
//        if (name != null) {
//            return name.getString();
//        }
        return super.getDescriptionId();
    }


}
