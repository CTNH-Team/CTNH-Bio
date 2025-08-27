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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

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
        //装配线
        CBRecipeBuilder.of(CTNHBio.id("omni_core_recipe"), ASSEMBLY_LINE_RECIPES)
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance())
                .inputItems(frameGt,TungstenSteel)
                .inputItems(spring, Platinum, 2)
                .inputItems(GTItems.ENERGIUM_CRYSTAL, 4)
                .inputItems(CustomTags.LuV_CIRCUITS,2)
                .inputItems(Items.GLOW_BERRIES.getDefaultInstance(),4)
                .inputItems(ModItems.BIO_LUMENS.get().getDefaultInstance(),40)
                .inputItems(ModItems.NUTRIENTS.get().getDefaultInstance(),40)
                .inputFluids(OrganicMaterials.Heterogeneous_Compound, 500)
                .inputFluids(OrganicMaterials.Rejuvenation_Serum, 500)
                .inputFluids(OrganicMaterials.Cleansing_Serum, 500)
                .inputFluids(OrganicMaterials.Frenzy_Serum, 500)
                .outputItems(OMNI_CORE,2)
//                .scannerResearch(b -> b
//                        .researchStack(NOVA_CORE.asStack(1))
//                        .duration(1200)
//                        .EUt(VA[IV]))
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
//                .scannerResearch(b -> b
//                        .researchId("flesh_bits_ccb")
//                        .researchStack(new ItemStack(ModItems.FLESH_BITS.get()))
//                        .EUt(VA[EV]))
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
//                .scannerResearch(b -> b
//                        .researchStack(GTMachines.BREWERY[LuV].asStack(1))
//                        .duration(1200)
//                        .EUt(VA[IV]))
                .duration(1800)
                .EUt(24000)
                .save(provider);
        //研磨
        CBRecipeBuilder.of(CTNHBio.id("weird_pixel_dust_recipe"), GTRecipeTypes.MACERATOR_RECIPES)
                .inputItems(ModItems.NUTRIENT_PASTE,1)
                .outputItemsRanged(dust,WEIRD_PIXEL_DUST, UniformInt.of(1, 3))
                .EUt(24)
                .duration(100)
                .save(provider);
        //搅拌
        CBRecipeBuilder.of(CTNHBio.id("weird_pixel_dust_recipe"), GTRecipeTypes.MIXER_RECIPES)
                .circuitMeta(5)
                .inputItems(dust,Iron,3)
                .inputItems(dust,Chromium)
                .inputItems(dust,Vanadium)
                .inputItems(dust,Neodymium)
                .inputItems(ModItems.CREATOR_MIX,2)
                .outputItemsRanged(dust,BLOODSTEEL, UniformInt.of(8, 9))
                .EUt(6144)
                .duration(400)
                .save(provider);
    }
}
