package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTFluids;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

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
        CBRecipeBuilder.of(CTNHBio.id("potion"), CBRecipeTypes.BASIC_LIVING_RECIPES)
                .inputFluids(FluidIngredient.of(CustomTags.POTION_FLUIDS, 250))
                .duration(10)
                .EUt(32)
                .addData("potion", true)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("potion_test"), CBRecipeTypes.BASIC_LIVING_RECIPES)
                .inputFluids(FluidIngredient.of(Fluids.WATER, 250))
                .duration(10)
                .EUt(32)
                .save(provider);
    }
}
