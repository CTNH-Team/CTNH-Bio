package com.moguang.ctnhbio.data.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.moguang.ctnhbio.api.capability.NutrientRecipeCapability;
import com.moguang.ctnhbio.api.capability.recipe.EntityRecipeCapability;
import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import com.moguang.ctnhbio.common.condition.EffectCondition;
import dev.shadowsoffire.hostilenetworks.Hostile;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

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
    /*Entity Recipe*/
    //basics
    public CBRecipeBuilder inputEntity(EntityIngredient entity){
        perTick = false;
        input(EntityRecipeCapability.CAP, entity);
        return this;
    }
    public CBRecipeBuilder outputEntity(EntityIngredient entity){
        perTick = false;
        output(EntityRecipeCapability.CAP, entity);
        return this;
    }
    //forward EntityIngredient constructors
    public CBRecipeBuilder inputEntity(EntityType<?> type){
        return inputEntity(EntityIngredient.of(type));
    }
    public CBRecipeBuilder inputEntity(TagKey<EntityType<?>> tag){
        return inputEntity(EntityIngredient.of(tag));
    }
    public CBRecipeBuilder inputEntity(String id){
        return inputEntity(EntityIngredient.of(id));
    }
    public CBRecipeBuilder outputEntity(EntityType<?> type){
        return outputEntity(EntityIngredient.of(type));
    }
    public CBRecipeBuilder outputEntity(TagKey<EntityType<?>> tag){
        return outputEntity(EntityIngredient.of(tag));
    }
    public CBRecipeBuilder outputEntity(String id){
        return outputEntity(EntityIngredient.of(id));
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

    public CBRecipeBuilder inputModel(ModelIngredient model){
        perTick = false;
        input(ModelRecipeCapability.CAP, model);
        return this;
    }
    public CBRecipeBuilder outputModel(ModelIngredient model){
        perTick = false;
        output(ModelRecipeCapability.CAP, model);
        return this;
    }

    @Override
    public CBRecipeBuilder inputFluids(FluidStack input) {
        super.inputFluids(input);
        return this;
    }


    public CBRecipeBuilder inputFluids(String rl, int amount) {
        super.inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse(rl)),amount));
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
