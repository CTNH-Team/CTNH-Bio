package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;

public class BioReactorRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
// ORGANIC_COMPOUND
        CBRecipeBuilder.of(CTNHBio.id("organic_compound"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(2)
                .inputFluids(new FluidStack(Bile.getFluid(), 200))
                .inputItems(ModItems.ORGANIC_MATTER.get().asItem().getDefaultInstance())
                .inputItems(ModItems.NUTRIENTS.get().asItem().getDefaultInstance())
                .outputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
                .EUt(32)
                .duration(2 * 20)
                .save(provider);

        // EXOTIC_COMPOUND
        CBRecipeBuilder.of(CTNHBio.id("exotic_compound"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(4)
                .inputItems(ModItems.EXOTIC_DUST.get().asItem().getDefaultInstance(), 2)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().asItem().getDefaultInstance())
                .inputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 100))
                .EUt(32)
                .duration(4 * 20)
                .save(provider);

        // GENETIC_COMPOUND
        CBRecipeBuilder.of(CTNHBio.id("genetic_compound"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(4)
                .inputFluids(new FluidStack(Endocrine_Hormone.getFluid(), 100))
                .inputItems(ModItems.NUTRIENT_PASTE.get().asItem().getDefaultInstance())
                .inputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .EUt(32)
                .duration(4 * 20)
                .save(provider);

        // UNSTABLE_COMPOUND
        CBRecipeBuilder.of(CTNHBio.id("unstable_compound"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(4)
                .inputFluids(new FluidStack(Mutagenic_Secretion.getFluid(), 100))
                .inputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Unstable_Compound.getFluid(), 100))
                .EUt(32)
                .duration(4 * 20)
                .save(provider);

        // HEALING_ADDITIVE
        CBRecipeBuilder.of(CTNHBio.id("healing_additive"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(2)
                .inputFluids(new FluidStack(Regenerate_Fluid.getFluid(), 200))
                .inputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .EUt(32)
                .duration(2 * 20)
                .save(provider);

        // DECAYING_ADDITIVE
        CBRecipeBuilder.of(CTNHBio.id("decaying_additive"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(2)
                .inputFluids(new FluidStack(Wither_Slime.getFluid(), 200))
                .inputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Decay_Essence.getFluid(), 100))
                .EUt(32)
                .duration(2 * 20)
                .save(provider);

        // INSOMNIA_CURE
        CBRecipeBuilder.of(CTNHBio.id("insomnia_cure"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .inputItems(Items.SUGAR.getDefaultInstance())
                .inputFluids(new FluidStack(Bile.getFluid(), 100))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Insomnia_Cure.getFluid(), 100))
                .EUt(32)
                .duration(8 * 20)
                .save(provider);

        // ABSORPTION_BOOST
        CBRecipeBuilder.of(CTNHBio.id("absorption_boost"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .inputItems(ModItems.EXOTIC_DUST.get().asItem().getDefaultInstance())
                .inputItems(ModItems.MINERAL_FRAGMENT.get().asItem().getDefaultInstance())
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Absorption_Boost.getFluid(), 100))
                .EUt(32)
                .duration(8 * 20)
                .save(provider);

        // CLEANSING_SERUM
        CBRecipeBuilder.of(CTNHBio.id("cleansing_serum"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .inputFluids(new FluidStack(Decay_Essence.getFluid(), 100))
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Cleansing_Serum.getFluid(), 100))
                .EUt(32)
                .duration(8 * 20)
                .save(provider);

        // FRENZY_SERUM
        CBRecipeBuilder.of(CTNHBio.id("frenzy_serum"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .inputFluids(new FluidStack(Mutagenic_Secretion.getFluid(), 100))
                .inputFluids(new FluidStack(Endocrine_Hormone.getFluid(), 100))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Frenzy_Serum.getFluid(), 100))
                .EUt(32)
                .duration(8 * 20)
                .save(provider);

        // BREEDING_STIMULANT
        CBRecipeBuilder.of(CTNHBio.id("breeding_stimulant"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(6)
                .inputItems(ModItems.NUTRIENT_PASTE.get().asItem().getDefaultInstance())
                .inputItems(ItemTags.FLOWERS)
                .inputFluids(new FluidStack(Endocrine_Hormone.getFluid(), 100))
                .inputItems(Items.COCOA_BEANS.getDefaultInstance())
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Breeding_Stimulant.getFluid(), 100))
                .EUt(32)
                .duration(6 * 20)
                .save(provider);

        // REJUVENATION_SERUM
        CBRecipeBuilder.of(CTNHBio.id("rejuvenation_serum"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .inputItems(ModItems.NUTRIENT_PASTE.get().asItem().getDefaultInstance())
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .inputFluids(new FluidStack(Decay_Essence.getFluid(), 100))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Rejuvenation_Serum.getFluid(), 100))
                .EUt(32)
                .duration(8 * 20)
                .save(provider);

        // AGEING_SERUM
        CBRecipeBuilder.of(CTNHBio.id("ageing_serum"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(6)
                .inputItems(ModItems.NUTRIENT_PASTE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.MINERAL_FRAGMENT.get().asItem().getDefaultInstance())
                .inputFluids(new FluidStack(Decay_Essence.getFluid(), 100))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Ageing_Serum.getFluid(), 100))
                .EUt(32)
                .duration(6 * 20)
                .save(provider);

        // ENLARGEMENT_SERUM
        CBRecipeBuilder.of(CTNHBio.id("enlargement_serum"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(6)
                .inputItems(ModItems.NUTRIENT_PASTE.get().asItem().getDefaultInstance())
                .inputFluids(new FluidStack(Endocrine_Hormone.getFluid(), 100))
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .inputItems(ModItems.MINERAL_FRAGMENT.get().asItem().getDefaultInstance())
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Enlargement_Serum.getFluid(), 100))
                .EUt(32)
                .duration(6 * 20)
                .save(provider);

        // SHRINKING_SERUM
        CBRecipeBuilder.of(CTNHBio.id("shrinking_serum"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .inputItems(ModItems.EXOTIC_DUST.get().asItem().getDefaultInstance())
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .inputFluids(new FluidStack(Decay_Essence.getFluid(), 200))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Shrinking_Serum.getFluid(), 100))
                .EUt(32)
                .duration(8 * 20)
                .save(provider);

        //鲜肉增殖-初级配方
        CBRecipeBuilder.of(CTNHBio.id("living_flesh_recipe_one"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .notConsumable(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance())
                .inputItems(ModItems.CREATOR_MIX.get().asItem().getDefaultInstance())
                .inputFluids(new FluidStack(Regenerate_Fluid.getFluid(), 300))
                .outputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(),2)
                .chancedOutput(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(), 3000, 0)
                .chancedOutput(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(), 1000, 0)
                .EUt(30)
                .duration(8 * 20)
                .save(provider);

        //怪异肉循环-初级配方
        CBRecipeBuilder.of(CTNHBio.id("creator_mix_recipe_one"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(5)
                .notConsumable(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance())
                .inputItems(ModItems.NUTRIENTS.get().asItem().getDefaultInstance(),5)
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 200))
                .outputItems(ModItems.CREATOR_MIX.get().asItem().getDefaultInstance(),1)
                .chancedOutput(ModItems.CREATOR_MIX.get().asItem().getDefaultInstance(), 4000, 0)
                .chancedOutput(ModItems.CREATOR_MIX.get().asItem().getDefaultInstance(), 2000, 0)
                .EUt(120)
                .duration(4 * 20)
                .save(provider);
    }
}