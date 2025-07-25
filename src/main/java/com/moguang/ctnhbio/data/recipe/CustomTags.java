package com.moguang.ctnhbio.data.recipe;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class CustomTags {
    public static final TagKey<Fluid> ORGANIC_FLUIDS = TagUtil.createFluidTag("organic");
}
