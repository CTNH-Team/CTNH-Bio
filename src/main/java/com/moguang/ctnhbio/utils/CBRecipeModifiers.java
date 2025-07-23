package com.moguang.ctnhbio.utils;

import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.moguang.ctnhbio.api.ILivingMachine;
import net.minecraft.world.effect.MobEffects;

public class CBRecipeModifiers {
    public static RecipeModifier BASIC_LIVING_MODIFIER = ((machine, recipe) -> {
        if (machine instanceof ILivingMachine livingMachine) {
            var builder = ModifierFunction.builder();
            var entity = livingMachine.getMachineEntity();
            if (entity.getEffect(MobEffects.DIG_SPEED) != null) {
                int tier = entity.getEffect(MobEffects.DIG_SPEED).getAmplifier();
                builder.durationMultiplier(Math.max(0, 1 - 0.2 * tier));
            }
            if (entity.getEffect(MobEffects.DIG_SLOWDOWN) != null) {
                int tier = entity.getEffect(MobEffects.DIG_SLOWDOWN).getAmplifier();
                builder.durationMultiplier(1 + tier);
            }
            if (entity.getEffect(MobEffects.DAMAGE_BOOST) != null) {
                int tier = entity.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier();
            }
        }
        return ModifierFunction.IDENTITY;
    });
}
