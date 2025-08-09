package com.moguang.ctnhbio.data.tags;

import com.github.elenterius.biomancy.init.ModBlocks;
import com.moguang.ctnhbio.registry.CBTags;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class BlockTags {
    public static void init(RegistrateTagsProvider<Block> provider) {
        create(provider, CBTags.GROWING_REPLACEABLE_TAG, ModBlocks.FLESH.get());
    }

    public static void create(RegistrateTagsProvider<Block> provider, TagKey<Block> tagKey, Block... rls) {
        var builder = provider.addTag(tagKey);
        for (Block block : rls) {
            builder.addOptional(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
        }
    }
}
