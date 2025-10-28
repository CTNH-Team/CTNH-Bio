package com.moguang.ctnhbio.data.recipe.multi;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import com.moguang.ctnhbio.data.recipe.CogniRecipeBuilder;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTItems.*;

import static com.moguang.ctnhbio.registry.CBItems.WETWARE_DIODE;

public class CogniRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CogniRecipeBuilder.start(
                CTNHBio.id("cogni_wetware_super_computer"),
                CBRecipeTypes.COGNI_ASSEMBLY,
                CBRecipeTypes.COGNI_ASSEMBLY_STEP
        )
                .EUt(32)
                .duration(60)
                .setIntermediate(CBItems.WETWARE_PROCESSOR_COMPUTER_UNFINISHED.get())
                .setFinalOutput(WETWARE_SUPER_COMPUTER_UV.get())
                .addStep(step -> step
                        .inputItems(WETWARE_PROCESSOR_ASSEMBLY_ZPM)
                        .inputModel(ModelIngredient.of(ModelTier.SELF_AWARE, EntityType.COW))
                )
                .addStep(step -> step
                        .inputItems(WETWARE_DIODE, 2))
                .addStep(step -> step
                        .inputItems(RANDOM_ACCESS_MEMORY, 2))
                .save(provider);

    }
}
