package com.moguang.ctnhbio.common.item;

import com.github.elenterius.biomancy.api.serum.Serum;
import com.github.elenterius.biomancy.api.serum.SerumContainer;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import static net.minecraftforge.fluids.FluidUtil.getFluidContained;

public class OrganicVialItem extends ComponentItem implements SerumContainer {
    public OrganicVialItem(Properties properties) {
        super(properties);
    }

    public Fluid getFluid(ItemStack stack) {
        // 通过组件系统获取流体数据
        var fluidStack = getFluidContained(stack);
        return fluidStack.map(FluidStack::getFluid).orElse(null);
    }

    @Override
    public Serum getSerum() {
        return null;
    }
}
