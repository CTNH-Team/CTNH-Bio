package com.moguang.ctnhbio.registry;

import com.moguang.ctnhbio.data.recipe.*;
import com.moguang.ctnhbio.data.recipe.living.*;
import com.moguang.ctnhbio.data.recipe.multi.GreatFleshRecipes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class CBRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        DecomposerRecipes.init(provider);
        BasicLivingRecipes.init(provider);
        GreatFleshRecipes.init(provider);
        BioelectrlcForgeRecipes.init(provider);
        BioReactorRecipes.init(provider);
        DigesterRecipes.init(provider);
        CommonRecipes.init(provider);
        //BiomancyRecipes.init(provider);
        VanillaRecipeProvider.init(provider);
    }
}
