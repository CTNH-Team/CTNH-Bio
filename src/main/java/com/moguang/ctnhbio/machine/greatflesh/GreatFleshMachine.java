package com.moguang.ctnhbio.machine.greatflesh;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
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
        tryDifferentiate();
        super.afterWorking();
    }

    public void tryDifferentiate(){
        //GTRegistries.MACHINES
    }
}
