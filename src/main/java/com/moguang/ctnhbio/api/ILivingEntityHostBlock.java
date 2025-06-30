package com.moguang.ctnhbio.api;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

public interface ILivingEntityHostBlock<T extends LivingEntity> {
    T getHostedEntity(Level level, BlockPos pos, BlockState state);

    // 当玩家左击方块时触发
    default boolean onBlockAttacked(BlockState state, Level level, BlockPos pos, Player player) {
        T entity = getHostedEntity(level, pos, state);
        if (entity != null &&
                !level.isClientSide() &&
                !player.getItemInHand(InteractionHand.MAIN_HAND).isCorrectToolForDrops(state)) {
            player.attack(entity); // 玩家攻击实体
            return true;
        }
        return false;
    }

    // 当玩家右击方块时触发
    default InteractionResult onBlockUsed(BlockState state, Level level, BlockPos pos, Player player) {
        T entity = getHostedEntity(level, pos, state);
        if (entity != null) {
            return player.interactOn(entity, player.getUsedItemHand()); // 玩家与实体交互
        }
        return InteractionResult.PASS;
    }
}
