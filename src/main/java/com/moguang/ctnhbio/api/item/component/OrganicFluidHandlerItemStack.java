package com.moguang.ctnhbio.api.item.component;

import com.gregtechceu.gtceu.api.misc.forge.ThermalFluidHandlerItemStack;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;

public class OrganicFluidHandlerItemStack extends ThermalFluidHandlerItemStack implements IOrganicFluidHandler {

    public OrganicFluidHandlerItemStack(@NotNull ItemStack container, int capacity, int maxFluidTemperature,
                                        boolean gasProof, boolean acidProof, boolean cryoProof, boolean plasmaProof) {
        super(container, capacity, maxFluidTemperature, gasProof, acidProof, cryoProof, plasmaProof);
    }
}
