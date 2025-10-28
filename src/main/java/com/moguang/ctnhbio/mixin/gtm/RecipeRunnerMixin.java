package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.gregtechceu.gtceu.api.recipe.RecipeRunner;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Mixin(value = RecipeRunner.class, remap = false)
public abstract class RecipeRunnerMixin {

    @Shadow
    @Final
    private IO io;

    @Shadow
    @Final
    private Map<IO, List<RecipeHandlerList>> capabilityProxies;

//    @ModifyVariable(
//            method = "handleContents",
//            at = @At(
//                    value = "STORE",
//                    ordinal = 0  // 表示第一个存储到handlers变量的操作
//            ),
//            ordinal = 0
//    )
//    private List<RecipeHandlerList> modifyHandlers(List<RecipeHandlerList> original) {
//        // 获取当前IO参数（需要额外处理）
//
//        // 创建新列表避免修改原始集合
//        List<RecipeHandlerList> newHandlers = new ArrayList<>(original);
//
//        // 如果不是BOTH类型，则添加BOTH类型的handlers
//        if (io != IO.BOTH) {
//            List<RecipeHandlerList> bothHandlers = this.capabilityProxies.getOrDefault(IO.BOTH, Collections.emptyList());
//            newHandlers.addAll(bothHandlers);
//        }
//
//        return newHandlers;
//    }
}
