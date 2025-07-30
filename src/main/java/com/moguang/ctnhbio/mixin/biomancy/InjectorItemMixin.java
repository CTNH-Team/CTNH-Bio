package com.moguang.ctnhbio.mixin.biomancy;

import com.github.elenterius.biomancy.api.serum.Serum;
import com.github.elenterius.biomancy.api.serum.SerumContainer;
import com.github.elenterius.biomancy.item.injector.InjectorItem;
import com.moguang.ctnhbio.common.item.OrganicVialItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.github.elenterius.biomancy.item.injector.InjectorItem.getItemHandler;


@Mixin(value = InjectorItem.class, remap = false)
public class InjectorItemMixin extends Item {
    public InjectorItemMixin(Properties p_41383_) {
        super(p_41383_);
    }

    /**
     * @author luckyblock
     * @reason support fluid container
     */
    @Overwrite
    public Serum getSerum(ItemStack stack) {
        if(getItemHandler(stack).isPresent())
        {
            var vial = getItemHandler(stack).get().getStack();
            return OrganicVialItem.getSerumFromStack(vial);
        }
        return Serum.EMPTY;
    }

}
