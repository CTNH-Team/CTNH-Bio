package com.moguang.ctnhbio.data.recipe.multi;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTItems.*;

public class CogniRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        // CogniRecipeBuilder.start(
        // CTNHBio.id("cogni_wetware_super_computer"),
        // CBRecipeTypes.COGNI_ASSEMBLY,
        // CBRecipeTypes.COGNI_ASSEMBLY_STEP
        // )
        // .EUt(32)
        // .duration(60)
        // .setIntermediate(CBItems.WETWARE_PROCESSOR_COMPUTER_UNFINISHED.get())
        // .setFinalOutput(WETWARE_SUPER_COMPUTER_UV.get())
        // .addStep(step -> step
        // .inputItems(WETWARE_PROCESSOR_ASSEMBLY_ZPM)
        // .inputModel(ModelIngredient.of(ModelTier.SELF_AWARE, EntityType.COW))
        // )
        // .addStep(step -> step
        // .inputItems(WETWARE_DIODE, 2))
        // .addStep(step -> step
        // .inputItems(RANDOM_ACCESS_MEMORY, 2))
        // .save(provider);
    }
}
