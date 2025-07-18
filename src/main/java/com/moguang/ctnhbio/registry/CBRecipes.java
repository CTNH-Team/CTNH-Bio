package com.moguang.ctnhbio.registry;

import com.moguang.ctnhbio.data.recipe.BioElectricRecipes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class CBRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        BioElectricRecipes.init(provider);
    }
}
