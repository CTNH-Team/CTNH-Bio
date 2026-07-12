package com.moguang.ctnhbio.api.capability.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeDefinition;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.utils.GTMath;

import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;

import net.minecraft.network.chat.Component;

import com.moguang.ctnhbio.api.machine.trait.NotifiableNutrientHandler;
import com.mojang.serialization.Codec;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.List;

public class NutrientRecipeCapability extends RecipeCapability<Float> {

    public static NutrientRecipeCapability CAP = new NutrientRecipeCapability();

    protected NutrientRecipeCapability() {
        super("nutrient", 0xFEEE00, false, Codec.FLOAT);
    }

    @Override
    public Float fromNetwork(net.minecraft.network.FriendlyByteBuf friendlyByteBuf) {
        return friendlyByteBuf.readFloat();
    }

    @Override
    public void toNetwork(Float nutrient, net.minecraft.network.FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeFloat(nutrient);
    }

    @Override
    public Float copyInner(Float content, int multiplier) {
        return content * multiplier;
    }

    @Override
    public int limitMaxParallelByOutput(RecipeHandlerGroup holder, GTRecipe recipe, int limit, boolean tick) {
        float produced = (tick ? recipe.getTickOutputContents(this) : recipe.getOutputContents(this))
                .stream().reduce(0.0f, Float::sum);
        if (produced <= 0) return limit;

        float available = holder.getOutputHandlerMap().getOrDefault(this, List.of()).stream()
                .filter(NotifiableNutrientHandler.class::isInstance)
                .map(NotifiableNutrientHandler.class::cast)
                .map(NotifiableNutrientHandler::getLeft)
                .reduce(0.0f, Float::sum);
        return Math.min(limit, GTMath.saturatedCast((long) (available / produced)));
    }

    @Override
    public int getMaxParallelByInput(RecipeHandlerGroup holder, GTRecipe recipe, int limit, boolean tick) {
        float consumed = (tick ? recipe.getTickInputContents(this) : recipe.getInputContents(this))
                .stream().reduce(0.0f, Float::sum);
        if (consumed <= 0) return limit;

        float available = holder.getInputHandlerMap().getOrDefault(this, List.of()).stream()
                .filter(NotifiableNutrientHandler.class::isInstance)
                .map(NotifiableNutrientHandler.class::cast)
                .map(NotifiableNutrientHandler::getAmount)
                .reduce(0.0f, Float::sum);
        return Math.min(limit, GTMath.saturatedCast((long) (available / consumed)));
    }

    @Override
    public void addXEIInfo(WidgetGroup group, int xOffset, GTRecipeDefinition recipe, List<Float> contents,
                           int duration, boolean perTick,
                           boolean isInput, MutableInt yOffset) {
        float nutrient = contents.stream().reduce(0.0f, Float::sum);
        group.addWidget(new LabelWidget(3 - xOffset, yOffset.addAndGet(10),
                Component.translatable(
                        isInput ? "ctnhbio.recipe.nutrient_consume" : "ctnhbio.recipe.nutrient_generate", nutrient)));
    }

    @Override
    public boolean shouldBypassDistinct() {
        return true;
    }
}
