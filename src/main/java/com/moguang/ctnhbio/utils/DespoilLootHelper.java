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
            LootParams lootParams = new LootParams.Builder(level)
                    .withParameter(LootContextParams.THIS_ENTITY, entity)
                    .withParameter(LootContextParams.ORIGIN, entity.position())
                    .withParameter(LootContextParams.DAMAGE_SOURCE, source)
                    .create(LootContextParamSets.ENTITY);

            Consumer<ItemStack> lootConsumer = stack -> {
                if (!stack.isEmpty()) loot.add(stack);
            };

            for (int i = 0; i < despoilLevel; i++) {
                if (level.getRandom().nextFloat() <= despoilChance) {
                    lootTable.getRandomItemsRaw(lootParams, lootConsumer);
                }
            }
        }
        return loot;
    }
}
