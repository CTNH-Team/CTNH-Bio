package com.moguang.ctnhbio.mixin.hostilenetworks;

import dev.shadowsoffire.hostilenetworks.data.CachedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CachedModel.class, remap = false)
public class CacheModelMixin {
    @Inject(
            method = "getAccuracy",
            at = @At(value = "TAIL"),
            cancellable = true
    )
    public void getAccuracyMixin(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(Math.max(cir.getReturnValue(), 0.1f));
    }
}
