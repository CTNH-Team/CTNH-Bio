package com.moguang.ctnhbio.common.recipe;

import fr.lucreeper74.createmetallurgy.content.blocks.industrial_crucible.foundry.recipes.EntityIngredient;
import lombok.Getter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;
@Getter
public class MobCrushingRecipe {

    private final EntityType<?> entityType;
    private final ItemStack result;
    private final float chance; // 掉落概率（0.0~1.0）
    private final int minAmount; // 最小数量
    private final int maxAmount; // 最大数量

    public MobCrushingRecipe(EntityType<?> entityType, ItemStack result, float chance, int minAmount, int maxAmount) {
        this.entityType = entityType;
        this.result = result;
        this.chance = chance;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    // 检查生物是否匹配此配方
    public boolean matches(Entity entity) {
        return entity.getType() == entityType;
    }

    // 生成随机数量和概率的产物（可能返回空）
    public Optional<ItemStack> rollResult() {
        if (Math.random() > chance) {
            return Optional.empty(); // 未命中概率
        }
        int amount = minAmount + (int) (Math.random() * (maxAmount - minAmount + 1));
        ItemStack stack = result.copy();
        stack.setCount(amount);
        return Optional.of(stack);
    }

    public EntityIngredient.EntityInput getDisplay() {

        return new EntityIngredient.EntityInput(entityType);
    }
}
