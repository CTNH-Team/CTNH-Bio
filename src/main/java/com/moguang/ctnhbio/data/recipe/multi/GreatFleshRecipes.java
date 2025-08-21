package com.moguang.ctnhbio.data.recipe.multi;

import com.gregtechceu.gtceu.api.GTValues;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class GreatFleshRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CBRecipeBuilder.of(CTNHBio.id("cogni_assembler"), CBRecipeTypes.GREAT_FLESH)
                .nutrient(1)
                .inputItems(Ingredient.of(CBItems.META_CORE))
                .duration(100)
                .EUt(32)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("circulatory_system"), CBRecipeTypes.GREAT_FLESH)
                .nutrient(1)
                .inputItems(Ingredient.of(CBItems.OMNI_CORE))
                .duration(100)
                .save(provider);

        CBRecipeBuilder.of(CTNHBio.id("weatherer"), CBRecipeTypes.GREAT_FLESH)
                .nutrient(1)
                .inputItems(Ingredient.of(CBItems.NOVA_CORE))
                .duration(100)
                .save(provider);

    }
}
