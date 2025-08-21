package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.data.chemical.material.MarkerMaterials;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.materials.OrganicMaterials;
import com.moguang.ctnhbio.registry.CBItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.ASSEMBLY_LINE_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CIRCUIT_ASSEMBLER_RECIPES;
import static com.moguang.ctnhbio.data.materials.CommonMaterials.*;
import static com.moguang.ctnhbio.registry.CBItems.*;

public class CommonRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        //小化反
        CBRecipeBuilder.of(CTNHBio.id("fenton_reagent_mixing"), GTRecipeTypes.CHEMICAL_RECIPES)
                .inputFluids(GTMaterials.Iron2Chloride, 1000)
                .inputFluids(HydrogenPeroxide, 1000)
                .outputFluids(new FluidStack(FENTONS_REAGENT.getFluid(),2000))
                .duration(100)
                .EUt(1920)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("polypyrrole_from_fenton"), GTRecipeTypes.CHEMICAL_RECIPES)
                .inputFluids(new FluidStack(PYRROLE.getFluid(), 1000))
                .inputFluids(new FluidStack(FENTONS_REAGENT.getFluid(), 1000))
                .outputFluids(new FluidStack(POLYPYRROLE.getFluid(), 1000))
                .outputFluidsRanged(new FluidStack(Iron3Chloride.getFluid(), 500), UniformInt.of(100,200 ))
                .duration(200)
                .EUt(960)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("ferric_to_ferrous_chloride"), GTRecipeTypes.CHEMICAL_RECIPES)
                .inputFluids(new FluidStack(GTMaterials.Iron3Chloride.getFluid(), 2000))
                .inputItems(dust,Iron)
                .outputFluids(new FluidStack(GTMaterials.Iron2Chloride.getFluid(), 3000))
                .duration(100)
                .EUt(480)
                .save(provider);

        //大化反
        CBRecipeBuilder.of(CTNHBio.id("fenton_reagent_mixing"), GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
                .inputFluids(GTMaterials.Iron2Chloride, 1000)
                .inputFluids(HydrogenPeroxide, 1000)
                .outputFluids(new FluidStack(FENTONS_REAGENT.getFluid(),2000))
                .duration(100)
                .EUt(1920)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("polypyrrole_from_fenton"), GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
                .inputFluids(new FluidStack(PYRROLE.getFluid(), 1000))
                .inputFluids(new FluidStack(FENTONS_REAGENT.getFluid(), 1000))
                .outputFluids(new FluidStack(POLYPYRROLE.getFluid(), 1000))
                .outputFluidsRanged(new FluidStack(Iron3Chloride.getFluid(), 500), UniformInt.of(100,200 ))
                .outputFluidsRanged(new FluidStack(GTMaterials.Water.getFluid(), 500), UniformInt.of(100, 200))
                .duration(200)
                .EUt(960)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("ferric_to_ferrous_chloride"), GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
                .inputFluids(new FluidStack(GTMaterials.Iron3Chloride.getFluid(), 2000))
                .inputItems(dust,Iron)
                .outputFluids(new FluidStack(GTMaterials.Iron2Chloride.getFluid(), 3000))
                .duration(100)
                .EUt(480)
                .save(provider);

        //蒸馏室
        CBRecipeBuilder.of(CTNHBio.id("ferric_to_ferrous_chloride"), GTRecipeTypes.DISTILLERY_RECIPES)
                .circuitMeta(5)
                .inputFluids(new FluidStack(CoalTar.getFluid(), 1000))
                .outputFluids(new FluidStack(PYRROLE.getFluid(), 800))
                .duration(120)
                .EUt(384)
                .save(provider);

        //装配线
        CBRecipeBuilder.of(CTNHBio.id("omni_core_recipe"), ASSEMBLY_LINE_RECIPES)
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance())
                .inputItems(frameGt,TungstenSteel)
                .inputItems(spring, Platinum, 2)
                .inputItems(GTItems.ENERGIUM_CRYSTAL, 4)
                .inputItems(CustomTags.LuV_CIRCUITS,2)
                .inputItems(ModItems.BLOOMBERRY.get().getDefaultInstance(),1)
                .inputItems(ModItems.BIO_LUMENS.get().getDefaultInstance(),40)
                .inputItems(ModItems.NUTRIENTS.get().getDefaultInstance(),40)
                .inputFluids(OrganicMaterials.Heterogeneous_Compound, 500)
                .inputFluids(OrganicMaterials.Rejuvenation_Serum, 500)
                .inputFluids(OrganicMaterials.Cleansing_Serum, 500)
                .inputFluids(OrganicMaterials.Frenzy_Serum, 500)
                .outputItems(OMNI_CORE,2)
                .scannerResearch(b -> b
                        .researchStack(NOVA_CORE.asStack(1))
                        .duration(1200)
                        .EUt(VA[IV]))
                .duration(600)
                .EUt(24000)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("mixflesh_recipe"), ASSEMBLY_LINE_RECIPES)
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance(),8)
                .inputItems(SYNET_CORE,2)
                .inputItems(META_CORE,2)
                .inputItems(NOVA_CORE,2)
                .inputItems(OMNI_CORE,2)
                .inputItems(Items.ROTTEN_FLESH, 16)
                .inputItems(ModItems.FLESH_BITS,16)
                .inputItems(ModItems.EXOTIC_DUST.get().getDefaultInstance(),64)
                .inputFluids(OrganicMaterials.Frenzy_Serum, 500)
                .inputFluids(OrganicMaterials.Cleansing_Serum, 500)
                .inputFluids(OrganicMaterials.Breeding_Stimulant, 500)
                .inputFluids(OrganicMaterials.Ageing_Serum, 500)
                .outputItems(ModItems.CREATOR_MIX,32)
                .scannerResearch(b -> b
                        .researchId("flesh_bits_ccb")
                        .researchStack(new ItemStack(Items.BREAD))
                        .EUt(VA[EV]))
                .duration(1000)
                .EUt(24000)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("primordial_cradle_recipe"), ASSEMBLY_LINE_RECIPES)
                .inputItems(ModItems.BIO_FORGE.get().getDefaultInstance(),1)
                .inputItems(ModItems.CREATOR_MIX.get().getDefaultInstance(),8)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(),64)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(),32)
                .inputItems(FIELD_GENERATOR_LuV.get().getDefaultInstance(),4)
                .inputItems(QUANTUM_STAR.get().getDefaultInstance(),4)
                .inputItems(QUANTUM_EYE.get().getDefaultInstance(),4)
                .inputItems(dust,Plutonium239,4)
                .inputItems(dust,Uranium235,4)
                .inputFluids(OrganicMaterials.Genetic_Compound, 1000)
                .inputFluids(OrganicMaterials.Heterogeneous_Compound, 1000)
                .inputFluids(OrganicMaterials.Healing_Compound, 1000)
                .inputFluids(OrganicMaterials.Decay_Essence, 1000)
                .outputItems(ModItems.PRIMORDIAL_CRADLE,1)
                .scannerResearch(b -> b
                        .researchStack(GTMachines.BREWERY[LuV].asStack(1))
                        .duration(1200)
                        .EUt(VA[IV]))
                .duration(1800)
                .EUt(24000)
                .save(provider);
        //切割机
        CBRecipeBuilder.of(CTNHBio.id("advanced_ram_chip_recipe"), GTRecipeTypes.CUTTER_RECIPES)
                .inputItems(CBItems.ADVANCED_RAM_WAFER.get().getDefaultInstance())
                .inputFluids(Lubricant, 500)
                .outputItems(ADVANCED_RAM_CHIP,16)
                .duration(900)
                .EUt(1920)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);

