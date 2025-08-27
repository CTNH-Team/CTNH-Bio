package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.common.data.GTRecipeCategories;
import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture;
import com.moguang.ctnhbio.registry.CBRecipeTypes;

public class CBRecipeCategories {

    public static void init() {
        GTRecipeCategories.get("basic_living").setIcon(new ResourceTexture("biomancy:textures/item/nutrient_paste.png"));
    }
}
