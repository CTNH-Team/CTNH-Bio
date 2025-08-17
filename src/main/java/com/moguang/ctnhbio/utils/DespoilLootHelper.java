package com.moguang.ctnhbio.utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class DespoilLootHelper {
    public static List<ItemStack> generateDespsoilLoot(
            int despoilLevel, Entity entity, DamageSource source, ServerLevel level, float despoilChance
    ) {
        List<ItemStack> loot = new ArrayList<>();
        ResourceLocation lootTableId = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType())
                .withPrefix("biomancy/despoil/");
        LootTable lootTable = level.getServer().getLootData().getLootTable(lootTableId);

        if (lootTable != LootTable.EMPTY) {
            // 1. 创建战利品参数
            LootParams lootParams = new LootParams.Builder(level)
                    .withParameter(LootContextParams.THIS_ENTITY, entity)
                    .withParameter(LootContextParams.ORIGIN, entity.position())
                    .withParameter(LootContextParams.DAMAGE_SOURCE, source)
                    .create(LootContextParamSets.ENTITY);

            // 2. 生成基础战利品（仅一次）
            List<ItemStack> baseLoot = new ArrayList<>();
            Consumer<ItemStack> lootConsumer = stack -> {
                if (!stack.isEmpty()) baseLoot.add(stack.copy());
            };
            lootTable.getRandomItemsRaw(lootParams, lootConsumer);

            // 3. 直接乘以 despoilLevel
            for (ItemStack stack : baseLoot) {
                if (stack.hasTag()) {
                    // 特殊 NBT 物品：生成 despoilLevel 个独立堆叠
                    for (int i = 0; i < despoilLevel; i++) {
                        loot.add(stack.copy());
                    }
                } else {
                    // 普通物品：合并数量
                    ItemStack finalStack = stack.copy();
                    finalStack.setCount(stack.getCount() * despoilLevel);
                    loot.add(finalStack);
                }
            }
        }
        return loot;
    }
}
