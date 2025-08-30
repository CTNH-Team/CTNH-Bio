package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.moguang.ctnhbio.api.capability.NutrientRecipeCapability;
import com.moguang.ctnhbio.api.capability.recipe.EntityRecipeCapability;
import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import net.minecraft.world.item.crafting.Ingredient;

public class CBRecipeCapabilities {
    public static final RecipeCapability<Double> NUTRIENT = NutrientRecipeCapability.CAP;
    public static final RecipeCapability<EntityIngredient> ENTITY = EntityRecipeCapability.CAP;
    public static final RecipeCapability<ModelIngredient> MODEL = ModelRecipeCapability.CAP;
    public static void init() {
        GTRegistries.RECIPE_CAPABILITIES.register(NUTRIENT.name, NUTRIENT);
        GTRegistries.RECIPE_CAPABILITIES.register(ENTITY.name, ENTITY);
        GTRegistries.RECIPE_CAPABILITIES.register(MODEL.name, MODEL);
    }
}
