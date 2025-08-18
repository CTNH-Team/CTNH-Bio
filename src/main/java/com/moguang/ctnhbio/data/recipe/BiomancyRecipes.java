package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.datagen.recipes.builder.BioForgingRecipeBuilder;
import com.github.elenterius.biomancy.datagen.recipes.builder.ItemData;
import com.github.elenterius.biomancy.init.ModBioForgeTabs;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBMachines;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.data.CreateBlockEntityBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.github.elenterius.biomancy.init.ModRecipes.BIO_FORGING_RECIPE_TYPE;
import static com.gregtechceu.gtceu.api.GTValues.LV;
import static com.gregtechceu.gtceu.api.GTValues.MV;
import static com.gregtechceu.gtceu.common.data.GTItems.*;


public class BiomancyRecipes extends RecipeProvider {
    public BiomancyRecipes(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        BioForgingRecipeBuilder.create(CTNHBio.id("digester_no_fluid"), new ItemData(ModItems.DIGESTER.get()))
                .addIngredient(ModItems.LIVING_FLESH.get())
                .addIngredient(ModItems.FLESH_BITS.get(), 10)
                .addIngredient(ModItems.BONE_FRAGMENTS.get(), 3)
                .addIngredient(ModItems.ELASTIC_FIBERS.get(), 8)
                .setCraftingCost(20)
                .setCategory(ModBioForgeTabs.MACHINES)
                .unlockedBy(ModItems.LIVING_FLESH.get())
                .save(consumer);

        BioForgingRecipeBuilder.create(CTNHBio.id("expensive_fleshkin_chest"), new ItemData(ModItems.FLESHKIN_CHEST.get()))
                .addIngredient(ModItems.LIVING_FLESH.get())
                .addIngredient(ModItems.FLESH_BITS.get(), 10)
                .addIngredient(ModItems.BONE_FRAGMENTS.get(), 12)
                .addIngredient(ModItems.TOUGH_FIBERS.get(), 32)
                .setCraftingCost(30)
                .setCategory(ModBioForgeTabs.MISC)
                .unlockedBy(ModItems.LIVING_FLESH.get()).save(consumer);

        BioForgingRecipeBuilder.create(CTNHBio.id("expensive_mascot_banner_patterns"), new ItemData(ModItems.MASCOT_BANNER_PATTERNS.get()))
                .addIngredient(ModItems.ORGANIC_MATTER.get(), 8)
                .addIngredient(Items.SPIDER_EYE, 1)
                .setCraftingCost(500)
                .setCategory(ModBioForgeTabs.MISC)
                .unlockedBy(ModItems.ORGANIC_MATTER.get()).save(consumer);

        BioForgingRecipeBuilder.create(CTNHBio.id("expensive_modular_larynx"), new ItemData(ModItems.MODULAR_LARYNX.get()))
                .addIngredient(ModItems.LIVING_FLESH.get())
                .addIngredient(ModItems.FLESH_BITS.get(), 6)
                .addIngredient(ModItems.BONE_FRAGMENTS.get(), 12)
                .addIngredient(ModItems.ELASTIC_FIBERS.get(), 16)
                .setCraftingCost(20)
                .setCategory(ModBioForgeTabs.MISC)
                .unlockedBy(ModItems.LIVING_FLESH.get()).save(consumer);

        BioForgingRecipeBuilder.create(CBMachines.DECOMPOSER[LV].getItem())
                .addIngredient(ModItems.PRIMORDIAL_CORE.get(), 2)
                .addIngredient(ModItems.DECOMPOSER.get(), 1)
                .addIngredient(CustomTags.MV_CIRCUITS,2)
                .addIngredient(GTMachines.MACERATOR[LV].getItem(), 1)
                .addIngredient(ELECTRIC_PUMP_LV, 2)
                .setCraftingCost(2)
                .setCategory(ModBioForgeTabs.MACHINES)
                .unlockedBy(ModItems.FLESH_BITS.get())
                .save(consumer);
        BioForgingRecipeBuilder.create(CBMachines.BIOELECTRIC_FORGE[LV].getItem())
                .addIngredient(ModItems.PRIMORDIAL_CORE.get(), 2)
                .addIngredient(ModItems.BIO_FORGE.get(), 1)
                .addIngredient(CustomTags.MV_CIRCUITS,2)
                .addIngredient(GTMachines.ASSEMBLER[LV].getItem(), 1)
                .addIngredient(ELECTRIC_PUMP_LV, 2)
                .setCraftingCost(2)
                .setCategory(ModBioForgeTabs.MACHINES)
                .unlockedBy(ModItems.FLESH_BITS.get())
                .save(consumer);
        BioForgingRecipeBuilder.create(CBMachines.DIGESTER[LV].getItem())
                .addIngredient(ModItems.PRIMORDIAL_CORE.get(), 2)
                .addIngredient(ModItems.DIGESTER.get(), 1)
                .addIngredient(CustomTags.MV_CIRCUITS,2)
                .addIngredient(GTMachines.BREWERY[LV].getItem(), 1)
                .addIngredient(CONVEYOR_MODULE_LV, 2)
                .setCraftingCost(2)
                .setCategory(ModBioForgeTabs.MACHINES)
                .unlockedBy(ModItems.FLESH_BITS.get())
                .save(consumer);
        BioForgingRecipeBuilder.create(CBMachines.BIOREACTOR[LV].getItem())
                .addIngredient(ModItems.PRIMORDIAL_CORE.get(), 2)
                .addIngredient(Items.SLIME_BLOCK, 1)
                .addIngredient(CustomTags.MV_CIRCUITS,2)
                .addIngredient(GTMachines.CHEMICAL_REACTOR[LV].getItem(), 1)
                .addIngredient(ROBOT_ARM_LV, 2)
                .setCraftingCost(2)
                .setCategory(ModBioForgeTabs.MACHINES)
                .unlockedBy(ModItems.FLESH_BITS.get())
                .save(consumer);

        BioForgingRecipeBuilder.create(CBItems.ORGANIC_VIAL)
                .addIngredient(ModItems.ELASTIC_FIBERS.get(), 2)
                .setCraftingCost(2)
                .setCategory(ModBioForgeTabs.TOOLS)
                .unlockedBy(ModItems.FLESH_BITS.get())
                .save(consumer);



    }
}
