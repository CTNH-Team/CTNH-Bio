package com.moguang.ctnhbio.data.recipe.living;

import com.github.elenterius.biofactory.init.ModFluids;
import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import com.moguang.ctnhbio.registry.CBTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class DigesterRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CBRecipeBuilder.of(CTNHBio.id("nutrient_solid"), CBRecipeTypes.DIGEST_RECIPES)
                .nutrient(1)
                .circuitMeta(0)
                .inputItems(Ingredient.of(CBTags.FOOD_TAG))
                .outputItems(ModItems.NUTRIENT_PASTE)
                .outputItems(ModItems.NUTRIENT_BAR)
                .duration(100)
                .EUt(32)
                .addData("circuit", 0)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("nutrient_fluid"), CBRecipeTypes.DIGEST_RECIPES)
                .nutrient(1)
                .circuitMeta(1)
                .inputItems(Ingredient.of(CBTags.FOOD_TAG))
                .outputFluids(new FluidStack(ModFluids.NUTRIENTS_FLUID.get(), 1))
                .duration(100)
                .EUt(32)
                .addData("circuit", 1)
                .save(provider);
    }
}
