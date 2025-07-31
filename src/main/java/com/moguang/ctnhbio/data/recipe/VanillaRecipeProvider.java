package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.moguang.ctnhbio.CTNHBio;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;


public class VanillaRecipeProvider extends RecipeProvider {
    public VanillaRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider, false, true, // 关键参数：setMaterialInfoData=false, isStrict=true
                CTNHBio.id("living_flesh_recipe"),
                new ItemStack(ModItems.LIVING_FLESH.get(), 8),
                "AAA", "AAA", "AAA",
                'A', Items.BEEF
        );
    }
}
