package com.moguang.ctnhbio.data.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.moguang.ctnhbio.api.capability.NutrientRecipeCapability;
import com.moguang.ctnhbio.common.condition.EffectCondition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class CBRecipeBuilder extends GTRecipeBuilder {
    public CBRecipeBuilder(ResourceLocation id, GTRecipeType recipeType) {
        super(id, recipeType);
    }

    public CBRecipeBuilder(GTRecipe toCopy, GTRecipeType recipeType) {
        super(toCopy, recipeType);
    }
    @SuppressWarnings("all")
    public static CBRecipeBuilder of(ResourceLocation id, GTRecipeType recipeType) {
        return new CBRecipeBuilder(id, recipeType);
    }
    @Override
    @SuppressWarnings("all")
    public final <T> CBRecipeBuilder input(RecipeCapability<T> capability, T... obj) {
        super.input(capability, obj);
        return this;
    }

    @Override
    @SuppressWarnings("all")
    public <T> CBRecipeBuilder output(RecipeCapability<T> capability, T... obj) {
        super.output(capability, obj);
        return this;
    }
    public CBRecipeBuilder nutrient(double nutrient) {
        this.addData("nutrient", (float) nutrient);
        if (nutrient >= 0) {
            input(NutrientRecipeCapability.CAP, nutrient);
        }
        else {
            output(NutrientRecipeCapability.CAP, -nutrient);
        }
        return this;
    }

    @Override
    @SuppressWarnings("all")
    public CBRecipeBuilder inputItems(ItemStack input) {
        super.inputItems(input);
        return this;
    }

    @Override
    @SuppressWarnings("all")
    public CBRecipeBuilder outputItems(ItemStack output) {
        super.outputItems(output);
        return this;
    }

    @Override
    public CBRecipeBuilder inputFluids(FluidStack input) {
        super.inputFluids(input);
        return this;
    }

    @Override
    public CBRecipeBuilder outputFluids(FluidStack output) {
        super.outputFluids(output);
        return this;
    }

    @Override
    public CBRecipeBuilder duration(int duration) {
        super.duration(duration);
        return this;
    }
    @Override
    public CBRecipeBuilder notConsumableFluid(FluidStack fluid) {
        super.notConsumableFluid(fluid);
        return this;
    }
    @Override
    public CBRecipeBuilder notConsumable(ItemStack input) {
        super.notConsumable(input);
        return this;
    }
    public CBRecipeBuilder effect(MobEffect... effect) {
        super.addCondition(new EffectCondition(effect));
        return this;
    }
}
