package com.moguang.ctnhbio.data.loot;

import com.github.elenterius.biomancy.init.ModBlocks;
import com.moguang.ctnhbio.registry.CBTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static com.github.elenterius.biomancy.init.ModBlocks.*;

public class CBLootTableProvider extends LootTableProvider {
    // 需要移除掉落的方块列表
    private static final Set<Supplier<? extends Block>> BLOCKS_TO_REMOVE_DROPS = Set.of(
            FLESH,
            FLESH_FENCE,
            PACKED_FLESH,
            FIBROUS_FLESH,
            ORNATE_FLESH,
            ORNATE_FLESH_SLAB,
            PRIMAL_FLESH,
            SMOOTH_PRIMAL_FLESH,
            POROUS_PRIMAL_FLESH,
            MALIGNANT_FLESH
    );

    public CBLootTableProvider(PackOutput output) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(NoDropBlockLoot::new, LootContextParamSets.BLOCK)
        ));
    }


    private static class NoDropBlockLoot implements LootTableSubProvider {


        @Override
        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
            // 为每个方块创建空战利品表
            BLOCKS_TO_REMOVE_DROPS.forEach(block -> {
                consumer.accept(
                        block.get().getLootTable(),
                        LootTable.lootTable() // 空的战利品表
                );
            });

//            ForgeRegistries.BLOCKS.getValues().forEach(block -> {
//                if (block.defaultBlockState().is(CBTags.GROWABLE_BLOCK_TAG)) {
//                    consumer.accept(
//                            block.getLootTable(),
//                            LootTable.lootTable() // 空表
//                    );
//                }
//            });
        }
    }
}
