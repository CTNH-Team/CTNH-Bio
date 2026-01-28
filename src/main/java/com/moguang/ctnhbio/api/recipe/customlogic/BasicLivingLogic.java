package com.moguang.ctnhbio.api.recipe.customlogic;

import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import org.jetbrains.annotations.Nullable;

public class BasicLivingLogic implements GTRecipeType.ICustomRecipeLogic {
    @Override
    public @Nullable GTRecipe createCustomRecipe(IRecipeCapabilityHolder holder) {
        return CBRecipeTypes.BASIC_LIVING_RECIPES.getLookup().findRecipe(holder);
    }
}
