package com.moguang.ctnhbio.api.item;

import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.lowdragmc.lowdraglib.client.renderer.IRenderer;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class LivingMachineItem extends MetaMachineItem {
    public LivingMachineItem(LivingMetaMachineBlock block, Properties properties) {
        super(block, properties);
    }

}