//                .notConsumable(lens, Color.Blue)
        //激光蚀刻
        CBRecipeBuilder.of(CTNHBio.id("advanced_ram_wafer_p_recipe"), GTRecipeTypes.LASER_ENGRAVER_RECIPES)
                .notConsumable(lens, MarkerMaterials.Color.Gray)
                .inputItems(PHOSPHORUS_WAFER.get().getDefaultInstance())
                .outputItems(CBItems.ADVANCED_RAM_WAFER.get().getDefaultInstance())
                .duration(450)
                .EUt(1920)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("advanced_ram_wafer_na_recipe"), GTRecipeTypes.LASER_ENGRAVER_RECIPES)
                .notConsumable(lens, MarkerMaterials.Color.Gray)
                .inputItems(NAQUADAH_WAFER.get().getDefaultInstance())
                .outputItems(CBItems.ADVANCED_RAM_WAFER.get().getDefaultInstance(),4)
                .duration(450)
                .EUt(6144)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("advanced_ram_wafer_ne_recipe"), GTRecipeTypes.LASER_ENGRAVER_RECIPES)
                .notConsumable(lens, MarkerMaterials.Color.Gray)
                .inputItems(NEUTRONIUM_WAFER.get().getDefaultInstance())
                .outputItems(CBItems.ADVANCED_RAM_WAFER.get().getDefaultInstance(),8)
                .duration(450)
                .EUt(30720)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(provider);
        //两个要移植到新机器的湿件配方
        CBRecipeBuilder.of(CTNHBio.id("wetware_super_computer_uv_cb"), GTRecipeTypes.ASSEMBLY_LINE_RECIPES)
                .inputItems(WETWARE_PRINTED_CIRCUIT_BOARD)
                .inputItems(WETWARE_PROCESSOR_ASSEMBLY_ZPM, 2)
                .inputItems(WETWARE_DIODE, 2)
                .inputItems(NOR_MEMORY_CHIP, 16)
                .inputItems(ADVANCED_RAM_CHIP, 8)
                .inputItems(wireFine, YttriumBariumCuprate, 24)
                .inputItems(foil, Polybenzimidazole, 32)
                .inputItems(plate, Europium, 4)
                .inputFluids(SolderingAlloy.getFluid(1152))
                .outputItemsRanged(new ItemStack(GTItems.WETWARE_SUPER_COMPUTER_UV.get()), UniformInt.of(1, 2))
                .stationResearch(b -> b.researchStack(WETWARE_PROCESSOR_ASSEMBLY_ZPM.asStack()).CWUt(16))
                .EUt(38400).duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("wetware_mainframe_uhv_cb"), GTRecipeTypes.ASSEMBLY_LINE_RECIPES)
                .inputItems(frameGt, Tritanium, 2)
                .inputItems(WETWARE_SUPER_COMPUTER_UV, 2)
                .inputItems(WETWARE_DIODE, 8)
                .inputItems(WETWARE_CAPACITOR, 8)
                .inputItems(WETWARE_TRANSISTOR, 8)
                .inputItems(WETWARE_RESISTOR, 8)
                .inputItems(WETWARE_INDUCTOR, 8)
                .inputItems(foil, Polybenzimidazole, 64)
                .inputItems(ADVANCED_RAM_CHIP, 8)
                .inputItems(wireGtDouble, EnrichedNaquadahTriniumEuropiumDuranide, 16)
                .inputItems(plate, Europium, 8)
                .inputFluids(SolderingAlloy.getFluid(L * 20))
                .inputFluids(Polybenzimidazole.getFluid(L * 8))
                .outputItemsRanged(new ItemStack(GTItems.WETWARE_MAINFRAME_UHV.get()), UniformInt.of(1, 2))
                .stationResearch(b -> b.researchStack(WETWARE_SUPER_COMPUTER_UV.asStack()).CWUt(96).EUt(VA[UV]))
                .EUt(300000).duration(800)
                .save(provider);
        //研磨
        CBRecipeBuilder.of(CTNHBio.id("weird_pixel_dust_recipe"), GTRecipeTypes.MACERATOR_RECIPES)
                .inputItems(ModItems.NUTRIENT_PASTE,1)
                .outputItemsRanged(dust,WEIRD_PIXEL_DUST, UniformInt.of(1, 3))
                .EUt(24)
                .duration(100)
                .save(provider);
        //对于进阶RAM适配原版电路的配方
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("mainframe_iv_asmd_cb")
                .inputItems(frameGt, Aluminium, 2)
                .inputItems(WORKSTATION_EV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 2)
                .inputItems(ADVANCED_SMD_CAPACITOR, 4)
                .inputItems(ADVANCED_RAM_CHIP, 4)
                .inputItems(wireGtSingle, AnnealedCopper, 16)
                .outputItems(MAINFRAME_IV)
                .solderMultiplier(4)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[HV]).duration(300)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("nano_processor_assembly_ev_asmd_cb")
                .inputItems(ADVANCED_CIRCUIT_BOARD)
                .inputItems(NANO_PROCESSOR_HV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR)
                .inputItems(ADVANCED_SMD_CAPACITOR, 2)
                .inputItems(ADVANCED_RAM_CHIP, 2)
                .inputItems(wireFine, Electrum, 16)
                .outputItems(NANO_PROCESSOR_ASSEMBLY_EV, 2)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(600).duration(150)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("nano_computer_iv_asmd_cb")
                .inputItems(ADVANCED_CIRCUIT_BOARD)
                .inputItems(NANO_PROCESSOR_ASSEMBLY_EV, 2)
                .inputItems(ADVANCED_SMD_DIODE, 2)
                .inputItems(NOR_MEMORY_CHIP, 4)
                .inputItems(ADVANCED_RAM_CHIP, 4)
                .inputItems(wireFine, Electrum, 16)
                .outputItems(NANO_COMPUTER_IV)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(600).duration(150)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("nano_mainframe_luv_asmd_cb")
                .inputItems(frameGt, Aluminium, 2)
                .inputItems(NANO_COMPUTER_IV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 4)
                .inputItems(ADVANCED_SMD_CAPACITOR, 8)
                .inputItems(ADVANCED_RAM_CHIP, 4)
                .inputItems(wireGtSingle, AnnealedCopper, 32)
                .outputItems(NANO_MAINFRAME_LuV)
                .solderMultiplier(4)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[EV]).duration(300)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("quantum_assembly_iv_asmd_cb")
                .inputItems(EXTREME_CIRCUIT_BOARD)
                .inputItems(QUANTUM_PROCESSOR_EV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 2)
                .inputItems(ADVANCED_SMD_CAPACITOR, 4)
                .inputItems(ADVANCED_RAM_CHIP, 1)
                .inputItems(wireFine, Platinum, 16)
                .outputItems(QUANTUM_ASSEMBLY_IV, 2)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(2400).duration(150)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("quantum_computer_luv_asmd_cb")
                .inputItems(EXTREME_CIRCUIT_BOARD)
                .inputItems(QUANTUM_ASSEMBLY_IV, 2)
                .inputItems(ADVANCED_SMD_DIODE, 2)
                .inputItems(NOR_MEMORY_CHIP, 4)
                .inputItems(ADVANCED_RAM_CHIP, 4)
                .inputItems(wireFine, Platinum, 32)
                .outputItems(QUANTUM_COMPUTER_LuV)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(2400).duration(150)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("quantum_mainframe_zpm_asmd_cb")
                .inputItems(frameGt, HSSG, 2)
                .inputItems(QUANTUM_COMPUTER_LuV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 6)
                .inputItems(ADVANCED_SMD_CAPACITOR, 12)
                .inputItems(ADVANCED_RAM_CHIP, 6)
                .inputItems(wireGtSingle, AnnealedCopper, 48)
                .solderMultiplier(4)
                .outputItems(QUANTUM_MAINFRAME_ZPM)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(VA[IV]).duration(300)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("crystal_assembly_luv")
                .inputItems(ELITE_CIRCUIT_BOARD)
                .inputItems(CRYSTAL_PROCESSOR_IV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 4)
                .inputItems(ADVANCED_SMD_CAPACITOR, 8)
                .inputItems(ADVANCED_RAM_CHIP, 6)
                .inputItems(wireFine, NiobiumTitanium, 16)
                .outputItems(CRYSTAL_ASSEMBLY_LuV, 2)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(9600).duration(300)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("crystal_computer_zpm")
                .inputItems(ELITE_CIRCUIT_BOARD)
                .inputItems(CRYSTAL_ASSEMBLY_LuV, 2)
                .inputItems(ADVANCED_RAM_CHIP, 1)
                .inputItems(NOR_MEMORY_CHIP, 32)
                .inputItems(NAND_MEMORY_CHIP, 64)
                .inputItems(wireFine, NiobiumTitanium, 32)
                .solderMultiplier(2)
                .outputItems(CRYSTAL_COMPUTER_ZPM)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(9600).duration(300)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("crystal_mainframe_uv")
                .inputItems(frameGt, HSSE, 2)
                .inputItems(CRYSTAL_COMPUTER_ZPM, 2)
                .inputItems(ADVANCED_RAM_CHIP, 8)
                .inputItems(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .inputItems(wireGtSingle, NiobiumTitanium, 8)
                .inputItems(ADVANCED_SMD_INDUCTOR, 8)
                .inputItems(ADVANCED_SMD_CAPACITOR, 16)
                .inputItems(ADVANCED_SMD_DIODE, 8)
                .inputFluids(SolderingAlloy.getFluid(L * 10))
                .outputItems(CRYSTAL_MAINFRAME_UV)
                .stationResearch(b -> b.researchStack(CRYSTAL_COMPUTER_ZPM.asStack()).CWUt(16))
                .EUt(VA[LuV]).duration(600)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("wetware_processor_assembly_zpm")
                .inputItems(WETWARE_PRINTED_CIRCUIT_BOARD)
                .inputItems(WETWARE_PROCESSOR_LuV, 2)
                .inputItems(ADVANCED_SMD_INDUCTOR, 6)
                .inputItems(ADVANCED_SMD_CAPACITOR, 12)
                .inputItems(ADVANCED_RAM_CHIP, 6)
                .inputItems(wireFine, YttriumBariumCuprate, 16)
                .solderMultiplier(2)
                .outputItems(WETWARE_PROCESSOR_ASSEMBLY_ZPM, 2)
                .cleanroom(CleanroomType.CLEANROOM)
                .EUt(38400).duration(300)
                .save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("data_stick_cb")
                .inputItems(PLASTIC_CIRCUIT_BOARD)
                .inputItems(CENTRAL_PROCESSING_UNIT, 2)
                .inputItems(NAND_MEMORY_CHIP, 32)
                .inputItems(ADVANCED_RAM_CHIP, 1)
                .inputItems(wireFine, RedAlloy, 16)
                .inputItems(plate, Polyethylene, 4)
                .outputItems(TOOL_DATA_STICK)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(100).EUt(90).save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("data_orb_cb")
                .inputItems(ADVANCED_CIRCUIT_BOARD)
                .inputItems(CustomTags.HV_CIRCUITS, 2)
                .inputItems(ADVANCED_RAM_CHIP, 1)
                .inputItems(NOR_MEMORY_CHIP, 32)
                .inputItems(NAND_MEMORY_CHIP, 64)
                .inputItems(wireFine, Platinum, 32)
                .outputItems(TOOL_DATA_ORB)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(100).EUt(1200).save(provider);

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder("data_module_cb")
                .inputItems(WETWARE_PRINTED_CIRCUIT_BOARD)
                .inputItems(CustomTags.ZPM_CIRCUITS, 2)
                .inputItems(ADVANCED_RAM_CHIP, 8)
                .inputItems(NOR_MEMORY_CHIP, 64)
                .inputItems(NAND_MEMORY_CHIP, 64)
                .inputItems(wireFine, YttriumBariumCuprate, 32)
                .outputItems(TOOL_DATA_MODULE)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .duration(100).EUt(38400).save(provider);
    }
}
