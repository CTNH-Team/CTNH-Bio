package com.moguang.ctnhbio.machine.greatflesh;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GreatFleshMachine extends WorkableLivingMultiblockMachine {
    public GreatFleshMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        if(recipe != null) lastRecipeId = recipe.id;

        return super.beforeWorking(recipe);
    }

    @Override
    public void afterWorking() {
        super.afterWorking();
        if(!isRemote())
        {
            tryDifferentiate();
        }
    }

    public void tryDifferentiate(){
        String name = lastRecipeId.getPath();
        String target = name.substring(name.indexOf("/")+1);
        var definition = GTRegistries.MACHINES.get(CTNHBio.id(target));
        Level level = getLevel();
        var facing =  getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        if (definition != null && level != null) {
            // 1. 先收集所有需要销毁的机器位置
            List<BlockPos> positionsToDestroy = new ArrayList<>();
            for (var part : getParts()) {
                if (part instanceof MetaMachine machine) {
                    positionsToDestroy.add(machine.getPos());
                }
            }

            // 2. 异步销毁所有机器
            level.getServer().submit(() -> {
                for (BlockPos pos : positionsToDestroy) {
                    level.destroyBlock(pos, true);
                }

                // 3. 最后设置新方块状态
                level.setBlock(
                        getPos(),
                        definition.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, facing),
                        Block.UPDATE_ALL
                );
            });
        }
    }
}
