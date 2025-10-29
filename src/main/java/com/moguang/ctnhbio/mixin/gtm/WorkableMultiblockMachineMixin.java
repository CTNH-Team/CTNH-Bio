package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.IRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.machine.trait.MachineTrait;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Mixin(value = WorkableMultiblockMachine.class, remap = false)
public abstract class WorkableMultiblockMachineMixin {
    @Inject(method = "onStructureFormed",
            at = @At(value = "INVOKE",
                    target = "Ljava/util/Map;computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;",
                    shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILSOFT)
    void bothIOHandlerSupport(CallbackInfo ci, Long2ObjectMap<?> var1, Map<IO, List<IRecipeHandler<?>>> ioTraits, Iterator<?> var3, MachineTrait var4, IRecipeHandlerTrait<?> handlerTrait){
        if(handlerTrait.getHandlerIO() == IO.BOTH) {
            ioTraits.computeIfAbsent(IO.IN, i -> new ArrayList<>()).add(handlerTrait);
            ioTraits.computeIfAbsent(IO.OUT, i -> new ArrayList<>()).add(handlerTrait);
        }
    }
}
