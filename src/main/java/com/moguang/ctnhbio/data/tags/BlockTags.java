package com.moguang.ctnhbio.data.tags;

import com.github.elenterius.biomancy.init.ModBlocks;
import com.moguang.ctnhbio.registry.CBBlocks;
import com.moguang.ctnhbio.registry.CBTags;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Supplier;

import static com.github.elenterius.biomancy.init.ModBlocks.*;
import static com.moguang.ctnhbio.registry.CBBlocks.*;


public class BlockTags {
    public static void init(RegistrateTagsProvider<Block> provider) {
        create(provider, CBTags.GROWING_REPLACEABLE_TAG, FLESH);
        create(provider, CBTags.GROWABLE_BLOCK_TAG,
                FLESH,
                FLESH_FENCE,
                PACKED_FLESH,
                FIBROUS_FLESH,
                ORNATE_FLESH,
                ORNATE_FLESH_SLAB,
                PRIMAL_FLESH,
                SMOOTH_PRIMAL_FLESH,
                POROUS_PRIMAL_FLESH,
                MALIGNANT_FLESH,


                FLESH_CASING,
                PRIMAL_FLESH_CASING,
                ORNATE_FLESH_CASING,
                ACID_FLESH_CASING,
                BIO_ACID_CASING
                );
    }

    public static void create(RegistrateTagsProvider<Block> provider, TagKey<Block> tagKey, Supplier<? extends Block> ... rls) {
        var builder = provider.addTag(tagKey);
        for (var block : rls) {
            builder.addOptional(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())));
        }
    }
}
