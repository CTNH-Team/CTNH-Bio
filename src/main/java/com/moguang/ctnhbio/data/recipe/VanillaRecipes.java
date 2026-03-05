package com.moguang.ctnhbio.data.recipe;

import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.CTNHBio;

import java.util.function.Consumer;

public class VanillaRecipes {

    @SuppressWarnings("all")
    public static void init(Consumer<FinishedRecipe> provider) {
        // LIVING_FLESH
        VanillaRecipeHelper.addShapedRecipe(provider, false, true,
                CTNHBio.id("living_flesh_recipe"),
                new ItemStack(ModItems.LIVING_FLESH.get(), 16),
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
                'G', Items.CHICKEN);

        // PRIMORDIAL_CORE
        VanillaRecipeHelper.addShapedRecipe(provider, false, true,
                CTNHBio.id("primordial_core_recipe"),
                new ItemStack(ModItems.PRIMORDIAL_CORE.get(), 1),
                "ABA",
                "BIB",
                "ABA",
                'A', ModItems.LIVING_FLESH.get(),
                'B', Items.AMETHYST_SHARD,
                'I', CustomTags.LV_CIRCUITS);
    }
}
