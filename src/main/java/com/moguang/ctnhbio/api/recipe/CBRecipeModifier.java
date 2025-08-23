package com.moguang.ctnhbio.api.recipe;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import org.jetbrains.annotations.NotNull;

public class CBRecipeModifier {
    public static @NotNull ModifierFunction batchMode(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        if (recipe.duration < ConfigHolder.INSTANCE.machines.batchDuration) {
            int parallel = ConfigHolder.INSTANCE.machines.batchDuration / recipe.duration;
            parallel = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, parallel);

            if (parallel == 0) return ModifierFunction.NULL;
            if (parallel == 1) return ModifierFunction.IDENTITY;

            int duration = recipe.recipeType == CBRecipeTypes.BASIC_LIVING_RECIPES ? 1 : parallel;

            return ModifierFunction.builder()
                    .inputModifier(ContentModifier.multiplier(parallel))
                    .outputModifier(ContentModifier.multiplier(parallel))
                    .durationMultiplier(duration)
                    .batchParallels(parallel)
                    .build();
        }
        return ModifierFunction.IDENTITY;
    }
}
