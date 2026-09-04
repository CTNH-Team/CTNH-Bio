package com.moguang.ctnhbio.machine.greatflesh;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.registry.GTRegistries;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;

import java.util.ArrayList;
import java.util.List;

public class GreatFleshMachine extends WorkableLivingMultiblockMachine {

    public GreatFleshMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public void afterWorking() {
        super.afterWorking();
        if (!isRemote() && isStructureOperational()) {
            tryDifferentiate();
        }
    }

    public void tryDifferentiate() {
        String name = getRecipeLogic().getLastRecipe().id.getPath();
        String target = name.substring(name.indexOf("/") + 1);
        var definition = GTRegistries.MACHINES.get(CTNHBio.id(target));
        Level level = getLevel();
        var facing = getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        if (definition != null && level != null) {
            // 1. 先收集所有需要销毁的机器位置
            List<BlockPos> positionsToDestroy = new ArrayList<>();
            for (var part : getParts()) {
                if (part instanceof MetaMachine machine) {
                    positionsToDestroy.add(machine.getPos());
                }
            }

            // 2. 下一tick销毁所有机器
            level.getServer().submit(() -> {
                if (!isStructureOperational() || MetaMachine.getMachine(level, getPos()) != this) {
                    return;
                }
                for (BlockPos pos : positionsToDestroy) {
                    level.destroyBlock(pos, true);
                }

                // 3. 最后设置新方块状态
                level.setBlock(
                        getPos(),
                        definition.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, facing),
                        Block.UPDATE_ALL);
            });
        }
    }
}
