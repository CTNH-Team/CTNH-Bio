package com.moguang.ctnhbio.api.recipe;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IOverclockMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.config.ConfigHolder;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

import static com.gregtechceu.gtceu.api.recipe.OverclockingLogic.NON_PERFECT_OVERCLOCK;

public class CBRecipeModifiers {

    public static @Nullable Component autoBatchMode(@NotNull MetaMachine machine, RecipeHandlerGroup group,
                                                    @NotNull GTRecipe recipe) {
        if (recipe.duration < ConfigHolder.INSTANCE.machines.batchDuration) {
            int parallel = ConfigHolder.INSTANCE.machines.batchDuration / recipe.duration;
            parallel = ParallelLogic.getParallelAmount(group, recipe, parallel, false);

            if (parallel <= 1) return null;

            recipe.multiplyInputs(parallel);
            recipe.multiplyOutputs(parallel);
            recipe.multiplyDuration(parallel);
            recipe.batchParallels *= parallel;
        }
        return null;
    }

    public static final RecipeModifier BIO_TIER_CHECK = (machine, group, recipe) -> {
        if (machine instanceof ITieredMachine tieredMachine) {
            if (recipe.tier > tieredMachine.getTier() + 1) {
                return Component.translatable("gtceu.recipe_modifier.insufficient_voltage");
            }
            return null;
        }
        return RecipeModifier.nullWrongType(ITieredMachine.class, machine);
    };

    public static final Function<OverclockingLogic, RecipeModifier> BIO_ELECTRIC_OVERCLOCK = Util
            .memoize(logic -> (machine, group, recipe) -> {
                Component tierCheck = BIO_TIER_CHECK.apply(machine, group, recipe);
                if (tierCheck != null) return tierCheck;
                if (!(machine instanceof IOverclockMachine overclockMachine)) return null;
                return logic.getModifier(machine, group, recipe, overclockMachine.getOverclockVoltage());
            });

    public static final RecipeModifier BIO_OC_NON_PERFECT = BIO_ELECTRIC_OVERCLOCK.apply(NON_PERFECT_OVERCLOCK);
}
