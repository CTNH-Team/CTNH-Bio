package com.moguang.ctnhbio.data.recipe.multi;

import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.L;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Polybenzimidazole;
import static com.moguang.ctnhbio.registry.CBItems.*;
import static com.moguang.ctnhbio.registry.CBItems.WETWARE_INDUCTOR;

public class ConsciousnessAssemblyRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CBRecipeBuilder.of(CTNHBio.id("wetware_super_computer_uv_cb"), CBRecipeTypes.CONSCIOUSNESS_ASSEMBLY)
                .inputItems(WETWARE_PRINTED_CIRCUIT_BOARD)
                .inputItems(WETWARE_PROCESSOR_ASSEMBLY_ZPM, 2)
                .inputItems(WETWARE_DIODE, 2)
                .inputItems(NOR_MEMORY_CHIP, 16)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ctnhcore:advanced_ram_chip")),8)
                .inputItems(wireFine, YttriumBariumCuprate, 24)
                .inputItems(foil, Polybenzimidazole, 32)
                .inputItems(plate, Europium, 4)
                .inputFluids(SolderingAlloy.getFluid(1152))
                .outputItemsRanged(new ItemStack(GTItems.WETWARE_SUPER_COMPUTER_UV.get()), UniformInt.of(1, 2))
                .EUt(38400).duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("wetware_mainframe_uhv_cb"), CBRecipeTypes.CONSCIOUSNESS_ASSEMBLY)
                .inputItems(frameGt, Tritanium, 2)
                .inputItems(WETWARE_SUPER_COMPUTER_UV, 2)
                .inputItems(WETWARE_DIODE, 8)
                .inputItems(WETWARE_CAPACITOR, 8)
                .inputItems(WETWARE_TRANSISTOR, 8)
                .inputItems(WETWARE_RESISTOR, 8)
                .inputItems(WETWARE_INDUCTOR, 8)
                .inputItems(foil, Polybenzimidazole, 64)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ctnhcore:advanced_ram_chip")),8)
                .inputItems(wireGtDouble, EnrichedNaquadahTriniumEuropiumDuranide, 16)
                .inputItems(plate, Europium, 8)
                .inputFluids(SolderingAlloy.getFluid(L * 20))
                .inputFluids(Polybenzimidazole.getFluid(L * 8))
                .outputItemsRanged(new ItemStack(GTItems.WETWARE_MAINFRAME_UHV.get()), UniformInt.of(1, 2))
                .EUt(300000).duration(800)
                .save(provider);
    }
}
