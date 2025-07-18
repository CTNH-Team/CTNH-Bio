package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

public class CBRecipeTypes {
    public static final GTRecipeType BIOELECTRIC_FORGE_RECIPES = GTRecipeTypes.register("bioelectric_forge", GTRecipeTypes.ELECTRIC)
            .setMaxIOSize(6, 2, 3, 1)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType GREAT_FLESH = GTRecipeTypes.register("great_flesh", GTRecipeTypes.ELECTRIC)
            .setMaxIOSize(6, 0, 3, 0)
            //.setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static void init() {}
}
