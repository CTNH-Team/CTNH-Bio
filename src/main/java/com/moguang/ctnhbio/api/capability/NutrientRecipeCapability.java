package com.moguang.ctnhbio.api.capability;

import com.gregtechceu.gtceu.api.capability.recipe.CWURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.*;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;
import com.moguang.ctnhbio.api.ILivingMachine;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.List;

public class NutrientRecipeCapability extends RecipeCapability<Double> {
    public static NutrientRecipeCapability CAP = new NutrientRecipeCapability();
    protected NutrientRecipeCapability() {
        super("nutrient", 0xFEEE00, false, 4, SerializerDouble.INSTANCE);
    }
    @Override
    public int limitMaxParallelByOutput(IRecipeCapabilityHolder holder, GTRecipe recipe, int maxMultiplier, boolean tick) {
        if (holder instanceof ILivingMachine livingMachine) {
            double nutrient = recipe.getOutputContents(NutrientRecipeCapability.CAP)
                    .stream().map(Content::getContent).mapToDouble(NutrientRecipeCapability.CAP::of).sum();
            return (int) Math.floor((livingMachine.getNutrientCapacity() - livingMachine.getNutrientAmount()) / nutrient);
        }
        return Integer.MAX_VALUE;
    }
    @Override
    public int getMaxParallelByInput(IRecipeCapabilityHolder holder, GTRecipe recipe, int limit, boolean tick) {
        if (holder instanceof ILivingMachine livingMachine) {
            double nutrient = recipe.getInputContents(NutrientRecipeCapability.CAP)
                    .stream().map(Content::getContent).mapToDouble(NutrientRecipeCapability.CAP::of).sum();
            return (int) Math.floor(livingMachine.getNutrientAmount() / nutrient);
        }
        return Integer.MAX_VALUE;
    }
    @Override
    public Double copyInner(Double content) {
        return content;
    }

    @Override
    public Double copyWithModifier(Double content, ContentModifier modifier) {
        return modifier.apply(content);
    }

    @Override
    public void addXEIInfo(WidgetGroup group, int xOffset, GTRecipe recipe, List<Content> contents, boolean perTick,
                           boolean isInput, MutableInt yOffset) {
        double nutrient = contents.stream().map(Content::getContent).mapToDouble(NutrientRecipeCapability.CAP::of).sum();
        group.addWidget(new LabelWidget(3 - xOffset, yOffset.addAndGet(10),
                LocalizationUtils.format("ctnhbio.recipe.nutrient", nutrient)));
    }
}
