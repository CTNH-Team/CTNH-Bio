package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.machine.WorkableTieredMachine;
import com.gregtechceu.gtceu.api.machine.trait.IRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.machine.trait.MachineTrait;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Mixin(value = WorkableTieredMachine.class, remap = false)
public abstract class WorkableTieredMachineMixin {
    @Inject(method = "onLoad",
            at = @At(value = "INVOKE",
                    target = "Ljava/util/Map;computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;",
                    shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILSOFT)
    void bothIOHandlerSupport(CallbackInfo ci, Map<IO, List<IRecipeHandler<?>>> ioTraits, Iterator<?> var2, MachineTrait var3, IRecipeHandlerTrait<?> handlerTrait){
        if(handlerTrait.getHandlerIO() == IO.BOTH) {
            ioTraits.computeIfAbsent(IO.IN, i -> new ArrayList<>()).add(handlerTrait);
            ioTraits.computeIfAbsent(IO.OUT, i -> new ArrayList<>()).add(handlerTrait);
        }
    }
}
