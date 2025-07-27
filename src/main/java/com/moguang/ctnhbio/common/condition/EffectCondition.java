package com.moguang.ctnhbio.common.condition;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.moguang.ctnhbio.api.ILivingMachine;
import com.moguang.ctnhbio.registry.CBRecipeConditions;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EffectCondition extends RecipeCondition {
    public static final Codec<EffectCondition> CODEC = RecordCodecBuilder
            .create(instance -> RecipeCondition.isReverse(instance)
                    .and(Codec.STRING.listOf().fieldOf("mob_effect").forGetter(val ->
                            Arrays.stream(val.effects)
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
        for (String effect: effects) {
            if (BuiltInRegistries.MOB_EFFECT.containsKey(new ResourceLocation(effect))) {
                newEffect.add(BuiltInRegistries.MOB_EFFECT.get(new ResourceLocation(effect)));
            }
        }
        this.effects = newEffect.toArray(new MobEffect[0]);
    }

    @Override
    public RecipeConditionType<?> getType() {
        return CBRecipeConditions.EFFECT;
    }

    @Override
    public Component getTooltips() {
        return Component.translatable("recipe.condition.effect.tooltip", getEffectName());
    }
    public String getEffectName() {
        String name = "";
        int count = 0;
        for (MobEffect effect: effects) {
            if (count != 0) {
                name += ", ";
            }
            name += effect.getDisplayName().getString();
            count ++;
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
            for (MobEffect effect: effects) {
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

    @NotNull
    @Override
    public JsonObject serialize() {
        JsonObject config = super.serialize();
        JsonArray effectsArray = new JsonArray();
        for (MobEffect effect : effects) {
            effectsArray.add(BuiltInRegistries.MOB_EFFECT.getKey(effect).toString());
        }
        config.add("mob_effect", effectsArray);
        return config;
    }

    @Override
    public RecipeCondition deserialize(@NotNull JsonObject config) {
        super.deserialize(config);
        JsonArray effectsArray = GsonHelper.getAsJsonArray(config, "mob_effect");
        List<String> effectStrings = new ArrayList<>();
        for (JsonElement element : effectsArray) {
            effectStrings.add(element.getAsString());
        }
        this.effects = effectStrings.stream()
                .map(ResourceLocation::new)
                .filter(BuiltInRegistries.MOB_EFFECT::containsKey)
                .map(BuiltInRegistries.MOB_EFFECT::get)
                .toArray(MobEffect[]::new);
        return this;
    }

    @Override
    public RecipeCondition fromNetwork(FriendlyByteBuf buf) {
        super.fromNetwork(buf);
        int effectCount = buf.readVarInt();
        this.effects = new MobEffect[effectCount];
        for (int i = 0; i < effectCount; i++) {
            ResourceLocation effectId = buf.readResourceLocation();
            this.effects[i] = BuiltInRegistries.MOB_EFFECT.get(effectId);
        }
        return this;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf) {
        super.toNetwork(buf);
        buf.writeVarInt(effects.length);
        for (MobEffect effect : effects) {
            buf.writeResourceLocation(BuiltInRegistries.MOB_EFFECT.getKey(effect));
        }
    }
}
