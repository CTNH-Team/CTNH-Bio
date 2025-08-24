package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import com.moguang.ctnhbio.api.capability.recipe.EntityRecipeCapability;

public class CBRecipeTypes {
    public static String NUTRIENT = "nutrient";
    public static final GTRecipeType BIOELECTRIC_FORGE_RECIPES = GTRecipeTypes.register("bioelectric_forge", NUTRIENT)
            .setMaxIOSize(6, 2, 3, 1)
            .setEUIO(IO.IN)
            .setMaxTooltips(5)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType DECOMPOSER_RECIPES = GTRecipeTypes.register("decomposer", NUTRIENT)
            .setMaxIOSize(2, 6, 1, 3)
            .setEUIO(IO.IN)
            .setMaxTooltips(5)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType DIGEST_RECIPES = GTRecipeTypes.register("digest", NUTRIENT)
            .setMaxIOSize(2, 2, 2, 2)
            .setEUIO(IO.IN)
            .setMaxTooltips(5)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType BIO_REACTOR_RECIPES = GTRecipeTypes.register("ctnhbio_reactor", NUTRIENT)
            .setMaxIOSize(3, 3, 3, 3)
            .setEUIO(IO.IN)
            .setMaxTooltips(5)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType BRAIN_IN_A_VAT_RECIPES = GTRecipeTypes.register("brain_in_a_vat", NUTRIENT)
            .setMaxIOSize(1, 0, 1, 0)
            .setEUIO(IO.IN)
            .setMaxTooltips(5)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BOILER_HEAT, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType BASIC_LIVING_RECIPES = GTRecipeTypes.register("basic_living", NUTRIENT)
            .setMaxIOSize(1, 0, 1, 0)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType GREAT_FLESH = GTRecipeTypes.register("great_flesh", GTRecipeTypes.ELECTRIC)
            .setMaxIOSize(6, 0, 3, 0)
            //.setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType HOSTILE_OBSERVATION = GTRecipeTypes.register("hostile_observation", NUTRIENT)
            .setMaxSize(IO.IN, ItemRecipeCapability.CAP,1)
            .setMaxSize(IO.IN, EntityRecipeCapability.CAP,1)
            .setMaxSize(IO.OUT, ItemRecipeCapability.CAP,1)
            .setEUIO(IO.IN)
            .setMaxTooltips(5)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.UP_TO_DOWN);

    public static void init() {}
}
