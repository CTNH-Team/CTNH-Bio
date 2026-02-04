package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.datagen.recipes.builder.BioForgingRecipeBuilder;
import com.github.elenterius.biomancy.init.ModBioForgeTabs;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.LV;


public class VanillaRecipes {


    @SuppressWarnings("all")
    public static void init(Consumer<FinishedRecipe> provider) {
        //LIVING_FLESH
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
                'G', Items.CHICKEN
        );

//PRIMORDIAL_CORE
        VanillaRecipeHelper.addShapedRecipe(provider, false, true,
                CTNHBio.id("primordial_core_recipe"),
                new ItemStack(ModItems.PRIMORDIAL_CORE.get(), 1),
                "ABA",
                "BIB",
                "ABA",
                'A', ModItems.LIVING_FLESH.get(),
                'B', Items.AMETHYST_SHARD,
                'I', CustomTags.LV_CIRCUITS
        );
    }
}
