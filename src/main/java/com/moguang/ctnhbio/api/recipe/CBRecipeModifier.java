package com.moguang.ctnhbio.api.recipe;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.config.ConfigHolder;

import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CBRecipeModifier {

    public static @Nullable Component autoBatchMode(@NotNull MetaMachine machine, RecipeHandlerGroup group,
                                                    @NotNull GTRecipe recipe) {
        if (recipe.duration < ConfigHolder.INSTANCE.machines.batchDuration) {
            int parallel = ConfigHolder.INSTANCE.machines.batchDuration / recipe.duration;
            parallel = ParallelLogic.getParallelAmount(group, recipe, parallel, false);

            if (parallel <= 1) return null;

            recipe.multiplyInputs(parallel);
            recipe.multiplyOutputs(parallel);
            recipe.multiplyDuration(parallel);
            recipe.batchParallels *= parallel;
        }
        return null;
    }
}
