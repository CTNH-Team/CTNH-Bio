package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;

public class DecomposerRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        //分解者
        CBRecipeBuilder.of(CTNHBio.id("grass_block"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GRASS_BLOCK.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dirt"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DIRT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("coarse_dirt"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.COARSE_DIRT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("podzol"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.PODZOL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("rooted_dirt"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.ROOTED_DIRT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("sand"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.SAND.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 3))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("red_sand"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.RED_SAND.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 3))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("gravel"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GRAVEL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(3, 6))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("sponge"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.SPONGE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("sea_pickle"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.SEA_PICKLE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("lily_of_the_valley"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.LILY_OF_THE_VALLEY.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .outputFluidsRanged(new FluidStack(Toxin_Extract.getFluid(), 1), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("oxeye_daisy"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.OXEYE_DAISY.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("wither_rose"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.WITHER_ROSE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 2))
                .outputFluidsRanged(new FluidStack(Wither_Slime.getFluid(), 1), UniformInt.of(3, 5))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("spore_blossom"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.SPORE_BLOSSOM.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(3, 5))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("brown_mushroom"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.BROWN_MUSHROOM.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("red_mushroom"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.RED_MUSHROOM.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("crimson_fungus"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.CRIMSON_FUNGUS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("warped_fungus"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.WARPED_FUNGUS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("crimson_roots"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.CRIMSON_ROOTS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("warped_roots"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.WARPED_ROOTS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("nether_sprouts"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.NETHER_SPROUTS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("sugar_cane"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.SUGAR_CANE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(Items.SUGAR), UniformInt.of(1, 2))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("kelp"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.KELP.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("bamboo"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.BAMBOO.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("chorus_flower"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.CHORUS_FLOWER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(2, 5))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("clay"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.CLAY.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("glowstone"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GLOWSTONE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 4))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 4))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("glow_lichen"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GLOW_LICHEN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dragon_egg"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DRAGON_EGG.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(97, 128))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(6, 10))
                .outputFluidsRanged(new FluidStack(Endocrine_Hormone.getFluid(), 1), UniformInt.of(1700, 2300))
                .EUt(32)
                .duration(7080)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("turtle_egg"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.TURTLE_EGG.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Endocrine_Hormone.getFluid(), 1), UniformInt.of(100, 500))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("tube_coral"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.TUBE_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(100, 250))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("brain_coral"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.BRAIN_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(100, 250))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("bubble_coral"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.BUBBLE_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(100, 250))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("fire_coral"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.FIRE_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(100, 250))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("horn_coral"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.HORN_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(100, 250))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_brain_coral"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DEAD_BRAIN_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_bubble_coral"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DEAD_BUBBLE_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_fire_coral"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DEAD_FIRE_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_horn_coral"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DEAD_HORN_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_tube_coral"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DEAD_TUBE_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("tube_coral_fan"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.TUBE_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(100, 250))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("brain_coral_fan"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.BRAIN_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(100, 250))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("bubble_coral_fan"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.BUBBLE_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(100, 250))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("fire_coral_fan"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.FIRE_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(100, 250))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("horn_coral_fan"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.HORN_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(100, 250))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_tube_coral_fan"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DEAD_TUBE_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_brain_coral_fan"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DEAD_BRAIN_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_bubble_coral_fan"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DEAD_BUBBLE_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_fire_coral_fan"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DEAD_FIRE_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_horn_coral_fan"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DEAD_HORN_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("redstone"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.REDSTONE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("turtle_helmet"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.TURTLE_HELMET.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(15, 25))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(9, 15))
                .EUt(32)
                .duration(400)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("scute"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.SCUTE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("apple"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.APPLE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("diamond"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DIAMOND.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(4, 8))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("emerald"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.EMERALD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(5, 9))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("lapis_lazuli"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.LAPIS_LAZULI.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("quartz"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.QUARTZ.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("amethyst_shard"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.AMETHYST_SHARD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(3, 5))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("raw_iron"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.RAW_IRON.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("iron_ingot"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.IRON_INGOT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("raw_copper"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.RAW_COPPER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("copper_ingot"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.COPPER_INGOT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("raw_gold"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.RAW_GOLD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("gold_ingot"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GOLD_INGOT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("netherite_ingot"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.NETHERITE_INGOT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(43, 72))
                .EUt(32)
                .duration(600)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("netherite_scrap"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.NETHERITE_SCRAP.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("string"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.STRING.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("feather"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.FEATHER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("flint"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.FLINT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("porkchop"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.PORKCHOP.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(2, 3))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("golden_apple"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(4)
                .inputItems(Items.GOLDEN_APPLE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(37, 63))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(4, 6))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(300, 600))
                .EUt(32)
                .duration(2840)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("enchanted_golden_apple"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(5)
                .inputItems(Items.ENCHANTED_GOLDEN_APPLE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(43, 72))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(1200, 2000))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(6, 10))
                .EUt(32)
                .duration(4080)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("leather"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.LEATHER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(1, 4))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("clay_ball"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.CLAY_BALL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("slime_ball"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.SLIME_BALL.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(200, 300))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(100, 200))
                .EUt(32)
                .duration(140)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("slime_block"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(3)
                .inputItems(Items.SLIME_BLOCK.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(1800, 2700))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(1000, 1800))
                .EUt(32)
                .duration(1300)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("magma_cream"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.MAGMA_CREAM.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(100, 200))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(100, 300))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 3))
                .outputFluidsRanged(new FluidStack(Mutagenic_Secretion.getFluid(), 1), UniformInt.of(1, 200))
                .EUt(32)
                .duration(240)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("egg"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.EGG.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Endocrine_Hormone.getFluid(), 1), UniformInt.of(1, 100))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("glowstone_dust"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GLOWSTONE_DUST.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("cod"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.COD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("salmon"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.SALMON.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("tropical_fish"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.TROPICAL_FISH.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("pufferfish"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.PUFFERFISH.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .outputFluidsRanged(new FluidStack(Toxin_Extract.getFluid(), 1), UniformInt.of(100, 300))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("ink_sac"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.INK_SAC.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(100, 200))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("glow_ink_sac"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GLOW_INK_SAC.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("cocoa_beans"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.COCOA_BEANS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("bone_meal"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.BONE_MEAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("bone"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.BONE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(3, 6))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("cake"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.CAKE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(10, 18))
                .EUt(32)
                .duration(400)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("cookie"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.COOKIE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("melon_slice"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.MELON_SLICE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dried_kelp"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DRIED_KELP.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dried_kelp_block"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.DRIED_KELP_BLOCK.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("wheat_seeds"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.WHEAT_SEEDS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("beef"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.BEEF.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(3, 6))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("chicken"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.CHICKEN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(3, 4))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(2, 3))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("rotten_flesh"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.ROTTEN_FLESH.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(1, 3))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("ender_pearl"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.ENDER_PEARL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(2, 3))
                .EUt(32)
                .duration(240)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("blaze_rod"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.BLAZE_ROD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(2, 2))
                .EUt(32)
                .duration(240)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("blaze_powder"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.BLAZE_POWDER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("ghast_tear"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GHAST_TEAR.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Endocrine_Hormone.getFluid(), 1), UniformInt.of(400, 800))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(100, 200))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(300)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("gold_nugget"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GOLD_NUGGET.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("nether_wart"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.NETHER_WART.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("spider_eye"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.SPIDER_EYE.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(1, 100))
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("fermented_spider_eye"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.FERMENTED_SPIDER_EYE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("ender_eye"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.ENDER_EYE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(5, 6))
                .EUt(32)
                .duration(240)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("glistering_melon_slice"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GLISTERING_MELON_SLICE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(3, 6))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(2, 2))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("carrot"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.CARROT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("potato"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.POTATO.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("baked_potato"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.BAKED_POTATO.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(2, 5))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("poisonous_potato"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.POISONOUS_POTATO.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Toxin_Extract.getFluid(), 1), UniformInt.of(200, 400))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(140)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("golden_carrot"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GOLDEN_CARROT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(4, 8))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("skeleton_skull"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(Items.SKELETON_SKULL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(28, 48))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(4, 7))
                .EUt(32)
                .duration(1880)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("wither_skeleton_skull"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(Items.WITHER_SKELETON_SKULL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(28, 48))
                .outputFluidsRanged(new FluidStack(Wither_Slime.getFluid(), 1), UniformInt.of(800, 1600))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(4, 7))
                .EUt(32)
                .duration(1880)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("player_head"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(Items.PLAYER_HEAD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(19, 32))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(Items.SKELETON_SKULL), UniformInt.of(1, 1))
                .EUt(32)
                .duration(2000)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("zombie_head"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(Items.ZOMBIE_HEAD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(14, 24))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(Items.SKELETON_SKULL), UniformInt.of(1, 1))
                .EUt(32)
                .duration(2000)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("creeper_head"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(Items.CREEPER_HEAD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(19, 32))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(Items.SKELETON_SKULL), UniformInt.of(1, 1))
                .EUt(32)
                .duration(2000)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dragon_head"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DRAGON_HEAD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(50, 50))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(50, 50))
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(25, 25))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(20, 20))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(50, 50))
                .EUt(32)
                .duration(10000)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("nether_star"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.NETHER_STAR.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(50, 50))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(25, 25))
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(20, 20))
                .EUt(32)
                .duration(10000)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("prismarine_shard"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.PRISMARINE_SHARD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("prismarine_crystals"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.PRISMARINE_CRYSTALS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(1, 3))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("rabbit"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.RABBIT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(3, 6))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(2, 3))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("rabbit_foot"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.RABBIT_FOOT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 3))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("rabbit_hide"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.RABBIT_HIDE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("mutton"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.MUTTON.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(2, 3))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("chorus_fruit"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.CHORUS_FRUIT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 3))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 2))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(1, 100))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(260)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("chorus_flower"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.CHORUS_FLOWER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(2, 5))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("shulker_shell"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(Items.SHULKER_SHELL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(6, 10))
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(4, 7))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(800)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("iron_nugget"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.IRON_NUGGET.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("phantom_membrane"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.PHANTOM_MEMBRANE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(4, 7))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("nautilus_shell"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(Items.NAUTILUS_SHELL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(6, 10))
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(4, 7))
                .EUt(32)
                .duration(600)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("heart_of_the_sea"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(5)
                .inputItems(Items.HEART_OF_THE_SEA.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(8, 8))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(15, 15))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 5))
                .EUt(32)
                .duration(6000)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("glow_berries"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GLOW_BERRIES.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("shroomlight"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(Items.SHROOMLIGHT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(2, 3))
                .EUt(32)
                .duration(520)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("pointed_dripstone"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.POINTED_DRIPSTONE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(120)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("mob_fang"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.MOB_FANG.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(4, 6))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("mob_claw"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.MOB_CLAW.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(4, 6))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("mob_sinew"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.MOB_SINEW.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(4, 8))
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(180)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("mob_marrow"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.MOB_MARROW.get().asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Endocrine_Hormone.getFluid(), 1), UniformInt.of(100, 400))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(260)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("withered_mob_marrow"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.WITHERED_MOB_MARROW.get().asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Wither_Slime.getFluid(), 1), UniformInt.of(300, 500))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(260)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("generic_mob_gland"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.GENERIC_MOB_GLAND.get().asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(400, 600))
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 3))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("toxin_gland"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.TOXIN_GLAND.get().asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Toxin_Extract.getFluid(), 1), UniformInt.of(200, 500))
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 3))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("volatile_gland"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.VOLATILE_GLAND.get().asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Mutagenic_Secretion.getFluid(), 1), UniformInt.of(200, 500))
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 3))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(200)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("echo_shard"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.ECHO_SHARD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(8, 12))
                .EUt(32)
                .duration(300)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("goat_horn"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.GOAT_HORN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 7))
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(6, 8))
                .EUt(32)
                .duration(240)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("pearlescent_froglight"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(Items.PEARLESCENT_FROGLIGHT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(5, 9))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(200, 300))
                .EUt(32)
                .duration(400)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("verdant_froglight"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(Items.VERDANT_FROGLIGHT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(5, 9))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(200, 300))
                .EUt(32)
                .duration(400)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("ochre_froglight"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(Items.OCHRE_FROGLIGHT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(5, 9))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(200, 300))
                .EUt(32)
                .duration(400)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("frogspawn"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.FROGSPAWN.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(1, 100))
                .EUt(32)
                .duration(20)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("pitcher_pod"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.PITCHER_POD.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(100, 300))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 3))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(180)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("pitcher_plant"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.PITCHER_PLANT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 3))
                .outputFluidsRanged(new FluidStack(Toxin_Extract.getFluid(), 1), UniformInt.of(1, 200))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(180)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("torchflower"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.TORCHFLOWER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("torchflower_seeds"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.TORCHFLOWER_SEEDS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("sniffer_egg"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(Items.SNIFFER_EGG.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 4))
                .outputFluidsRanged(new FluidStack(Endocrine_Hormone.getFluid(), 1), UniformInt.of(100, 400))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 4))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(2, 6))
                .EUt(32)
                .duration(320)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("piglin_head"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(Items.PIGLIN_HEAD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(22, 36))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(8, 12))
                .outputItemsRanged(new ItemStack(Items.SKELETON_SKULL), UniformInt.of(1, 1))
                .EUt(32)
                .duration(2000)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("living_flesh"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(3, 6))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(180)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("primal_orifice"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.PRIMAL_ORIFICE.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(1, 2))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(100, 400))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("primal_bloom"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.PRIMAL_BLOOM.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(2, 3))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(100, 300))
                .EUt(32)
                .duration(220)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("nutrient_paste"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.NUTRIENT_PASTE.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.NUTRIENTS.get()), UniformInt.of(5, 5))
                .outputItemsRanged(new ItemStack(ModItems.ORGANIC_MATTER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(120)
                .save(provider);

        registerRawOreDecompositionRecipes(provider);

    }

    private static void registerRawOreDecompositionRecipes(Consumer<FinishedRecipe> provider) {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasProperty(PropertyKey.ORE) &&
                    !ChemicalHelper.get(TagPrefix.rawOre, material).isEmpty()) {
                addRawOreRecipe(provider, material);
            }
        }
    }

    private static void addRawOreRecipe(Consumer<FinishedRecipe> provider, Material material) {
        //洗矿-热离-粉碎
        OreProperty property = material.getProperty(PropertyKey.ORE);
        ItemStack finalDust = ChemicalHelper.get(TagPrefix.dust, material);
        if (finalDust.isEmpty()) return;
        CBRecipeBuilder.of(CTNHBio.id("decompose_raw_" + material.getName()),
                        CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(3)
                .inputItems(TagPrefix.crushed, material)
                .inputFluids(new FluidStack(Fluids.WATER, 1500))
                .outputItems(finalDust)
                .chancedOutput(
                        ChemicalHelper.get(TagPrefix.dust, property.getOreByProduct(0, material)),
                        1400,
                        0
                )
                .chancedOutput(
                        ChemicalHelper.get(TagPrefix.dust, property.getOreByProduct(1, material)),
                        3300,
                        0
                )
                .chancedOutput(
                        ChemicalHelper.get(TagPrefix.dust, property.getOreByProduct(2, material)),
                        1700,
                        0
                )
                .EUt(90)
                .duration(1000)
                .save(provider);
        //洗矿-粉碎-离心
        OreProperty one = material.getProperty(PropertyKey.ORE);
        ItemStack Dust = ChemicalHelper.get(TagPrefix.dust, material);
        if (Dust.isEmpty()) return;
        CBRecipeBuilder.of(CTNHBio.id("wash_grind_centrifuge_" + material.getName()),
                        CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(2)
                .inputItems(TagPrefix.crushed, material)
                .inputFluids(new FluidStack(DistilledWater.getFluid(), 1500))
                .outputItems(Dust)
                .chancedOutput(
                        ChemicalHelper.get(TagPrefix.dust, one.getOreByProduct(0, material)),
                        1400,
                        0
                )
                .chancedOutput(
                        ChemicalHelper.get(TagPrefix.dust, one.getOreByProduct(1, material)),
                        1500,
                        0
                )
                .chancedOutput(
                        ChemicalHelper.get(TagPrefix.dust, one.getOreByProduct(1, material)),
                        1700,
                        0
                )
                .EUt(24)
                .duration(1500)
                .save(provider);
        //酸洗-粉碎-离心
        if (!property.getWashedIn().first().isNull()) {
            Material washingByproduct = property.getOreByProduct(3, material);
            ObjectIntPair<Material> washedInTuple = property.getWashedIn();
            CBRecipeBuilder.of(CTNHBio.id("acid_grind_centrifuge_" + material.getName()),
                            CBRecipeTypes.DECOMPOSER_RECIPES)
                    .nutrient(3)
                    .inputItems(TagPrefix.crushed, material)
                    .inputFluids(new FluidStack(
                            washedInTuple.first().getFluid(),
                            washedInTuple.secondInt() * 2
                    ))
                    .outputItems(ChemicalHelper.get(TagPrefix.dust, material))
                    .chancedOutput(
                            ChemicalHelper.get(TagPrefix.dust, washingByproduct),
                            7000,
                            0
                    )
                    .chancedOutput(
                            ChemicalHelper.get(TagPrefix.dust, property.getOreByProduct(1, material)),
                            1700,
                            0
                    )
                    .chancedOutput(
                            ChemicalHelper.get(TagPrefix.dust, property.getOreByProduct(1, material)),
                            1300,
                            0
                    )
                    .EUt(64)
                    .duration(800)
                    .save(provider);
        }
    }
}


