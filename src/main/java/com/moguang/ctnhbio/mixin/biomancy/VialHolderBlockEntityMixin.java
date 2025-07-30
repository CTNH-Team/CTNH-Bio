package com.moguang.ctnhbio.mixin.biomancy;

import com.github.elenterius.biomancy.api.serum.Serum;
import com.github.elenterius.biomancy.api.serum.SerumContainer;
import com.github.elenterius.biomancy.block.vialholder.VialHolderBlockEntity;
import com.moguang.ctnhbio.common.item.OrganicVialItem;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = VialHolderBlockEntity.class, remap = false)
public class VialHolderBlockEntityMixin {

    @Shadow
    @Final
    private ItemStackHandler inventory;

    /**
     * @author
     * @reason
     */
    @Overwrite
    public int getVialColor(int slot) {
        if (slot < 0 || slot >= inventory.getSlots()) return 0xFFFFFFFF;
        if (inventory.getStackInSlot(slot).getItem() instanceof SerumContainer container) {
            return OrganicVialItem.getSerumFromStack(inventory.getStackInSlot(slot)).getColor();
        }
        return 0xFFFFFFFF;
    }
}
