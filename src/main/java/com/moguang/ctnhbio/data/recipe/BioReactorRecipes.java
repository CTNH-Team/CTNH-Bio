package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.moguang.ctnhbio.data.materials.CommonMaterials.WEIRD_PIXEL_DUST;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;

public class BioReactorRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
// ORGANIC_COMPOUND
        CBRecipeBuilder.of(CTNHBio.id("organic_compound"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(2)
                .effect()
                .inputFluids(new FluidStack(Bile.getFluid(), 200))
                .inputItems(ModItems.ORGANIC_MATTER.get().asItem().getDefaultInstance())
                .inputItems(ModItems.NUTRIENTS.get().asItem().getDefaultInstance())
                .outputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
                .EUt(96)
                .duration(2 * 20)
                .save(provider);

        // EXOTIC_COMPOUND
        CBRecipeBuilder.of(CTNHBio.id("exotic_compound"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(4)
                .inputItems(ModItems.EXOTIC_DUST.get().asItem().getDefaultInstance(), 2)
                .inputItems(TagPrefix.dust,Steel)
                .inputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 100))
                .EUt(96)
                .duration(4 * 20)
                .save(provider);

        // GENETIC_COMPOUND
        CBRecipeBuilder.of(CTNHBio.id("genetic_compound"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(4)
                .inputFluids(new FluidStack(Endocrine_Hormone.getFluid(), 100))
                .inputItems(TagPrefix.dust,WEIRD_PIXEL_DUST)
                .inputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .EUt(96)
                .duration(4 * 20)
                .save(provider);

        // UNSTABLE_COMPOUND
        CBRecipeBuilder.of(CTNHBio.id("unstable_compound"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(4)
                .inputFluids(new FluidStack(Mutagenic_Secretion.getFluid(), 100))
                .inputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Unstable_Compound.getFluid(), 100))
                .EUt(96)
                .duration(4 * 20)
                .save(provider);

        // HEALING_ADDITIVE
        CBRecipeBuilder.of(CTNHBio.id("healing_additive"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(2)
                .inputFluids(new FluidStack(Regenerate_Fluid.getFluid(), 200))
                .inputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .EUt(96)
                .duration(4 * 20)
                .save(provider);

        // DECAYING_ADDITIVE
        CBRecipeBuilder.of(CTNHBio.id("decaying_additive"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(2)
                .inputFluids(new FluidStack(Wither_Slime.getFluid(), 200))
                .inputFluids(new FluidStack(Organic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Decay_Essence.getFluid(), 100))
                .EUt(96)
                .duration(4 * 20)
                .save(provider);

        // INSOMNIA_CURE
        CBRecipeBuilder.of(CTNHBio.id("insomnia_cure"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .inputItems(Items.SUGAR.getDefaultInstance())
                .inputFluids(new FluidStack(Bile.getFluid(), 100))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Insomnia_Cure.getFluid(), 100))
                .EUt(384)
                .duration(8 * 20)
                .save(provider);

        // ABSORPTION_BOOST
        CBRecipeBuilder.of(CTNHBio.id("absorption_boost"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .inputItems(ModItems.EXOTIC_DUST.get().asItem().getDefaultInstance())
                .inputItems(TagPrefix.dust,Steel)
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Absorption_Boost.getFluid(), 100))
                .EUt(384)
                .duration(8 * 20)
                .save(provider);

        // CLEANSING_SERUM
        CBRecipeBuilder.of(CTNHBio.id("cleansing_serum"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .inputFluids(new FluidStack(Decay_Essence.getFluid(), 100))
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Cleansing_Serum.getFluid(), 100))
                .EUt(384)
                .duration(8 * 20)
                .save(provider);

        // FRENZY_SERUM
        CBRecipeBuilder.of(CTNHBio.id("frenzy_serum"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .inputFluids(new FluidStack(Mutagenic_Secretion.getFluid(), 100))
                .inputFluids(new FluidStack(Endocrine_Hormone.getFluid(), 100))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Frenzy_Serum.getFluid(), 100))
                .EUt(384)
                .duration(8 * 20)
                .save(provider);

        // BREEDING_STIMULANT
        CBRecipeBuilder.of(CTNHBio.id("breeding_stimulant"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(6)
                .inputItems(TagPrefix.dust,WEIRD_PIXEL_DUST)
                .inputItems(ItemTags.FLOWERS)
                .inputFluids(new FluidStack(Endocrine_Hormone.getFluid(), 100))
                .inputItems(Items.COCOA_BEANS.getDefaultInstance())
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Breeding_Stimulant.getFluid(), 100))
                .EUt(384)
                .duration(6 * 20)
                .save(provider);

        // REJUVENATION_SERUM
        CBRecipeBuilder.of(CTNHBio.id("rejuvenation_serum"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .inputItems(TagPrefix.dust,WEIRD_PIXEL_DUST)
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .inputFluids(new FluidStack(Decay_Essence.getFluid(), 100))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Rejuvenation_Serum.getFluid(), 100))
                .EUt(384)
                .duration(8 * 20)
                .save(provider);

        // AGEING_SERUM
        CBRecipeBuilder.of(CTNHBio.id("ageing_serum"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(6)
                .inputItems(TagPrefix.dust,WEIRD_PIXEL_DUST)
                .inputItems(TagPrefix.dust,Steel)
                .inputFluids(new FluidStack(Decay_Essence.getFluid(), 100))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Ageing_Serum.getFluid(), 100))
                .EUt(384)
                .duration(6 * 20)
                .save(provider);

        // ENLARGEMENT_SERUM
        CBRecipeBuilder.of(CTNHBio.id("enlargement_serum"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(6)
                .inputItems(TagPrefix.dust,WEIRD_PIXEL_DUST)
                .inputFluids(new FluidStack(Endocrine_Hormone.getFluid(), 100))
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .inputItems(TagPrefix.dust,Steel)
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .outputFluids(new FluidStack(Enlargement_Serum.getFluid(), 100))
                .EUt(384)
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
                .EUt(384)
                .duration(8 * 20)
                .save(provider);

        //鲜肉增殖-初级配方
        CBRecipeBuilder.of(CTNHBio.id("living_flesh_recipe_one"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .effect(MobEffects.MOVEMENT_SPEED)
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
        //鲜肉增殖-中级配方
        CBRecipeBuilder.of(CTNHBio.id("living_flesh_recipe_two"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .effect(MobEffects.MOVEMENT_SPEED)
                .notConsumable(CBItems.SYNET_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance())
                .inputItems(ModItems.CREATOR_MIX.get().asItem().getDefaultInstance())
                .inputFluids(new FluidStack(Regenerate_Fluid.getFluid(), 400))
                .outputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(),2)
                .chancedOutput(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(), 7000, 0)
                .chancedOutput(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(), 4000, 0)
                .EUt(120)
                .duration(8 * 20)
                .save(provider);
        //鲜肉增殖-高级配方
        CBRecipeBuilder.of(CTNHBio.id("living_flesh_recipe_three"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(8)
                .effect(MobEffects.MOVEMENT_SPEED)
                .notConsumable(CBItems.META_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance())
                .inputItems(ModItems.CREATOR_MIX.get().asItem().getDefaultInstance())
                .inputFluids(new FluidStack(Regenerate_Fluid.getFluid(), 500))
                .outputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(),4)
                .chancedOutput(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(), 6000, 0)
                .chancedOutput(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(), 4000, 0)
                .EUt(384)
                .duration(8 * 20)
                .save(provider);

        //怪异肉循环-初级配方
        CBRecipeBuilder.of(CTNHBio.id("creator_mix_recipe_one"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(5)
                .effect(MobEffects.MOVEMENT_SPEED)
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
        //怪异肉循环-中级配方
        CBRecipeBuilder.of(CTNHBio.id("creator_mix_recipe_two"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(5)
                .effect(MobEffects.MOVEMENT_SPEED)
                .notConsumable(CBItems.SYNET_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance())
                .inputItems(ModItems.NUTRIENTS.get().asItem().getDefaultInstance(),10)
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 300))
                .outputItems(ModItems.CREATOR_MIX.get().asItem().getDefaultInstance(),1)
                .chancedOutput(ModItems.CREATOR_MIX.get().asItem().getDefaultInstance(), 8000, 0)
                .chancedOutput(ModItems.CREATOR_MIX.get().asItem().getDefaultInstance(), 5000, 0)
                .EUt(384)
                .duration(4 * 20)
                .save(provider);
        //怪异肉循环-高级配方
        CBRecipeBuilder.of(CTNHBio.id("creator_mix_recipe_three"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(5)
                .effect(MobEffects.MOVEMENT_SPEED)
                .notConsumable(CBItems.META_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance())
                .inputItems(ModItems.NUTRIENTS.get().asItem().getDefaultInstance(),10)
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 400))
                .outputItems(ModItems.CREATOR_MIX.get().asItem().getDefaultInstance(),2)
                .chancedOutput(ModItems.CREATOR_MIX.get().asItem().getDefaultInstance(), 8000, 0)
                .chancedOutput(ModItems.CREATOR_MIX.get().asItem().getDefaultInstance(), 6000, 0)
                .EUt(1960)
                .duration(4 * 20)
                .save(provider);
        //原初核心循环-脉络核心
        CBRecipeBuilder.of(CTNHBio.id("primordial_core_synet"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(10)
                .effect(MobEffects.MOVEMENT_SPEED)
                .notConsumable(CBItems.SYNET_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(),1)
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 50))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 50))
                .inputFluids(new FluidStack(Unstable_Compound.getFluid(), 50))
                .outputItems(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance(),1)
                .chancedOutput(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance(), 6000, 0)
                .chancedOutput(CBItems.SYNET_CORE.get().asItem().getDefaultInstance(), 1000, 0)
                .EUt(384)
                .duration(10 * 20)
                .save(provider);
        //原初核心循环-拓扑核心
        CBRecipeBuilder.of(CTNHBio.id("primordial_core_meta"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(10)
                .effect(MobEffects.MOVEMENT_SPEED)
                .notConsumable(CBItems.META_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(),1)
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .inputFluids(new FluidStack(Unstable_Compound.getFluid(), 100))
                .outputItems(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance(),1)
                .chancedOutput(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance(), 9500, 0)
                .chancedOutput(CBItems.META_CORE.get().asItem().getDefaultInstance(), 1200, 0)
                .EUt(1960)
                .duration(10 * 20)
                .save(provider);
        //原初核心循环-灵蜕核心
        CBRecipeBuilder.of(CTNHBio.id("primordial_core_nova"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(10)
                .effect(MobEffects.MOVEMENT_SPEED)
                .notConsumable(CBItems.NOVA_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(),1)
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 150))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 150))
                .inputFluids(new FluidStack(Unstable_Compound.getFluid(), 150))
                .outputItems(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance(),2)
                .chancedOutput(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance(), 7000, 0)
                .chancedOutput(CBItems.NOVA_CORE.get().asItem().getDefaultInstance(), 1400, 0)
                .EUt(1960)
                .duration(10 * 20)
                .save(provider);
        //原初核心循环-终观核心
        CBRecipeBuilder.of(CTNHBio.id("primordial_core_omni"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(10)
                .effect(MobEffects.MOVEMENT_SPEED)
                .notConsumable(CBItems.OMNI_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance())
                .inputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance(),1)
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 200))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 200))
                .inputFluids(new FluidStack(Unstable_Compound.getFluid(), 200))
                .outputItems(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance(),3)
                .chancedOutput(ModItems.PRIMORDIAL_CORE.get().asItem().getDefaultInstance(), 9000, 0)
                .chancedOutput(CBItems.OMNI_CORE.get().asItem().getDefaultInstance(), 1700, 0)
                .EUt(6144)
                .duration(10 * 20)
                .save(provider);
        //印刷湿件电路基板
        CBRecipeBuilder.of(CTNHBio.id("primordial_core_omni"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .nutrient(10)
                .inputItems(CBItems.WETWARE_CIRCUIT_BOARD.get().asItem().getDefaultInstance(),4)
                .inputItems(TagPrefix.foil,VanadiumGallium,32)
                .inputFluids(new FluidStack(Iron3Chloride.getFluid(), 5000))
                .outputItemsRanged(new ItemStack(CBItems.WETWARE_PRINTED_CIRCUIT_BOARD.get()), UniformInt.of(4, 6))
                .EUt(6144)
                .duration(2 * 20)
                .save(provider);

    }
}