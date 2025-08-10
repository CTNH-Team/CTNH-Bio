package com.moguang.ctnhbio.data.recipe;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class RecipeRemoval {
    public static void init(Consumer<ResourceLocation> registry) {
        biomancyRemovals(registry);
        ctnhbioRemovals(registry);
    }

    public static void biomancyRemovals(Consumer<ResourceLocation> registry){
        var recipes = new String[]{
                "minecraft:tnt"
        };


        registry.accept(new ResourceLocation("biomancy:crafting/primordial_core"));
        registry.accept(new ResourceLocation("biomancy:bio_brewing"));

    }
    public static void ctnhbioRemovals(Consumer<ResourceLocation> registry){
        var recipes = new String[]{
                "minecraft:tnt"
        };

        registry.accept(new ResourceLocation("gtceu:circuit_assembler/wetware_processor_luv"));
        registry.accept(new ResourceLocation("gtceu:circuit_assembler/wetware_processor_luv_soldering_alloy"));
        registry.accept(new ResourceLocation("gtceu:circuit_assembler/wetware_processor_assembly_zpm"));
        registry.accept(new ResourceLocation("gtceu:circuit_assembler/wetware_processor_assembly_zpm_soldering_alloy"));
        registry.accept(new ResourceLocation("gtceu:assembly_line/wetware_super_computer_uv"));
    }


}
