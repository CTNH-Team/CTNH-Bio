package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.ingredient.item.ItemIngredient;
import com.gregtechceu.gtceu.api.registry.GTRegistries;

import com.moguang.ctnhbio.api.capability.recipe.CogniItemRecipeCapability;
import com.moguang.ctnhbio.api.capability.recipe.EntityRecipeCapability;
import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
import com.moguang.ctnhbio.api.capability.recipe.NutrientRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;

public class CBRecipeCapabilities {

    public static final RecipeCapability<Float> NUTRIENT = NutrientRecipeCapability.CAP;
    public static final RecipeCapability<EntityIngredient> ENTITY = EntityRecipeCapability.CAP;
    public static final RecipeCapability<ModelIngredient> MODEL = ModelRecipeCapability.CAP;
    public static final RecipeCapability<ItemIngredient> COGNI_ITEM = CogniItemRecipeCapability.CAP;

    public static void init() {
        GTRegistries.RECIPE_CAPABILITIES.register(NUTRIENT.name, NUTRIENT);
        GTRegistries.RECIPE_CAPABILITIES.register(ENTITY.name, ENTITY);
        GTRegistries.RECIPE_CAPABILITIES.register(MODEL.name, MODEL);
        GTRegistries.RECIPE_CAPABILITIES.register(COGNI_ITEM.name, COGNI_ITEM);
    }
}
