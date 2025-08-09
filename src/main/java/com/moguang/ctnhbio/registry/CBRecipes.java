package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.*;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class CBRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        DecomposerRecipes.init(provider);
        BasicLivingRecipes.init(provider);
        GreatFleshRecipes.init(provider);
        BioelectrlcForgeRecipes.init(provider);
        BioReactorRecipes.init(provider);
        DigesterRecipes.init(provider);
        ThinkingRecipes.init(provider);
        CommonRecipes.init(provider);
    }
}
