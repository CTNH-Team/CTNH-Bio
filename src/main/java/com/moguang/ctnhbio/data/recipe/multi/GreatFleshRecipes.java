package com.moguang.ctnhbio.data.recipe.multi;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.ingredient.fluid.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;

import com.github.elenterius.biomancy.init.ModFluids;
import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBRecipeTypes;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.IV;
import static com.moguang.ctnhbio.data.materials.CommonMaterials.BLOODSTEEL;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;

public class GreatFleshRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        CBRecipeBuilder.of(CTNHBio.id("cogni_assembler"), CBRecipeTypes.GREAT_FLESH)
                .nutrient(100)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .inputItems(CBItems.OMNI_CORE, 2)
                .inputItems(GTItems.QUBIT_CENTRAL_PROCESSING_UNIT, 16)
                .inputItems(TagPrefix.plateDense, BLOODSTEEL, 6)
                .inputItems(TagPrefix.plateDense, BLOODSTEEL, 6)
                .inputItems(ModItems.CREATOR_MIX, 8)
                .inputFluids(new FluidStack(Unstable_Compound.getFluid(), 6000))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 6000))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 6000))
                .duration(1000)
                .EUt(GTValues.V[GTValues.ZPM])
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("circulatory_system"), CBRecipeTypes.GREAT_FLESH)
                .nutrient(100)
                .inputItems(CustomTags.LuV_CIRCUITS, 4)
                .inputItems(CBItems.NOVA_CORE, 2)
                .inputItems(Items.ENCHANTED_GOLDEN_APPLE, 8)
                .inputItems(TagPrefix.plateDense, BLOODSTEEL, 6)
                .inputItems(TagPrefix.plateDense, BLOODSTEEL, 6)
                .inputItems(ModItems.CREATOR_MIX, 8)
                .inputFluids(new FluidStack(Organic_Compound.getFluid(), 6000))
                .inputFluids(new FluidStack(Withering_Ooze.getFluid(), 6000))
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 6000))
                .duration(1000)
                .EUt(GTValues.V[GTValues.ZPM])
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("weatherer"), CBRecipeTypes.GREAT_FLESH)
                .nutrient(100)
                .inputItems(GTMachines.WORLD_ACCELERATOR[IV], 4)
                .inputItems(CBItems.META_CORE, 2)
                .inputItems(GTItems.COMPONENT_GRINDER_TUNGSTEN, 8)
                .inputItems(TagPrefix.plateDense, BLOODSTEEL, 6)
                .inputItems(TagPrefix.plateDense, BLOODSTEEL, 6)
                .inputItems(ModItems.CREATOR_MIX, 8)
                .inputFluids(FluidIngredient.of(ModFluids.ACID.get(), 6000))
                .inputFluids(new FluidStack(Decay_Essence.getFluid(), 6000))
                .inputFluids(new FluidStack(Bile.getFluid(), 6000))
                .duration(1000)
                .EUt(GTValues.V[GTValues.ZPM])
                .save(provider);
        // .inputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
    }
}
