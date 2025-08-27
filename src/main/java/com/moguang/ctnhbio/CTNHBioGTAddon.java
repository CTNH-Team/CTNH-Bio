package com.moguang.ctnhbio;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.moguang.ctnhbio.data.recipe.RecipeRemoval;
import com.moguang.ctnhbio.registry.*;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

@GTAddon
public class CTNHBioGTAddon implements IGTAddon {
    @Override
    public CBRegistrate getRegistrate() {
        return CTNHBio.REGISTRATE;
    }

    @Override
    public void initializeAddon() {
        CBItems.init();
        CBBlocks.init();
    }

    @Override
    public void registerRecipeCapabilities() {
        CBRecipeCapabilities.init();
    }

    @Override
    public String addonModId() {
        return CTNHBio.MODID;
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        CBRecipes.init(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
        RecipeRemoval.init(consumer);
    }
}
