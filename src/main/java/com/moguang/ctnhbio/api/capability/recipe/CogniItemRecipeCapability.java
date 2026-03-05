package com.moguang.ctnhbio.api.capability.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import com.moguang.ctnhbio.mixin.gtm.RecipeCapabilityAccessor;

public class CogniItemRecipeCapability extends ItemRecipeCapability {

    public static final CogniItemRecipeCapability CAP = new CogniItemRecipeCapability();

    protected CogniItemRecipeCapability() {
        super();
        ((RecipeCapabilityAccessor) this).setName("cogni_item");
        ((RecipeCapabilityAccessor) this).setSortIndex(-100);
    }

    @Override
    public int getMaxParallelByInput(IRecipeCapabilityHolder holder, GTRecipe recipe, int limit, boolean tick) {
        return 1;
    }
}
