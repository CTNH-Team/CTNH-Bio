package com.moguang.ctnhbio.api.gui;

import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyUIProvider;
import com.lowdragmc.lowdraglib.gui.texture.ResourceBorderTexture;

public class LivingMachineUIWidget extends FancyMachineUIWidget {
    public LivingMachineUIWidget(IFancyUIProvider mainPage, int width, int height) {
        super(mainPage, width, height);

        ResourceBorderTexture background = new ResourceBorderTexture(
                "ctnhbio:textures/gui/biobase/background_bio.png", 16, 16, 4, 4);
        setBackground(background);
        assert playerInventory != null;
        playerInventory.setSlotBackground(CBGuiTextures.SLOT_BIO);

    }
}
