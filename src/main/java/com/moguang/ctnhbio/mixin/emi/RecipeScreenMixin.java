package com.moguang.ctnhbio.mixin.emi;

import com.llamalad7.mixinextras.sugar.Local;
import com.moguang.ctnhbio.utils.IKeyPressedWithCoord;
import dev.emi.emi.api.widget.Widget;
import dev.emi.emi.screen.RecipeScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = RecipeScreen.class, remap = true)
public class RecipeScreenMixin {
    @Redirect(
            method = "keyPressed",
            at = @At(value = "INVOKE",
                    target = "Ldev/emi/emi/api/widget/Widget;keyPressed(III)Z",
                    remap = false
            ),
            remap = true
    )
    public boolean keyPressed(Widget instance,
                              int keyCode,
                              int scanCode,
                              int modifiers,
                              @Local(name = "mx") int mx,
                              @Local(name = "my") int my
                              ) {
        if(instance instanceof IKeyPressedWithCoord keyPressedWithCoord)
            return keyPressedWithCoord.ctnhbio$keyPressedWithCoord(keyCode, scanCode, modifiers,mx,my);
        return instance.keyPressed(keyCode, scanCode, modifiers);
    }

}
