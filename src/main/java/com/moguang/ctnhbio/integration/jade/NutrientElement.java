package com.moguang.ctnhbio.integration.jade;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.theme.IThemeHelper;
import snownee.jade.api.ui.Element;
import snownee.jade.overlay.DisplayHelper;

public class NutrientElement extends Element {
    private String text;
    public NutrientElement(double nutrientCapacity, double nutrientAmount) {
        this.text = String.format(" %s/%s", DisplayHelper.dfCommas.format(nutrientAmount), DisplayHelper.dfCommas.format(nutrientCapacity));
    }
    @Override
    public Vec2 getSize() {
        Font font = Minecraft.getInstance().font;
        return new Vec2((float)(font.width(this.text)), 10.0F);
    }

    @Override
    public void render(GuiGraphics guiGraphics, float x, float y, float maxX, float maxY) {
        DisplayHelper.INSTANCE.drawText(guiGraphics, Component.translatable("jade.nutrient.info").getString() + this.text, x + 8.0F, y, IThemeHelper.get().getNormalColor());
    }
}
