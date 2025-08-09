package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class DigesterRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CBRecipeBuilder.of(CTNHBio.id("nutrient"), CBRecipeTypes.DIGEST_RECIPES)
                .nutrient(1)
                .inputItems(Ingredient.of(Items.BREAD))
                .outputItems(ModItems.NUTRIENT_PASTE)
                .duration(100)
                .EUt(32)
                .save(provider);
    }
}
