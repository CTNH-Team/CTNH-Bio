package com.moguang.ctnhbio.api.capability.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.api.recipe.ingredient.item.ItemIngredient;

public class CogniItemRecipeCapability extends ItemRecipeCapability {

    public static final CogniItemRecipeCapability CAP = new CogniItemRecipeCapability();

    protected CogniItemRecipeCapability() {
        super("cogni_item", 0xFFD96106, true, ItemIngredient.CODEC);
    }

    @Override
    public int getMaxParallelByInput(RecipeHandlerGroup holder, GTRecipe recipe, int limit, boolean tick) {
        return 1;
    }
}
