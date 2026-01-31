package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.capability.recipe.CogniItemRecipeCapability;
import com.moguang.ctnhbio.api.capability.recipe.EntityRecipeCapability;
import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
import com.moguang.ctnhbio.api.gui.CBRecipeTypeUI;
import com.moguang.ctnhbio.api.recipe.customlogic.DigestRecipeLogic;
import net.minecraft.network.chat.Component;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBRecipeTypes {
    public static String NUTRIENT = "nutrient";

    // 声明所有配方类型变量
    public static GTRecipeType BIOELECTRIC_FORGE_RECIPES;
    public static GTRecipeType DECOMPOSER_RECIPES;
    public static GTRecipeType DIGEST_RECIPES;
    public static GTRecipeType BIO_REACTOR_RECIPES;
    public static GTRecipeType BRAIN_IN_A_VAT_RECIPES;
    public static GTRecipeType BASIC_LIVING_RECIPES;
    public static GTRecipeType GREAT_FLESH;
    public static GTRecipeType COGNI_ASSEMBLY;
    public static GTRecipeType COGNI_ASSEMBLY_STEP;
    public static GTRecipeType HOSTILE_OBSERVATION;

    public static void init() {
        // 初始化所有配方类型
        BIOELECTRIC_FORGE_RECIPES = REGISTRATE.recipeType(CTNHBio.id("bioelectric_forge"), NUTRIENT)
                //GTRecipeTypes.register("bioelectric_forge", NUTRIENT)
                .cnlang("生物电炉")
                .lang("Bioelectric Forge")
                .setMaxIOSize(6, 2, 3, 1)
                .setEUIO(IO.IN)
                .setMaxTooltips(5)
                .setSound(GTSoundEntries.CHEMICAL)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

        DECOMPOSER_RECIPES = REGISTRATE.recipeType("decomposer", NUTRIENT)
                .cnlang("电力分解")
                .lang("Decomposer")
                .setMaxIOSize(2, 6, 3, 3)
                .setEUIO(IO.IN)
                .setMaxTooltips(5)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

        DIGEST_RECIPES = REGISTRATE.recipeType(CTNHBio.id("digest"), NUTRIENT)
                .cnlang("电力消化")
                .lang("Digest")
                .setMaxIOSize(2, 2, 2, 2)
                .setEUIO(IO.IN)
                .setMaxTooltips(6)
                .setSound(CBSoundEntries.DIGESTER_CRAFTING)
                .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .addDataInfo(tag -> {
                    if(tag.contains("info")){
                        return DigestRecipeLogic.based_on_nutrition.translate().getString();
                    }
                    return "";
                })
                .addCustomRecipeLogic(new DigestRecipeLogic());

        BIO_REACTOR_RECIPES = REGISTRATE.recipeType(CTNHBio.id("ctnhbio_reactor"), NUTRIENT)
                .cnlang("生物反应")
                .lang("Bio Reactor")
                .setMaxIOSize(3, 3, 3, 3)
                .setEUIO(IO.IN)
                .setMaxTooltips(5)
                .setSound(GTSoundEntries.CHEMICAL)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

        BRAIN_IN_A_VAT_RECIPES = REGISTRATE.recipeType(CTNHBio.id("brain_in_a_vat"), NUTRIENT)
                .cnlang("缸中之脑")
                .lang("Brain In A Vat")
                .setMaxIOSize(1, 0, 1, 0)
                .setEUIO(IO.IN)
                .setMaxTooltips(5)
                .setSound(GTSoundEntries.SCIENCE)
                .setProgressBar(GuiTextures.PROGRESS_BAR_BOILER_HEAT, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

        BASIC_LIVING_RECIPES = REGISTRATE.recipeType(CTNHBio.id("basic_living"), NUTRIENT)
                .cnlang("摄入营养")
                .lang("Living")
                .setMaxIOSize(1, 0, 1, 0)
                .setEUIO(IO.IN)
                .setMaxTooltips(5)
                .setSound(CBSoundEntries.EAT)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

        GREAT_FLESH = REGISTRATE.recipeType(CTNHBio.id("great_flesh"), NUTRIENT)
                .cnlang("巨型肉块-分化")
                .lang("Great Flesh")
                .setMaxIOSize(6, 0, 3, 0)
                .setEUIO(IO.IN)
                .setMaxTooltips(5)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

//        CONSCIOUSNESS_ASSEMBLY = REGISTRATE.recipeType(CTNHBio.id("consciousness_assembly"), NUTRIENT)
//                .cnlang("意识装配")
//                .lang("Consciousness Assembly")
//                .setMaxIOSize(15, 1, 3, 0)
//                .setEUIO(IO.IN)
//                .setMaxTooltips(5)
//                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

        COGNI_ASSEMBLY = REGISTRATE.recipeType(CTNHBio.id("cogni_assembly"), GTRecipeTypes.ELECTRIC)
                .cnlang("意识装配流程")
                .setMaxIOSize(9, 9, 9, 9)
                .setMaxSize(IO.IN, ModelRecipeCapability.CAP,9)

                .setEUIO(IO.IN)
                .setMaxTooltips(5)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

        //COGNI_ASSEMBLY.getRecipeUI().getJEISize()

        HOSTILE_OBSERVATION = REGISTRATE.recipeType(CTNHBio.id("hostile_observation"), NUTRIENT)
                .cnlang("敌意观测")
                .lang("Hostile Observation")
                .setMaxSize(IO.IN, EntityRecipeCapability.CAP,1)
                .setMaxSize(IO.IN, ModelRecipeCapability.CAP,1)
                .setMaxSize(IO.OUT, ModelRecipeCapability.CAP,1)
                .setMaxIOSize(2,0,0,0)
                .setEUIO(IO.IN)
                .setMaxTooltips(5)
                .setSound(GTSoundEntries.COMPUTATION)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.UP_TO_DOWN);

        COGNI_ASSEMBLY_STEP = REGISTRATE.recipeType(CTNHBio.id("cogni_assembly_step"), GTRecipeTypes.ELECTRIC)
                .cnlang("意识装配")
                .lang("Cogni Assembly")
                .setMaxIOSize(1, 1, 1, 1)
                .setMaxSize(IO.IN, ModelRecipeCapability.CAP,1)
                .setMaxSize(IO.IN, CogniItemRecipeCapability.CAP,1)
                .setMaxSize(IO.OUT, CogniItemRecipeCapability.CAP,1)
                .setEUIO(IO.IN)
                .setMaxTooltips(5)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    }
}