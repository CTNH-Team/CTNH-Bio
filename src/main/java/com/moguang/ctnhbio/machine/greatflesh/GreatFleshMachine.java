package com.moguang.ctnhbio.machine.greatflesh;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

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
        if(!isRemote())
        {
            tryDifferentiate();
        }
        super.afterWorking();
    }

    public void tryDifferentiate(){
        String name = lastRecipeId.getPath();
        String target = name.substring(name.indexOf("/")+1);
        var definition = GTRegistries.MACHINES.get(CTNHBio.id(target));
        Level level = getLevel();
        var facing =  getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        if(definition != null && level!= null)
        {
            level.getServer().submit(()->
                    level.setBlock(getPos(), definition.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING , facing), Block.UPDATE_ALL)
                    );
        }
    }
}
