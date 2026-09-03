package com.moguang.ctnhbio.api.gui;

import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyUIProvider;

public class LivingMachineUIWidget extends FancyMachineUIWidget {

    public LivingMachineUIWidget(IFancyUIProvider mainPage, int width, int height) {
        super(mainPage, width, height);;
        setBackground(CBGuiTextures.BACKGROUND_BIO);
        playerInventory.setSlotBackground(CBGuiTextures.SLOT);
        this.titleBar.widgets.forEach(widget -> widget.setBackground(CBGuiTextures.TITLE_BAR_BACKGROUND_BIO));
        this.sideTabsWidget.setTabTexture(CBGuiTextures.TAB_LEFT_BIO.getSubTexture(0, 1 / 3f, 0.5f, 1 / 3f));
        this.sideTabsWidget
                .setTabHoverTexture(CBGuiTextures.TAB_LEFT_BIO.getSubTexture(0.5f, 1 / 3f, 0.5f, 1 / 3f));
        this.sideTabsWidget
                .setTabPressedTexture(CBGuiTextures.TAB_LEFT_BIO.getSubTexture(0.5f, 1 / 3f, 0.5f, 1 / 3f));
        this.configuratorPanel.setTexture(CBGuiTextures.BACKGROUND_SMALL);
        this.rightConfiguratorPanel.setTexture(CBGuiTextures.BACKGROUND_SMALL);
    }
}
