package com.moguang.ctnhbio.mixin.biomancy;

import com.github.elenterius.biomancy.api.serum.Serum;
import com.github.elenterius.biomancy.api.serum.SerumContainer;
import com.github.elenterius.biomancy.client.gui.InjectorScreen;
import com.moguang.ctnhbio.common.item.OrganicVialItem;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.github.elenterius.biomancy.client.gui.InjectorScreen.CANCEL_ID;
import static com.github.elenterius.biomancy.client.gui.InjectorScreen.CLEAR_ID;

@Mixin(value = InjectorScreen.class, remap = false)
public class InjectorScreenMixin {
    /**
     * @author
     * @reason
     */
    @Overwrite
    private Object2IntMap<ItemStack> findSerumStacks(LocalPlayer player) {
        Object2IntMap<ItemStack> foundStacks = new Object2IntArrayMap<>();
        Object2IntMap<Serum> foundSerums = new Object2IntArrayMap<>();

        foundStacks.put(new ItemStack(Items.BARRIER), CANCEL_ID);

        Inventory inventory = player.getInventory();
        int slots = inventory.getContainerSize();
        for (int idx = 0; idx < slots; idx++) {
            ItemStack stack = inventory.getItem(idx);
            Item item = stack.getItem();
            if (item instanceof SerumContainer vial) {
                Serum serum = vial.getSerum();
                if(serum.isEmpty()) serum = OrganicVialItem.getSerumFromStack(stack);
                if (!serum.isEmpty()) {
                    if (!foundSerums.containsKey(serum)) {
                        foundStacks.put(stack, idx);
                    }
                    foundSerums.mergeInt(serum, stack.getCount(), Integer::sum);
                }
            }
        }

        //		if (foundStacks.size() > 1) {
        foundStacks.put(ItemStack.EMPTY, CLEAR_ID);
        return foundStacks;
        //		}

        //not items were found
        //		return Object2IntMaps.emptyMap();
    }
}
