package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.moguang.ctnhbio.common.condition.EffectCondition;

public class CBRecipeConditions {
    public static void init() {}
    public static final RecipeConditionType<EffectCondition> EFFECT = GTRegistries.RECIPE_CONDITIONS.register("effect",
            new RecipeConditionType<>(EffectCondition::new, EffectCondition.CODEC));
}
