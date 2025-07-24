package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.effect.MobEffects;

import java.util.function.Consumer;

public class BioElectricRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CBRecipeBuilder.of(CTNHBio.id("test"), CBRecipeTypes.BIO_REACTOR_RECIPES)
                .effect(MobEffects.POISON)
                .nutrient(2)
                .inputItems(ModItems.BILE.get().asItem().getDefaultInstance())
                .outputItems(ModItems.TOXIN_GLAND.get().asItem().getDefaultInstance())
                .EUt(32)
                .duration(100)
                .save(provider);
    }
}
