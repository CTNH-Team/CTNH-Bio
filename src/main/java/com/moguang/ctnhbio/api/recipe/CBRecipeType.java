package com.moguang.ctnhbio.api.recipe;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.common.recipe.MobCrushingRecipe;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.Recipe;

public class CBRecipeType{
    public static final RecipeType<MobCrushingRecipe> MOB_CRUSHING = new RecipeType<>(CTNHBio.id("mob_crushing"), MobCrushingRecipe.class);
}
