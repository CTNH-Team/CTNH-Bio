package com.moguang.ctnhbio.api.recipe.ingredient.entity;

import com.gregtechceu.gtceu.api.recipe.ingredient.IChancedIngredient;

import net.minecraft.world.entity.Entity;

import lombok.Getter;
import org.jetbrains.annotations.Range;

public final class ChancedEntityIngredient extends EntityIngredient implements IChancedIngredient {

    @Getter
    private final EntityIngredient inner;
    private final int chance;
    @Getter
    private final int multiplier;

    public ChancedEntityIngredient(EntityIngredient ingredient, @Range(from = 0, to = 10000) int chance,
                                   int multiplier) {
        super(ingredient.values, ingredient.count * multiplier, ingredient.nbt == null ? null : ingredient.nbt.copy());
        this.inner = ingredient;
        this.chance = chance;
        this.multiplier = multiplier;
    }

    public ChancedEntityIngredient(EntityIngredient ingredient, int chance) {
        this(ingredient, chance, 1);
    }

    @Override
    public int hash() {
        return inner.hash();
    }

    @Override
    public boolean test(Entity entity) {
        return inner.test(entity);
    }

    @Override
    public boolean isChanced() {
        return true;
    }

    @Override
    public int getChance() {
        return chance;
    }

    @Override
    public ChancedEntityIngredient copy() {
        return new ChancedEntityIngredient(inner.copy(), chance, multiplier);
    }

    @Override
    public EntityIngredient copyWithCount(int count) {
        return inner.copyWithCount(count);
    }

    @Override
    public ChancedEntityIngredient copyWithMultiplier(int multiplier) {
        return new ChancedEntityIngredient(inner.copy(), chance, this.multiplier * multiplier);
    }

    @Override
    public ChancedEntityIngredient copyWithChance(int chance) {
        return new ChancedEntityIngredient(inner.copy(), chance, multiplier);
    }
}
