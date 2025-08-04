package com.moguang.ctnhbio.data.recipe;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class ThinkingRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CBRecipeBuilder.of(CTNHBio.id("brain_thinking"), CBRecipeTypes.BRAIN_IN_A_VAT_RECIPES)
                .nutrient(100)
                .EUt(512)
                .outputCWU(1)
                .duration(200)
                .save(provider);
    }
}
