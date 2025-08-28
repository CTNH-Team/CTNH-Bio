package com.moguang.ctnhbio.data.recipe.multi;

import com.github.elenterius.biomancy.init.ModFluids;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import dev.architectury.platform.Mod;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.IV;
import static com.moguang.ctnhbio.data.materials.CommonMaterials.BLOODSTEEL;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.Ageing_Serum;

public class GreatFleshRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CBRecipeBuilder.of(CTNHBio.id("cogni_assembler"), CBRecipeTypes.GREAT_FLESH)
                .nutrient(100)
                .inputItems(CustomTags.LuV_CIRCUITS,4)
                .inputItems(CBItems.OMNI_CORE,2)
                .inputItems(GTItems.QUBIT_CENTRAL_PROCESSING_UNIT,16)
                .inputItems(TagPrefix.plateDense,BLOODSTEEL,6)
                .inputItems(TagPrefix.plateDense,BLOODSTEEL,6)
                .inputItems(ModItems.CREATOR_MIX,8)
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:unstable_compound")),64000))
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:genetic_compound")),64000))
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:heterogeneous_compound")),64000))
                .duration(1000)
                .EUt(GTValues.V[GTValues.UV])
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("circulatory_system"), CBRecipeTypes.GREAT_FLESH)
                .nutrient(100)
                .inputItems(CustomTags.LuV_CIRCUITS,4)
                .inputItems(CBItems.NOVA_CORE,2)
                .inputItems(Items.ENCHANTED_GOLDEN_APPLE,8)
                .inputItems(TagPrefix.plateDense,BLOODSTEEL,6)
                .inputItems(TagPrefix.plateDense,BLOODSTEEL,6)
                .inputItems(ModItems.CREATOR_MIX,8)
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:organic_compound")),64000))
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:wither_slime")),64000))
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:healing_compound")),64000))
                .duration(1000)
                .EUt(GTValues.V[GTValues.UV])
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("weatherer"), CBRecipeTypes.GREAT_FLESH)
                .nutrient(100)
                .inputItems(GTMachines.WORLD_ACCELERATOR[IV],4)
                .inputItems(CBItems.META_CORE,2)
                .inputItems(GTItems.COMPONENT_GRINDER_TUNGSTEN,8)
                .inputItems(TagPrefix.plateDense,BLOODSTEEL,6)
                .inputItems(TagPrefix.plateDense,BLOODSTEEL,6)
                .inputItems(ModItems.CREATOR_MIX,8)
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("biomancy:acid")),64000))
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:decay_essence")),64000))
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:bile")),64000))
                .duration(1000)
                .EUt(GTValues.V[GTValues.UV])
                .save(provider);

    }
}
