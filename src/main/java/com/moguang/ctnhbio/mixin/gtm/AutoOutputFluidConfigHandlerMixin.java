package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.gui.widget.directional.handlers.AutoOutputFluidConfigHandler;
import com.lowdragmc.lowdraglib.gui.util.ClickData;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AutoOutputFluidConfigHandler.class,  remap = false)
public abstract class AutoOutputFluidConfigHandlerMixin {
//
//    @Shadow
//    private Direction side;
//
//    @Inject(
//            method = "handleClick",
//            at = @At("HEAD")
//
//    )
//    private void preHandleClick(ClickData cd, Direction direction, CallbackInfo ci) {
//
//        side = direction;
//
//    }
}

