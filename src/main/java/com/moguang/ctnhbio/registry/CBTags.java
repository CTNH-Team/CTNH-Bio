package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.moguang.ctnhbio.CTNHBio;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class CBTags {
    public static final TagKey<Block> GROWABLE_TAG = BlockTags.create(ResourceLocation.tryBuild("ctnhbio", "growable"));
    public static final TagKey<Block> GROWING_REPLACEABLE_TAG = BlockTags.create(ResourceLocation.tryBuild("ctnhbio", "growing_replaceable"));
    public static final TagKey<Block> HATCH_TAG = BlockTags.create(ResourceLocation.tryBuild("forge", "hatch"));
    public static final TagKey<Block> GROWABLE_BLOCK_TAG = BlockTags.create(CTNHBio.id("growable_block"));


    public static final TagKey<Item> FOOD_TAG = ItemTags.create(ResourceLocation.tryBuild("forge", "foods"));

    public static final TagKey<Fluid> NUTRIENT_FLUIDS_TAG = TagUtil.createFluidTag("nutrient");
    public static final TagKey<Fluid> ACID_FLUIDS_TAG = TagUtil.createFluidTag("stomach_acid");

}
