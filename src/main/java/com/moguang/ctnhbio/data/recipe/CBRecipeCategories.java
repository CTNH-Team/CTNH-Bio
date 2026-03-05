package com.moguang.ctnhbio.data.recipe;

import com.gregtechceu.gtceu.api.registry.GTRegistries;

import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture;

import com.moguang.ctnhbio.registry.CBRecipeTypes;

public class CBRecipeCategories {

    public static void init() {
        GTRegistries.RECIPE_CATEGORIES.get(CBRecipeTypes.BASIC_LIVING_RECIPES.registryName)
                .setIcon(new ResourceTexture("biomancy:textures/item/nutrient_paste.png"));
        GTRegistries.RECIPE_CATEGORIES.get(CBRecipeTypes.COGNI_ASSEMBLY.registryName)
                .setIcon(new ResourceTexture("ctnhbio:textures/item/wetware_capacitor.png"));
    }
}
