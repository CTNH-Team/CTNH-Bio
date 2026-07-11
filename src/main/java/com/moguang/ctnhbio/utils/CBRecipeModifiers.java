package com.moguang.ctnhbio.utils;

import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;

import net.minecraft.world.effect.MobEffects;

import com.moguang.ctnhbio.api.ILivingMachine;

public class CBRecipeModifiers {

    public static final RecipeModifier BASIC_LIVING_MODIFIER = (machine, group, recipe) -> {
        if (machine instanceof ILivingMachine livingMachine) {
            var entity = livingMachine.getMachineEntity();
            if (entity == null) {
                return null;
            }
            if (entity.getEffect(MobEffects.DIG_SPEED) != null) {
                int tier = entity.getEffect(MobEffects.DIG_SPEED).getAmplifier();
                recipe.multiplyDuration(Math.max(0, 1 - 0.2 * tier));
            }
            if (entity.getEffect(MobEffects.DIG_SLOWDOWN) != null) {
                int tier = entity.getEffect(MobEffects.DIG_SLOWDOWN).getAmplifier();
                recipe.multiplyDuration(1 + tier);
            }
            if (entity.getEffect(MobEffects.DAMAGE_BOOST) != null) {
                int tier = entity.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier();
            }
        }
        return null;
    };
}
