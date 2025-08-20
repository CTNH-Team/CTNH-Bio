package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.moguang.ctnhbio.api.capability.NutrientRecipeCapability;
import com.moguang.ctnhbio.api.capability.recipe.EntityRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;

public class CBRecipeCapabilities {
    public static final RecipeCapability<Double> NUTRIENT = NutrientRecipeCapability.CAP;
    public static final RecipeCapability<EntityIngredient> ENTITY = EntityRecipeCapability.CAP;
    public static void init() {
        GTRegistries.RECIPE_CAPABILITIES.register(NUTRIENT.name, NUTRIENT);
        GTRegistries.RECIPE_CAPABILITIES.register(ENTITY.name, ENTITY);
    }
}
