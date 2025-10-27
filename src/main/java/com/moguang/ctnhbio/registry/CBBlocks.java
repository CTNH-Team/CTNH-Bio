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
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
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
            "血肉机械方块",
            CTNHBio.id("block/casings/flesh_casing"));
    public static final BlockEntry<Block> PRIMAL_FLESH_CASING = createCasingBlock("primal_flesh_casing",
            "原初机械方块",
            CTNHBio.id("block/casings/primal_flesh_casing"));
    public static final BlockEntry<Block> ORNATE_FLESH_CASING = createCasingBlock("ornate_flesh_casing",
            "装饰性机械方块",
            CTNHBio.id("block/casings/ornate_flesh_casing"));
    public static final BlockEntry<Block> ACID_FLESH_CASING = createCasingBlock("acid_flesh_casing",
            "酸液机械方块",
            CTNHBio.id("block/casings/acid_flesh_casing"));
    public static final BlockEntry<Block> BIO_ACID_CASING = createCasingBlock("bio_acid_casing",
            "生物酸机械方块",
            CTNHBio.id("block/casings/bio_acid_casing"));
    public static final BlockEntry<Block> SYNAPTIC_CASING = createCasingBlock("synaptic_casing",
            "神经突触机械方块",
            CTNHBio.id("block/casings/opv/opv_casing"));
    public static final BlockEntry<Block> CONSCIOUSNESS_LINKER = createCasingBlock("consciousness_linker",
            "意识链接器",
            CTNHBio.id("block/casings/consciousness_linker"));
    public static final BlockEntry<Block> NEURAL_NETWORK_CASING = createCasingBlock("neural_network_casing",
            "神经网络外壳",
            CTNHBio.id("block/casings/neural_network_casing"));
    public static final BlockEntry<Block> NEURAL_COOLING_CONDUIT = createCasingBlock("neural_cooling_conduit",
            "神经冷却导管",
            CTNHBio.id("block/casings/neural_cooling_conduit"));

    public static final BlockEntry<Block> CONSCIOUSNESS_CONTROLLER =
            createCasingBlockWithProperties("consciousness_controller",
            "意识控制器",
                    p -> p.isValidSpawn((state, level, pos, ent) -> false)
                            .lightLevel(s ->15),
            CTNHBio.id("block/casings/consciousness_controller"));

    public static final BlockEntry<Block> CONSCIOUSNESS_SENSOR_GLASS =
            createGlassCasingBlockWithProperties("consciousness_sensor_glass",
            "意识传感玻璃",
                    p -> p.isValidSpawn((state, level, pos, ent) -> false)
                            .lightLevel(s ->10),
            CTNHBio.id("block/casings/consciousness_sensor_glass"),
            () -> RenderType::translucent);

    public static final BlockEntry<MembraneBlock> IMPERMEABLE_MEMBRANE = createMembraneBlock("impermeable_membrane",
            "不渗透膜",
            CTNHBio.id("block/membrane/impermeable_membrane"),
            () -> RenderType::translucent);

//    //联体桥
//    public static final BlockEntry<Block> PARABIOTIC_BRIDGE = REGISTRATE.block("parabiotic_bridge", Block::new)
//            .item(BlockItem::new)
//            .build()
//            .register();

//    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
//        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
//                () -> RenderType::cutoutMipped);
//    }
    public static BlockEntry<Block> createCasingBlock(String name, String cnname, ResourceLocation texture) {
        return createCasingBlock(name, cnname,  Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }

    public static BlockEntry<Block> createCasingBlockWithProperties(String name, String cnname, NonNullUnaryOperator<BlockBehaviour.Properties> properties,ResourceLocation texture) {
        return createCasingBlock(
                name,
                cnname,
                Block::new,
                texture,
                () -> Blocks.IRON_BLOCK,
                properties,
                () -> RenderType::cutoutMipped);
    }

    private static BlockEntry<Block> createGlassCasingBlock(String name, String cnname, ResourceLocation texture, Supplier<Supplier<RenderType>> type) {
        return createCasingBlock(name, cnname,  GlassBlock::new, texture, () -> Blocks.GLASS, type);
    }

    private static BlockEntry<Block> createGlassCasingBlockWithProperties(String name, String cnname, NonNullUnaryOperator<BlockBehaviour.Properties> properties,ResourceLocation texture, Supplier<Supplier<RenderType>> type) {
        return createCasingBlock(name, cnname,  GlassBlock::new, texture, () -> Blocks.GLASS, properties, type);
    }

    private static BlockEntry<MembraneBlock> createMembraneBlock(String name, String cnname, ResourceLocation texture,
                                                                 Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, p -> new MembraneBlock(p, IgnoreEntityCollisionPredicate.NEVER))
                .cnlang(cnname)
                .initialProperties(() -> Blocks.GLASS)
                .properties(p ->
                        p.isValidSpawn((state, level, pos, ent) -> false)
                        .noOcclusion()
                        .isRedstoneConductor(ModBlocks::neverValid)
                        .isSuffocating(ModBlocks::neverValid)
                        .isViewBlocking(ModBlocks::neverValid)
                )
                .addLayer(type)
                .exBlockstate(GTModels.cubeAllModel(texture))
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    public static BlockEntry<Block> createCasingBlock(String name,
                                                      String cnname,
                                                      NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                      ResourceLocation texture,
                                                      NonNullSupplier<? extends Block> baseProperties,
                                                      NonNullUnaryOperator<BlockBehaviour.Properties> properties,
                                                      Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .cnlang(cnname)
                .initialProperties(baseProperties)
                .properties(properties)
                .addLayer(type)
                .blockstate((ctx, prov) -> {
                    prov.simpleBlock(ctx.getEntry(), prov.models().cubeAll(name, texture));
                })
                .tag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.tryBuild("forge", "mineable/wrench")), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    public static BlockEntry<Block> createCasingBlock(String name,
                                                      String cnname,
                                                      NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                      ResourceLocation texture,
                                                      NonNullSupplier<? extends Block> properties,
                                                      Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .cnlang(cnname)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .blockstate((ctx, prov) -> {
                    prov.simpleBlock(ctx.getEntry(), prov.models().cubeAll(name, texture));
                })
                .tag(TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.tryBuild("forge", "mineable/wrench")), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    public static void init() {}
}
