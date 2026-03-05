package com.moguang.ctnhbio.data.recipe;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.common.data.GTMachines;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;

import com.moguang.ctnhbio.CTNHBio;
import com.simibubi.create.api.data.recipe.ItemApplicationRecipeGen;

import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.moguang.ctnhbio.registry.CBItems.BIO_CORES;
import static com.moguang.ctnhbio.registry.CBMachines.*;

public class LivingMachineRecipes extends ItemApplicationRecipeGen {

    private static LivingMachineRecipes INSTANCE = new LivingMachineRecipes();

    public LivingMachineRecipes() {
        super(new PackOutput(Path.of("null")), CTNHBio.MODID);
    }

    public static void init(Consumer<FinishedRecipe> provider) {
        for (var tier : GTValues.tiersBetween(LV, IV)) {
            machineRecipe(
                    GTMachines.ASSEMBLER[tier],
                    BIO_CORES[tier],
                    BIOELECTRIC_FORGE[tier]).register(provider);

            machineRecipe(
                    GTMachines.EXTRACTOR[tier],
                    BIO_CORES[tier],
                    DIGESTER[tier]).register(provider);

            machineRecipe(
                    GTMachines.MACERATOR[tier],
                    BIO_CORES[tier],
                    DECOMPOSER[tier]).register(provider);

            machineRecipe(
                    GTMachines.CHEMICAL_REACTOR[tier],
                    BIO_CORES[tier],
                    BIOREACTOR[tier]).register(provider);
        }
    }

    static GeneratedRecipe machineRecipe(MachineDefinition input1, Supplier<? extends Item> input2,
                                         MachineDefinition output) {
        return INSTANCE.create(output.getDescriptionId(),
                b -> b.require(input1.getItem())
                        .require(input2.get())
                        .output(output.asStack()));
    }
}
