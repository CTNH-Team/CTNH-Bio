package com.moguang.ctnhbio.data.recipe.living;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraftforge.fluids.FluidStack;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;

import java.util.function.Consumer;

import static com.github.elenterius.biomancy.init.ModFluids.ACID;

public class DigesterRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
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
