package com.moguang.ctnhbio.data.recipe.living;

import com.github.elenterius.biomancy.init.ModFluids;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
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

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.gemChipped;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;

public class DecomposerRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        //分解者
        CBRecipeBuilder.of(CTNHBio.id("grass_block_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .effect(MobEffects.POISON)
                .inputItems(Items.GRASS_BLOCK.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dirt_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DIRT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("coarse_dirt_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.COARSE_DIRT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("podzol_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.PODZOL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("rooted_dirt_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.ROOTED_DIRT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("sand_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.SAND.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 3))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("red_sand_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.RED_SAND.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 3))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("gravel_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.GRAVEL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(3, 6))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("sponge_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.SPONGE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("sea_pickle_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.SEA_PICKLE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("lily_of_the_valley_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.LILY_OF_THE_VALLEY.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .outputFluidsRanged(new FluidStack(Toxin_Extract.getFluid(), 1), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("oxeye_daisy_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.OXEYE_DAISY.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("wither_rose_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.WITHER_ROSE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 2))
                .outputFluidsRanged(new FluidStack(Wither_Slime.getFluid(), 1), UniformInt.of(3, 5))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("spore_blossom_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.SPORE_BLOSSOM.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(3, 5))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("brown_mushroom_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.BROWN_MUSHROOM.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("red_mushroom_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.RED_MUSHROOM.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("crimson_fungus_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.CRIMSON_FUNGUS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("warped_fungus_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.WARPED_FUNGUS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("crimson_roots_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.CRIMSON_ROOTS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("warped_roots_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.WARPED_ROOTS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("nether_sprouts_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.NETHER_SPROUTS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("sugar_cane_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.SUGAR_CANE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(Items.SUGAR), UniformInt.of(1, 2))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("kelp_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.KELP.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("bamboo_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.BAMBOO.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("chorus_flower_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.CHORUS_FLOWER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(2, 5))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("clay_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.CLAY.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("glowstone_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.GLOWSTONE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 4))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 4))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("glow_lichen_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.GLOW_LICHEN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dragon_egg_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DRAGON_EGG.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(97, 128))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(6, 10))
                .outputFluidsRanged(new FluidStack(Endocrine_Hormone.getFluid(), 1), UniformInt.of(170, 230))
                .EUt(32)
                .duration(7080)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("turtle_egg_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.TURTLE_EGG.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Endocrine_Hormone.getFluid(), 1), UniformInt.of(10, 50))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("tube_coral_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.TUBE_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(10, 25))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("brain_coral_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.BRAIN_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(10, 25))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("bubble_coral_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.BUBBLE_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(10, 25))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("fire_coral_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.FIRE_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(10, 25))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("horn_coral_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.HORN_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(10, 25))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_brain_coral_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DEAD_BRAIN_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_bubble_coral_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DEAD_BUBBLE_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_fire_coral_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DEAD_FIRE_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_horn_coral_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DEAD_HORN_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_tube_coral_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DEAD_TUBE_CORAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("tube_coral_fan_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.TUBE_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(10, 25))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("brain_coral_fan_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.BRAIN_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(10, 25))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("bubble_coral_fan_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.BUBBLE_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(10, 25))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("fire_coral_fan_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.FIRE_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(10, 25))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("horn_coral_fan_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.HORN_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(10, 25))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_tube_coral_fan_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DEAD_TUBE_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_brain_coral_fan_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DEAD_BRAIN_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_bubble_coral_fan_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DEAD_BUBBLE_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_fire_coral_fan_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DEAD_FIRE_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dead_horn_coral_fan_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DEAD_HORN_CORAL_FAN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("redstone_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.REDSTONE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("turtle_helmet_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.TURTLE_HELMET.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(15, 25))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(9, 15))
                .EUt(32)
                .duration(400)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("scute_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.SCUTE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("apple_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.APPLE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("diamond_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DIAMOND.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(4, 8))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("emerald_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.EMERALD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(5, 9))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("lapis_lazuli_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.LAPIS_LAZULI.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("quartz_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.QUARTZ.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("amethyst_shard_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.AMETHYST_SHARD.asItem().getDefaultInstance())
                .outputItemsRanged(gemChipped,Amethyst, UniformInt.of(3, 5))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("raw_iron_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.RAW_IRON.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("iron_ingot_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.IRON_INGOT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("raw_copper_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.RAW_COPPER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("copper_ingot_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.COPPER_INGOT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("raw_gold_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.RAW_GOLD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("gold_ingot_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.GOLD_INGOT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("netherite_ingot_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.NETHERITE_INGOT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(43, 72))
                .EUt(32)
                .duration(600)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("netherite_scrap_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.NETHERITE_SCRAP.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 9))
                .EUt(32)
                .duration(150)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("string_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.STRING.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("feather_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.FEATHER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("flint_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.FLINT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("porkchop_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.PORKCHOP.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(2, 3))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("golden_apple_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(12)
                .inputItems(Items.GOLDEN_APPLE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(37, 63))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(4, 6))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(30, 60))
                .EUt(32)
                .duration(2840)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("enchanted_golden_apple_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(17)
                .inputItems(Items.ENCHANTED_GOLDEN_APPLE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(43, 72))
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(120, 200))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(6, 10))
                .EUt(32)
                .duration(4080)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("leather_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.LEATHER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(1, 4))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("clay_ball_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.CLAY_BALL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("slime_ball_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.SLIME_BALL.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(20, 30))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(10, 20))
                .EUt(32)
                .duration(140)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("slime_block_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.SLIME_BLOCK.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(1800, 2700))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(1000, 1800))
                .EUt(32)
                .duration(1300)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("magma_cream_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.MAGMA_CREAM.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Regenerate_Fluid.getFluid(), 1), UniformInt.of(10, 20))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(10, 30))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 3))
                .outputFluidsRanged(new FluidStack(Mutagenic_Secretion.getFluid(), 1), UniformInt.of(1, 20))
                .EUt(32)
                .duration(240)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("egg_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.EGG.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Endocrine_Hormone.getFluid(), 1), UniformInt.of(1, 100))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("glowstone_dust_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.GLOWSTONE_DUST.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("cod_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.COD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("salmon_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.SALMON.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("tropical_fish_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.TROPICAL_FISH.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("pufferfish_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.PUFFERFISH.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .outputFluidsRanged(new FluidStack(Toxin_Extract.getFluid(), 1), UniformInt.of(10, 30))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("ink_sac_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.INK_SAC.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(10, 20))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("glow_ink_sac_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.GLOW_INK_SAC.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("cocoa_beans_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.COCOA_BEANS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("bone_meal_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.BONE_MEAL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("bone_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.BONE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(3, 6))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("cake_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.CAKE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(10, 18))
                .EUt(32)
                .duration(400)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("cookie_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.COOKIE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("melon_slice_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.MELON_SLICE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dried_kelp_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DRIED_KELP.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dried_kelp_block_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.DRIED_KELP_BLOCK.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("wheat_seeds_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.WHEAT_SEEDS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("beef_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.BEEF.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(3, 6))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("chicken_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.CHICKEN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(3, 4))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(2, 3))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("rotten_flesh_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.ROTTEN_FLESH.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(1, 3))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("ender_pearl_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.ENDER_PEARL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(2, 3))
                .EUt(32)
                .duration(240)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("blaze_rod_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.BLAZE_ROD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(2, 2))
                .EUt(32)
                .duration(240)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("blaze_powder_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.BLAZE_POWDER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("ghast_tear_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.GHAST_TEAR.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Endocrine_Hormone.getFluid(), 1), UniformInt.of(40, 80))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(10, 20))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(300)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("gold_nugget_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.GOLD_NUGGET.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("nether_wart_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.NETHER_WART.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("spider_eye_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.SPIDER_EYE.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(1, 10))
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("fermented_spider_eye_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.FERMENTED_SPIDER_EYE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("ender_eye_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.ENDER_EYE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(5, 6))
                .EUt(32)
                .duration(240)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("glistering_melon_slice_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.GLISTERING_MELON_SLICE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(3, 6))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(2, 2))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("carrot_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.CARROT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("potato_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.POTATO.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("baked_potato_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.BAKED_POTATO.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(2, 5))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("poisonous_potato_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.POISONOUS_POTATO.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Toxin_Extract.getFluid(), 1), UniformInt.of(20, 40))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(140)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("golden_carrot_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.GOLDEN_CARROT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(4, 8))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("skeleton_skull_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(15)
                .inputItems(Items.SKELETON_SKULL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(28, 48))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(4, 7))
                .EUt(32)
                .duration(1880)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("wither_skeleton_skull_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(15)
                .inputItems(Items.WITHER_SKELETON_SKULL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(28, 48))
                .outputFluidsRanged(new FluidStack(Wither_Slime.getFluid(), 1), UniformInt.of(80, 160))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(4, 7))
                .EUt(32)
                .duration(1880)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("player_head_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(15)
                .inputItems(Items.PLAYER_HEAD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(19, 32))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(Items.SKELETON_SKULL), UniformInt.of(1, 1))
                .EUt(32)
                .duration(2000)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("zombie_head_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(15)
                .inputItems(Items.ZOMBIE_HEAD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(14, 24))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(Items.SKELETON_SKULL), UniformInt.of(1, 1))
                .EUt(32)
                .duration(2000)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("creeper_head_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(15)
                .inputItems(Items.CREEPER_HEAD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(19, 32))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(Items.SKELETON_SKULL), UniformInt.of(1, 1))
                .EUt(32)
                .duration(2000)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("dragon_head_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
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

        CBRecipeBuilder.of(CTNHBio.id("nether_star_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.NETHER_STAR.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(50, 50))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(25, 25))
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(20, 20))
                .EUt(32)
                .duration(10000)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("prismarine_shard_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.PRISMARINE_SHARD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("prismarine_crystals_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.PRISMARINE_CRYSTALS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(1, 3))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("rabbit_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.RABBIT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(3, 6))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(2, 3))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("rabbit_foot_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.RABBIT_FOOT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 3))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("rabbit_hide_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.RABBIT_HIDE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("mutton_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.MUTTON.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(2, 3))
                .EUt(32)
                .duration(120)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("chorus_fruit_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.CHORUS_FRUIT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 3))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 2))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(1, 10))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(260)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("chorus_flower_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.CHORUS_FLOWER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(2, 5))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("shulker_shell_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(15)
                .inputItems(Items.SHULKER_SHELL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(6, 10))
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(4, 7))
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(800)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("iron_nugget_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.IRON_NUGGET.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(40)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("phantom_membrane_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.PHANTOM_MEMBRANE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(4, 7))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("nautilus_shell_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(15)
                .inputItems(Items.NAUTILUS_SHELL.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(6, 10))
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(4, 7))
                .EUt(32)
                .duration(600)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("heart_of_the_sea_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(17)
                .inputItems(Items.HEART_OF_THE_SEA.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.GEM_FRAGMENTS.get()), UniformInt.of(8, 8))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(15, 15))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 5))
                .EUt(32)
                .duration(6000)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("glow_berries_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.GLOW_BERRIES.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 1))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("shroomlight_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(15)
                .inputItems(Items.SHROOMLIGHT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(5, 9))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(2, 3))
                .EUt(32)
                .duration(520)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("pointed_dripstone_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.POINTED_DRIPSTONE.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(120)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("mob_fang_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.MOB_FANG.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(4, 6))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("mob_claw_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.MOB_CLAW.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(3, 5))
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(4, 6))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("mob_sinew_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.MOB_SINEW.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(4, 8))
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(180)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("mob_marrow_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.MOB_MARROW.get().asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Endocrine_Hormone.getFluid(), 1), UniformInt.of(10, 40))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(2, 4))
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(260)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("withered_mob_marrow_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.WITHERED_MOB_MARROW.get().asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Wither_Slime.getFluid(), 1), UniformInt.of(30, 50))
                .outputItemsRanged(new ItemStack(ModItems.BONE_FRAGMENTS.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(260)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("generic_mob_gland_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.GENERIC_MOB_GLAND.get().asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(40, 60))
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 3))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("toxin_gland_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.TOXIN_GLAND.get().asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Toxin_Extract.getFluid(), 1), UniformInt.of(20, 50))
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 3))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("volatile_gland_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.VOLATILE_GLAND.get().asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Mutagenic_Secretion.getFluid(), 1), UniformInt.of(20, 50))
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(2, 3))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(1, 3))
                .EUt(32)
                .duration(200)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("echo_shard_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.ECHO_SHARD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(8, 12))
                .EUt(32)
                .duration(300)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("goat_horn_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.GOAT_HORN.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.MINERAL_FRAGMENT.get()), UniformInt.of(5, 7))
                .outputItemsRanged(new ItemStack(ModItems.TOUGH_FIBERS.get()), UniformInt.of(6, 8))
                .EUt(32)
                .duration(240)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("pearlescent_froglight_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(15)
                .inputItems(Items.PEARLESCENT_FROGLIGHT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(5, 9))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(20, 30))
                .EUt(32)
                .duration(400)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("verdant_froglight_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(15)
                .inputItems(Items.VERDANT_FROGLIGHT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(5, 9))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(20, 30))
                .EUt(32)
                .duration(400)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("ochre_froglight_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(15)
                .inputItems(Items.OCHRE_FROGLIGHT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(5, 9))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(20, 30))
                .EUt(32)
                .duration(400)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("frogspawn_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.FROGSPAWN.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(1, 10))
                .EUt(32)
                .duration(20)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("pitcher_pod_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.PITCHER_POD.asItem().getDefaultInstance())
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(10, 30))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 3))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(180)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("pitcher_plant_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.PITCHER_PLANT.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 3))
                .outputFluidsRanged(new FluidStack(Toxin_Extract.getFluid(), 1), UniformInt.of(1, 20))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(2, 4))
                .EUt(32)
                .duration(180)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("torchflower_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.TORCHFLOWER.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.BIO_LUMENS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(80)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("torchflower_seeds_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.TORCHFLOWER_SEEDS.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(60)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("sniffer_egg_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(Items.SNIFFER_EGG.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.STONE_POWDER.get()), UniformInt.of(1, 4))
                .outputFluidsRanged(new FluidStack(Endocrine_Hormone.getFluid(), 1), UniformInt.of(10, 40))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 4))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(2, 6))
                .EUt(32)
                .duration(320)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("piglin_head_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(15)
                .inputItems(Items.PIGLIN_HEAD.asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(22, 36))
                .outputItemsRanged(new ItemStack(ModItems.ELASTIC_FIBERS.get()), UniformInt.of(8, 12))
                .outputItemsRanged(new ItemStack(Items.SKELETON_SKULL), UniformInt.of(1, 1))
                .EUt(32)
                .duration(2000)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("living_flesh_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.LIVING_FLESH.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(3, 6))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(1, 2))
                .EUt(32)
                .duration(180)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("primal_orifice_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.PRIMAL_ORIFICE.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(1, 2))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(10, 40))
                .EUt(32)
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("primal_bloom_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.PRIMAL_BLOOM.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.FLESH_BITS.get()), UniformInt.of(1, 2))
                .outputItemsRanged(new ItemStack(ModItems.EXOTIC_DUST.get()), UniformInt.of(2, 3))
                .outputFluidsRanged(new FluidStack(Bile.getFluid(), 1), UniformInt.of(10, 30))
                .EUt(32)
                .duration(220)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("nutrient_paste_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.NUTRIENT_PASTE.get().asItem().getDefaultInstance())
                .outputItemsRanged(new ItemStack(ModItems.NUTRIENTS.get()), UniformInt.of(5, 5))
                .outputItemsRanged(new ItemStack(GTItems.BIO_CHAFF.get()), UniformInt.of(1, 1))
                .EUt(32)
                .duration(120)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("flesh_bits_cb"), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(10)
                .inputItems(ModItems.FLESH_BITS.get().asItem().getDefaultInstance())
                .outputItemsRanged(TagPrefix.dust,Meat, UniformInt.of(1, 3))
                .EUt(24)
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
                .effect(MobEffects.DAMAGE_BOOST)
                .nutrient(10)
                .inputItems(TagPrefix.crushed, material)
                .inputFluids(new FluidStack(DistilledWater.getFluid(), 5000))
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
                .EUt(8000)
                .duration(500)
                .save(provider);
        //洗矿-粉碎-离心
        OreProperty one = material.getProperty(PropertyKey.ORE);
        ItemStack Dust = ChemicalHelper.get(TagPrefix.dust, material);
        if (Dust.isEmpty()) return;
        CBRecipeBuilder.of(CTNHBio.id("wash_grind_centrifuge_" + material.getName()),
                        CBRecipeTypes.DECOMPOSER_RECIPES)
                .effect(MobEffects.DAMAGE_BOOST)
                .nutrient(15)
                .inputItems(TagPrefix.crushed, material)
                .inputFluids(new FluidStack(Fluids.WATER, 1500))
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
                .duration(200)
                .save(provider);
        //酸洗-粉碎-离心
        if (!property.getWashedIn().first().isNull()) {
            Material washingByproduct = property.getOreByProduct(3, material);
            //ObjectIntPair<Material> washedInTuple = property.getWashedIn();
            CBRecipeBuilder.of(CTNHBio.id("acid_grind_centrifuge_" + material.getName()),
                            CBRecipeTypes.DECOMPOSER_RECIPES)
                    .effect(MobEffects.DAMAGE_BOOST)
                    .nutrient(10)
                    .inputItems(TagPrefix.crushed, material)
                    .inputFluids(new FluidStack(ModFluids.ACID.get(), 200))
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


