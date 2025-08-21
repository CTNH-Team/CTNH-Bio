package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBMachines;
import dev.architectury.platform.Mod;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.LV;


public class VanillaRecipeProvider {


    @SuppressWarnings("all")
    public static void init(Consumer<FinishedRecipe> provider) {
        // 第一个配方：LIVING_FLESH
        VanillaRecipeHelper.addShapedRecipe(provider, false, true,
                CTNHBio.id("living_flesh_recipe"),
                new ItemStack(ModItems.LIVING_FLESH.get(), 4),
                "ABC",
                "HIH",
                "DFG",
                'A', Items.PORKCHOP,
                'B', Items.RABBIT,
                'C', Items.BEEF,
                'H', Items.ENDER_PEARL,
                'I', Items.SPIDER_EYE,
                'D', Items.MUTTON,
                'F', Items.ROTTEN_FLESH,
                'G', Items.CHICKEN
        );

// 第二个配方：PRIMORDIAL_CORE
        VanillaRecipeHelper.addShapedRecipe(provider, false, true,
                CTNHBio.id("primordial_core_recipe"),
                new ItemStack(ModItems.PRIMORDIAL_CORE.get(), 1),
                "ABA",
                "BIB",
                "ABA",
                'A', ModItems.LIVING_FLESH.get(),
                'B', Items.AMETHYST_SHARD,
                'I', GTItems.SILICON_WAFER
        );

// 第三个配方：BIO_FORGE
        VanillaRecipeHelper.addShapedRecipe(provider, false, true,
                CTNHBio.id("bio_forge_recipe"),
                new ItemStack(ModItems.BIO_FORGE.get(), 1),
                "AEA",
                "BIB",
                "CDC",
                'A', ModItems.MOB_CLAW.get(),
                'B', CustomTags.LV_CIRCUITS,
                'C', GTItems.FLUID_CELL_LARGE_TUNGSTEN_STEEL.get(),
                'D', ModItems.LIVING_FLESH,
                'E', ModItems.PRIMORDIAL_CORE,
                'I', GTMachines.HULL[LV]
        );

        VanillaRecipeHelper.addShapedRecipe(provider, false, true,
                CTNHBio.id("bio_forge_recipe"),
                new ItemStack(ModItems.BIO_FORGE.get(), 1),
                "AEA",
                "BIB",
                "CDC",
                'A', ModItems.MOB_CLAW.get(),
                'B', CustomTags.LV_CIRCUITS,
                'C', GTItems.FLUID_CELL_LARGE_TUNGSTEN_STEEL.get(),
                'D', ModItems.LIVING_FLESH,
                'E', ModItems.PRIMORDIAL_CORE,
                'I', GTMachines.HULL[LV]
        );
    }
}
