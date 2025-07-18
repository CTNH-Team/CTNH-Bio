package com.moguang.ctnhbio.common.data.machine.multiblock;

import com.github.elenterius.biomancy.BiomancyMod;
import com.github.elenterius.biomancy.init.ModBlocks;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
import com.moguang.ctnhbio.registry.CBRecipeTypes;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class MultiblocksA {
    public static void init() {}
    public static MultiblockMachineDefinition BASIC_MULTI_LIVING_MACHINE = REGISTRATE.biomultiblock("basic_multi_living_machine", WorkableLivingMultiblockMachine::new)
            .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAA", "AAA", "AAA")
                    .aisle("AAA", "AAA", "AAA")
                    .aisle("AAA", "A@A", "AAA")
                    .where("A", Predicates.blocks(ModBlocks.FLESH.get()))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .build())
            .workableCasingModel(BiomancyMod.createRL("block/flesh"), GTCEu.id("block/multiblock/assembly_line"))
            .register();
}
