package com.moguang.ctnhbio.registry;

import com.moguang.ctnhbio.data.recipe.GreatFleshRecipes;
import com.moguang.ctnhbio.data.recipe.BasicLivingRecipes;
import com.moguang.ctnhbio.data.recipe.DecomposerRecipes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class CBRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        DecomposerRecipes.init(provider);
        BasicLivingRecipes.init(provider);
        GreatFleshRecipes.init(provider);
    }
}
