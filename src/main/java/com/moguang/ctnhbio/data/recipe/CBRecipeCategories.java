package com.moguang.ctnhbio.data.recipe;

import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.registry.GTRegistries;

import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBRecipeTypes;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBRecipeCategories {

    public static final GTRecipeCategory BIO_ORE_PROCESSING = REGISTRATE
            .recipeCategory(CTNHBio.id("bio_ore_processing"), CBRecipeTypes.DECOMPOSER_RECIPES)
            .lang("Bio Ore Processing")
            .cnlang("生物矿处")
            .setIcon(new ResourceTexture("biomancy:textures/fluid/acid_overlay.png").setColor(0xFF_39FF14));

    public static void init() {
        GTRegistries.RECIPE_CATEGORIES.get(CBRecipeTypes.BASIC_LIVING_RECIPES.registryName)
                .setIcon(new ResourceTexture("biomancy:textures/item/nutrient_paste.png"));
        GTRegistries.RECIPE_CATEGORIES.get(CBRecipeTypes.COGNI_ASSEMBLY.registryName)
                .setIcon(new ResourceTexture("ctnhbio:textures/item/wetware_capacitor.png"));
    }
}
