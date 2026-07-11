package com.moguang.ctnhbio.client.Text;

import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.IChancedIngredient;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.network.chat.Component;

import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;

import java.util.List;

public class ModelOutputLine {

    public static void addModelOutputLine(IMultiController machine, List<Component> textList) {
        if (machine instanceof IRecipeLogicMachine recipeLogicMachine) {
            GTRecipe recipe = recipeLogicMachine.getRecipeLogic().getLastRecipe();
            if (recipe == null) return;
            var modelOutputs = recipe.getOutputContents(ModelRecipeCapability.CAP);
            for (var model : modelOutputs) {
                var stack = model.getItem();
                int recipeTier = recipe.tier;
                int chanceTier = recipeTier + recipe.ocLevel;
                var function = recipe.getType().getChanceFunction();
                double maxDurationSec = (double) recipe.duration / 20.0;
                int count = stack.getCount();
                double countD = count;
                if (model.isChanced()) {
                    countD = countD *
                            function.getBoostedChance(model.getChance(), recipeTier, chanceTier) /
                            IChancedIngredient.MAX_CHANCE;
                    count = countD < 1 ? 1 : (int) Math.round(countD);
                }
                if (count < maxDurationSec) {
                    String key = "gtceu.multiblock.output_line." + (model.isChanced() ? "2" : "0");
                    textList.add(Component.translatable(key, stack.getHoverName(), count,
                            FormattingUtil.formatNumber2Places(maxDurationSec / countD)));
                } else {
                    String key = "gtceu.multiblock.output_line." + (model.isChanced() ? "3" : "1");
                    textList.add(Component.translatable(key, stack.getHoverName(), count,
                            FormattingUtil.formatNumber2Places(countD / maxDurationSec)));
                }
            }
        }
    }
}
