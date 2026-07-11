package com.moguang.ctnhbio.common.condition;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.moguang.ctnhbio.api.ILivingMachine;
import com.moguang.ctnhbio.registry.CBRecipeConditions;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.NotNull;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EffectCondition extends RecipeCondition<EffectCondition> {

    public static final Codec<EffectCondition> CODEC = RecordCodecBuilder
            .create(instance -> RecipeCondition.isReverse(instance)
                    .and(Codec.STRING.listOf().fieldOf("mob_effect").forGetter(val -> Arrays.stream(val.effects)
                            .map(effect -> BuiltInRegistries.MOB_EFFECT.getKey(effect).toString())
                            .collect(Collectors.toList())))
                    .apply(instance, EffectCondition::new));
    private MobEffect[] effects;
    public final static EffectCondition INSTANCE = new EffectCondition();

    public EffectCondition(MobEffect... effects) {
        super();
        this.effects = effects;
    }

    public EffectCondition(boolean isReverse, List<String> effects) {
        super(isReverse);
        List<MobEffect> newEffect = new ArrayList<>();
        for (String effect : effects) {
            if (BuiltInRegistries.MOB_EFFECT.containsKey(ResourceLocation.parse(effect))) {
                newEffect.add(BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.parse(effect)));
            }
        }
        this.effects = newEffect.toArray(new MobEffect[0]);
    }

    @Override
    public RecipeConditionType<EffectCondition> getType() {
        return CBRecipeConditions.EFFECT;
    }

    @CN("药水效果：%s")
    @EN("Potion Effect : %s")
    static Lang tooltip;

    @Override
    public Component getTooltips() {
        return tooltip.translate(getEffectName());
        // return Component.translatable("recipe.condition.effect.tooltip", getEffectName());
    }

    public String getEffectName() {
        String name = "";
        int count = 0;
        for (MobEffect effect : effects) {
            if (count != 0) {
                name += ", ";
            }
            name += effect.getDisplayName().getString();
            count++;
        }
        return name;
    }

    @Override
    public boolean testCondition(@NotNull GTRecipe gtRecipe, @NotNull RecipeLogic recipeLogic) {
        if (recipeLogic.getMachine() instanceof ILivingMachine livingMachine) {
            var entity = livingMachine.getMachineEntity();
            if (entity == null) {
                return false;
            }
            for (MobEffect effect : effects) {
                if (entity.getEffect(effect) == null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public EffectCondition createTemplate() {
        return new EffectCondition();
    }

    @Override
    public boolean perTick() {
        return true;
    }
}
