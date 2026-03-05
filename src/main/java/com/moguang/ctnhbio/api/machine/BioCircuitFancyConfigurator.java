package com.moguang.ctnhbio.api.machine;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.CircuitFancyConfigurator;
import com.gregtechceu.gtceu.common.item.IntCircuitBehaviour;

import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.texture.ItemStackTexture;
import com.lowdragmc.lowdraglib.gui.widget.ButtonWidget;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import com.moguang.ctnhbio.api.gui.CBGuiTextures;

public class BioCircuitFancyConfigurator extends CircuitFancyConfigurator {

    ItemStackHandler circuitSlot;

    public BioCircuitFancyConfigurator(ItemStackHandler circuitSlot) {
        super(circuitSlot);
        this.circuitSlot = circuitSlot;
    }

    @Override
    public Widget createConfigurator() {
        WidgetGroup group = new WidgetGroup(0, 0, 174, 132);
        group.addWidget(new LabelWidget(9, 8, "Programmed Circuit Configuration"));
        group.addWidget((new SlotWidget(this.circuitSlot, 0, (group.getSize().width - 18) / 2, 20, false, false))
                .setBackground(new GuiTextureGroup(CBGuiTextures.SLOT, GuiTextures.INT_CIRCUIT_OVERLAY)));
        group.addWidget(
                new ButtonWidget((group.getSize().width - 18) / 2, 20, 18, 18, IGuiTexture.EMPTY, (clickData) -> {
                    if (!clickData.isRemote) {
                        this.circuitSlot.setStackInSlot(0, ItemStack.EMPTY);
                    }

                }));

        int idx = 0;

        int x;
        int finalIdx;
        for (x = 0; x <= 2; ++x) {
            for (finalIdx = 0; finalIdx <= 8; ++finalIdx) {
                int finalIdx1 = idx;
                group.addWidget(new ButtonWidget(5 + 18 * finalIdx, 48 + 18 * x, 18, 18,
                        new GuiTextureGroup(new IGuiTexture[] { CBGuiTextures.SLOT,
                                (new ItemStackTexture(new ItemStack[] { IntCircuitBehaviour.stack(idx) }))
                                        .scale(0.8888889F) }),
                        (clickData) -> {
                            if (!clickData.isRemote) {
                                this.circuitSlot.setStackInSlot(0, IntCircuitBehaviour.stack(finalIdx1));
                            }
                        }));
                ++idx;
            }
        }

        for (x = 0; x <= 5; ++x) {
            finalIdx = x + 27;
            int finalIdx2 = finalIdx;
            group.addWidget(new ButtonWidget(5 + 18 * x, 102, 18, 18,
                    new GuiTextureGroup(new IGuiTexture[] { CBGuiTextures.SLOT,
                            (new ItemStackTexture(new ItemStack[] { IntCircuitBehaviour.stack(finalIdx) }))
                                    .scale(0.8888889F) }),
                    (clickData) -> {
                        if (!clickData.isRemote) {
                            this.circuitSlot.setStackInSlot(0, IntCircuitBehaviour.stack(finalIdx2));
                        }
                    }));
        }

        return group;
    }
}
