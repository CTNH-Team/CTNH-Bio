package com.moguang.ctnhbio.api.gui;

import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyUIProvider;
import com.lowdragmc.lowdraglib.gui.texture.ResourceBorderTexture;

public class LivingMachineUIWidget extends FancyMachineUIWidget {
    public LivingMachineUIWidget(IFancyUIProvider mainPage, int width, int height) {
        super(mainPage, width, height);;
        setBackground(CBGuiTextures.BACKGROUND_SMALL);
        assert playerInventory != null;
        playerInventory.setSlotBackground(CBGuiTextures.SLOT_BIO);
        if (this.titleBar != null) {
            this.titleBar.widgets.forEach(widget -> widget.setBackground(CBGuiTextures.TITLE_BAR_BACKGROUND_BIO));
        }
        if (this.getSideTabsWidget() != null) {
            this.sideTabsWidget.setTabTexture(CBGuiTextures.TAB_LEFT_BIO.getSubTexture(0, 1 / 3f, 0.5f, 1 / 3f));
            this.sideTabsWidget.setTabHoverTexture(CBGuiTextures.TAB_LEFT_BIO.getSubTexture(0.5f, 1 / 3f, 0.5f, 1 / 3f));
            this.sideTabsWidget.setTabPressedTexture(CBGuiTextures.TAB_LEFT_BIO.getSubTexture(0.5f, 1 / 3f, 0.5f, 1 / 3f));
        }
        if (this.getConfiguratorPanel() != null) {
            this.configuratorPanel.setTexture(CBGuiTextures.BACKGROUND_SMALL);
        }
    }
}
