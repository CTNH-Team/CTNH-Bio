package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.*;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class CBRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        DecomposerRecipes.init(provider);
        BasicLivingRecipes.init(provider);
        GreatFleshRecipes.init(provider);
        BioelectrlcForgeRecipes.init(provider);
        BioReactorRecipes.init(provider);
        ThinkingRecipes.init(provider);

        //小化反
        CBRecipeBuilder.of(CTNHBio.id("fenton_reagent_mixing"), GTRecipeTypes.CHEMICAL_RECIPES)
                .inputFluids(GTMaterials.Iron2Chloride, 1000)
                .inputFluids(HydrogenPeroxide, 1000)
                .outputFluids(new FluidStack(CBMaterials.FENTONS_REAGENT.getFluid(),2000))
                .duration(100)
                .EUt(1920)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("polypyrrole_from_fenton"), GTRecipeTypes.CHEMICAL_RECIPES)
                .inputFluids(new FluidStack(CBMaterials.PYRROLE.getFluid(), 1000))
                .inputFluids(new FluidStack(CBMaterials.FENTONS_REAGENT.getFluid(), 1000))
                .outputFluids(new FluidStack(CBMaterials.POLYPYRROLE.getFluid(), 1000))
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
                .outputFluids(new FluidStack(CBMaterials.FENTONS_REAGENT.getFluid(),2000))
                .duration(100)
                .EUt(1920)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("polypyrrole_from_fenton"), GTRecipeTypes.LARGE_CHEMICAL_RECIPES)
                .inputFluids(new FluidStack(CBMaterials.PYRROLE.getFluid(), 1000))
                .inputFluids(new FluidStack(CBMaterials.FENTONS_REAGENT.getFluid(), 1000))
                .outputFluids(new FluidStack(CBMaterials.POLYPYRROLE.getFluid(), 1000))
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
                .outputFluids(new FluidStack(CBMaterials.PYRROLE.getFluid(), 800))
                .duration(120)
                .EUt(384)
                .save(provider);
    }
}
