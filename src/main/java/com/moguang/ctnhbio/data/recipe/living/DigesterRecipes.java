package com.moguang.ctnhbio.data.recipe.living;

import com.github.elenterius.biofactory.init.ModFluids;
import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import com.moguang.ctnhbio.registry.CBTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.github.elenterius.biofactory.init.ModFluids.NUTRIENTS_FLUID;
import static com.github.elenterius.biomancy.init.ModFluids.ACID;


public class DigesterRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CBRecipeBuilder.of(CTNHBio.id("nutrient_solid"), CBRecipeTypes.DIGEST_RECIPES)
                .nutrient(1)
                .circuitMeta(1)
                .inputItems(Ingredient.of(CBTags.FOOD_TAG))
                .outputItems(new ItemStack(ModItems.NUTRIENT_PASTE.get(), 1) )
                .outputItems(ModItems.NUTRIENT_BAR)
                .duration(100)
                .EUt(32)
                .addData("circuit", 1)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("nutrient_fluid"), CBRecipeTypes.DIGEST_RECIPES)
                .nutrient(1)
                .circuitMeta(2)
                .inputItems(Ingredient.of(CBTags.FOOD_TAG))
                .outputFluids(new FluidStack(NUTRIENTS_FLUID.get(), 1))
                .duration(100)
                .EUt(32)
                .addData("circuit", 2)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("acid"), CBRecipeTypes.DIGEST_RECIPES)
                .nutrient(10)
                .circuitMeta(3)
                .outputFluidsRanged(new FluidStack(ACID.get(), 1), UniformInt.of(50, 150))
                .duration(100)
                .EUt(128)
                .addData("circuit", 0)
                .save(provider);
    }
}
