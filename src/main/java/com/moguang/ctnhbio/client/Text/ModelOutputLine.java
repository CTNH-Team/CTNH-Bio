package com.moguang.ctnhbio.client.Text;

import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.network.chat.Component;

import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;

import java.util.List;

public class ModelOutputLine {

    public static void addModelOutputLine(IMultiController machine, List<Component> textList) {
        if (machine instanceof IRecipeLogicMachine recipeLogicMachine) {
            GTRecipe recipe = recipeLogicMachine.getRecipeLogic().getLastRecipe();
            if (recipe == null) return;
            var modelOutputs = recipe.getOutputContents(ModelRecipeCapability.CAP);
            for (var model : modelOutputs) {
                var stack = ((ModelIngredient) model.content).getModel();
                int recipeTier = RecipeHelper.getPreOCRecipeEuTier(recipe);
                int chanceTier = recipeTier + recipe.ocLevel;
                var function = recipe.getType().getChanceFunction();
                double maxDurationSec = (double) recipe.duration / 20.0;
                int count = stack.getCount();
                double countD = count;
                if (model.chance < model.maxChance) {
                    countD = countD *
                            function.getBoostedChance(model, recipeTier, chanceTier) / model.maxChance;
                    count = countD < 1 ? 1 : (int) Math.round(countD);
                }
                if (count < maxDurationSec) {
                    String key = "gtceu.multiblock.output_line." + (model.chance < model.maxChance ? "2" : "0");
                    textList.add(Component.translatable(key, stack.getHoverName(), count,
                            FormattingUtil.formatNumber2Places(maxDurationSec / countD)));
                } else {
                    String key = "gtceu.multiblock.output_line." + (model.chance < model.maxChance ? "3" : "1");
                    textList.add(Component.translatable(key, stack.getHoverName(), count,
                            FormattingUtil.formatNumber2Places(countD / maxDurationSec)));
                }
            }
        }
    }
}
