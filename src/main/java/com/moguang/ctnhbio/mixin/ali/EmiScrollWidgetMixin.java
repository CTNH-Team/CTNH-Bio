package com.moguang.ctnhbio.mixin.ali;

import com.moguang.ctnhbio.utils.IKeyPressedWithCoord;
import com.yanny.ali.api.Rect;
import com.yanny.ali.compatibility.emi.EmiScrollWidget;
import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.api.widget.Widget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(value = EmiScrollWidget.class, remap = false)
public abstract class EmiScrollWidgetMixin implements IKeyPressedWithCoord {

    @Final
    @Shadow
    private List<Widget> widgets;

    @Final
    @Shadow
    private Rect contentRect;
    @Shadow
    private float scrollOffsetY;

    @Shadow
    protected abstract int getHiddenAmount();

    @Override
    public boolean ctnhbio$keyPressedWithCoord(int keyCode, int scanCode, int modifiers, int mouseX, int mouseY) {
        if (contentRect.contains(mouseX, mouseY)) {
            float scrollAmount = getHiddenAmount() * scrollOffsetY;
            for (Widget widget : widgets) {
                Bounds b = widget.getBounds();
                if (b.contains(mouseX, (int) (mouseY + scrollAmount))) {
                    if (widget.keyPressed(keyCode, scanCode, modifiers)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
