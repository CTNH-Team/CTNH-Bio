package com.moguang.ctnhbio.data.recipe;

import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class GreatFleshRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CBRecipeTypes.GREAT_FLESH.recipeBuilder("great_bioelectric_forge")
                .inputFluids(new FluidStack(Fluids.WATER, 100))
                .inputItems(new ItemStack(Items.ACACIA_LOG, 10))
                //.EUt(200)
                .duration(50)
                .save(provider);
    }
}
