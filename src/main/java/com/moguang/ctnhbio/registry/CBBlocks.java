package com.moguang.ctnhbio.registry;

import com.github.elenterius.biomancy.block.membrane.IgnoreEntityCollisionPredicate;
import com.github.elenterius.biomancy.block.membrane.MembraneBlock;
import com.github.elenterius.biomancy.init.ModBlocks;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.common.data.models.GTModels;
import com.gregtechceu.gtceu.common.registry.GTRegistration;
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
    public static final BlockEntry<Block> SYNAPTIC_CASING = createCasingBlock("synaptic_casing",
            CTNHBio.id("block/casings/opv/opv_casing"));
    public static final BlockEntry<Block> CONSCIOUSNESS_LINKER = createCasingBlock("consciousness_linker",
            CTNHBio.id("block/casings/consciousness_linker"));
    public static final BlockEntry<Block> NEURAL_NETWORK_CASING = createCasingBlock("neural_network_casing",
            CTNHBio.id("block/casings/neural_network_casing"));
    public static final BlockEntry<Block> NEURAL_COOLING_CONDUIT = createCasingBlock("neural_cooling_conduit",
            CTNHBio.id("block/casings/neural_cooling_conduit"));
    public static final BlockEntry<Block> CONSCIOUSNESS_CONTROLLER = createCasingBlock("consciousness_controller",
            CTNHBio.id("block/casings/consciousness_controller"));

    public static final BlockEntry<MembraneBlock> CONSCIOUSNESS_SENSOR_GLASS = createMembraneBlock("consciousness_sensor_glass",
            CTNHBio.id("block/casings/consciousness_sensor_glass"), () -> RenderType::translucent);

    public static final BlockEntry<MembraneBlock> IMPERMEABLE_MEMBRANE = createMembraneBlock("impermeable_membrane",
            CTNHBio.id("block/membrane/impermeable_membrane"), () -> RenderType::translucent);

//    //联体桥
//    public static final BlockEntry<Block> PARABIOTIC_BRIDGE = REGISTRATE.block("parabiotic_bridge", Block::new)
//            .item(BlockItem::new)
//            .build()
//            .register();

    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }
    private static BlockEntry<Block> createGlassCasingBlock(String name, ResourceLocation texture, Supplier<Supplier<RenderType>> type) {
        return createCasingBlock(name, GlassBlock::new, texture, () -> Blocks.GLASS, type);
    }
    private static BlockEntry<MembraneBlock> createMembraneBlock(String name, ResourceLocation texture,
                                                                 Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, p -> new MembraneBlock(p, IgnoreEntityCollisionPredicate.NEVER))
                //.initialProperties(() -> Blocks.GLASS)
                .properties(p ->
                        p.isValidSpawn((state, level, pos, ent) -> false)
                        .noOcclusion()
                        .isRedstoneConductor(ModBlocks::neverValid)
                        .isSuffocating(ModBlocks::neverValid)
                        .isViewBlocking(ModBlocks::neverValid)
                )
                .addLayer(type)
                .exBlockstate(GTModels.cubeAllModel(texture))
                //.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
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
                .loot((loot, block) -> loot.add(block, loot.noDrop()))
                //.tag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.tryBuild("forge", "mineable/wrench")), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }


    public static void init() {}
}
