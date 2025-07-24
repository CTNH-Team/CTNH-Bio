package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class BasicLivingRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CBRecipeBuilder.of(CTNHBio.id("nutrient"), CBRecipeTypes.BASIC_LIVING_RECIPES)
                .inputItems(ModItems.NUTRIENT_PASTE.get().asItem().getDefaultInstance())
                .nutrient(-3)
                .duration(1)
                .EUt(32)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("nutrient2"), CBRecipeTypes.BASIC_LIVING_RECIPES)
                .inputItems(ModItems.NUTRIENT_BAR.get().asItem().getDefaultInstance())
                .nutrient(-27)
                .duration(1)
                .EUt(32)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("nutrient3"), CBRecipeTypes.BASIC_LIVING_RECIPES)
                .inputItems(ModItems.NUTRIENTS.get().asItem().getDefaultInstance())
                .nutrient(-1)
                .duration(1)
                .EUt(32)
                .save(provider);
    }
}
