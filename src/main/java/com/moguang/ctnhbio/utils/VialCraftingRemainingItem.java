package com.moguang.ctnhbio.utils;

import com.gregtechceu.gtceu.api.item.component.IRecipeRemainder;

import net.minecraft.world.item.ItemStack;

import com.moguang.ctnhbio.registry.CBItems;

public class VialCraftingRemainingItem implements IRecipeRemainder {

    @Override
    public ItemStack getRecipeRemained(ItemStack itemStack) {
        return new ItemStack(CBItems.ORGANIC_VIAL);
    }
}
