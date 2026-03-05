package com.moguang.ctnhbio;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.addon.events.KJSRecipeKeyEvent;
import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.MapIngredientTypeManager;
import com.gregtechceu.gtceu.integration.kjs.recipe.components.ContentJS;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import com.moguang.ctnhbio.api.capability.recipe.NutrientRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelMapIngredient;
import com.moguang.ctnhbio.data.CBElements;
import com.moguang.ctnhbio.data.recipe.RecipeRemoval;
import com.moguang.ctnhbio.registry.*;
import com.mojang.datafixers.util.Pair;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;

import java.util.function.Consumer;

@GTAddon
public class CTNHBioGTAddon implements IGTAddon {

    public static final ContentJS<Double> NU_IN = new ContentJS<>(NumberComponent.DOUBLE, NutrientRecipeCapability.CAP,
            false);
    public static final ContentJS<Double> NU_OUT = new ContentJS<>(NumberComponent.DOUBLE, NutrientRecipeCapability.CAP,
            true);

    @Override
    public CBRegistrate getRegistrate() {
        return CTNHBio.REGISTRATE;
    }

    @Override
    public void initializeAddon() {
        CBItems.init();
        CBBlocks.init();
        MapIngredientTypeManager.registerMapIngredient(ModelIngredient.class, ModelMapIngredient::from);
    }

    @Override
    public void registerRecipeCapabilities() {
        CBRecipeCapabilities.init();
    }

    @Override
    public void registerRecipeKeys(KJSRecipeKeyEvent event) {
        event.registerKey(
                NutrientRecipeCapability.CAP,
                Pair.of(NU_IN, NU_OUT));
    }

    @Override
    public String addonModId() {
        return CTNHBio.MODID;
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        CBRecipes.init(provider);
    }

    @Override
    public void registerElements() {
        CBElements.init();
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
        RecipeRemoval.init(consumer);
    }
}
