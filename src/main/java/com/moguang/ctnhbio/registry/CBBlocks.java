package com.moguang.ctnhbio.registry;

import com.moguang.ctnhbio.CTNHBio;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBBlocks {

    public static final BlockEntry<Block> FLESH_CASING = createCasingBlock("flesh_casing",
            CTNHBio.id("block/casings/flesh_casing"));
    public static final BlockEntry<Block> PRIMAL_FLESH_CASING = createCasingBlock("primal_flesh_casing",
            CTNHBio.id("block/casings/primal_flesh_casing"));
    public static final BlockEntry<Block> ORNATE_FLESH_CASING = createCasingBlock("ornate_flesh_casing",
            CTNHBio.id("block/casings/ornate_flesh_casing"));
    public static final BlockEntry<Block> ACID_FLESH_CASING = createCasingBlock("acid_flesh_casing",
            CTNHBio.id("block/casings/acid_flesh_casing"));
    public static final BlockEntry<Block> BIO_ACID_CASING = createCasingBlock("bio_acid_casing",
            CTNHBio.id("block/casings/bio_acid_casing"));

    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }
    private static BlockEntry<Block> createGlassCasingBlock(String name, ResourceLocation texture, Supplier<Supplier<RenderType>> type) {
        return createCasingBlock(name, GlassBlock::new, texture, () -> Blocks.GLASS, type);
    }
    @SuppressWarnings("all")
    public static BlockEntry<Block> createCasingBlock(String name,
                                                      NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                      ResourceLocation texture,
                                                      NonNullSupplier<? extends Block> properties,
                                                      Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .blockstate((ctx, prov) -> {
                    prov.simpleBlock(ctx.getEntry(), prov.models().cubeAll(name, texture));
                })
                .tag(TagKey.create(BuiltInRegistries.BLOCK.key(), new ResourceLocation("forge", "mineable/wrench")), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    public static void init() {}
}
