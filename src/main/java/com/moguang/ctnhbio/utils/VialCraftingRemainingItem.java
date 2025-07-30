package com.moguang.ctnhbio.utils;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IEdibleItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.api.item.component.IRecipeRemainder;
import com.moguang.ctnhbio.registry.CBItems;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

public class VialCraftingRemainingItem implements IRecipeRemainder {
    @Override
    public ItemStack getRecipeRemained(ItemStack itemStack) {
        return new ItemStack(CBItems.ORGANIC_VIAL);
    }
}
