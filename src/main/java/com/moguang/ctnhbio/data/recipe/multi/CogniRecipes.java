package com.moguang.ctnhbio.data.recipe.multi;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CogniRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.moguang.ctnhbio.registry.CBItems.ADVANCED_RAM_CHIP;
import static com.moguang.ctnhbio.registry.CBItems.WETWARE_DIODE;

public class CogniRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CogniRecipeBuilder.start(
                CTNHBio.id("cogni_wetware_super_computer"),
                CBRecipeTypes.COGNI_ASSEMBLE,
                CBRecipeTypes.COGNI_ASSEMBLE_STEP
        )
                .EUt(32)
                .duration(60)
                .setIntermediate(Items.GLASS)
                .setFinalOutput(WETWARE_SUPER_COMPUTER_UV.get())
                .addStep(step -> step
                        .inputItems(WETWARE_PROCESSOR_ASSEMBLY_ZPM)
                        .inputItems(NOR_MEMORY_CHIP)
                        .inputModel(EntityType.COW, ModelTier.SELF_AWARE))
                .addStep(step -> step
                        .inputItems(WETWARE_DIODE, 2))
                .addStep(step -> step
                        .inputItems(ADVANCED_RAM_CHIP, 2))
                .save(provider);

    }
}
