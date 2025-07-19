package com.moguang.ctnhbio;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.moguang.ctnhbio.common.data.recipe.GreatFleshRecipes;
import com.moguang.ctnhbio.registry.CBRegistrate;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

@GTAddon
public class CTNHBioGTAddon implements IGTAddon {
    @Override
    public CBRegistrate getRegistrate() {
        return CTNHBio.REGISTRATE;
    }

    @Override
    public void initializeAddon() {

    }

    @Override
    public String addonModId() {
        return CTNHBio.MODID;
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        GreatFleshRecipes.init(provider);
    }
}
