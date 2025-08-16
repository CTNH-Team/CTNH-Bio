package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class CBTags {
    public static final TagKey<Block> GROWABLE_TAG = BlockTags.create(new ResourceLocation("ctnhbio", "growable"));
    public static final TagKey<Block> GROWING_REPLACEABLE_TAG = BlockTags.create(new ResourceLocation("ctnhbio", "growing_replaceable"));
    public static final TagKey<Block> HATCH_TAG = BlockTags.create(new ResourceLocation("forge", "hatch"));

    public static final TagKey<Item> FOOD_TAG = ItemTags.create(new ResourceLocation("forge", "foods"));

    public static final TagKey<Fluid> NUTRIENT_FLUIDS = TagUtil.createFluidTag("nutrient");

}
