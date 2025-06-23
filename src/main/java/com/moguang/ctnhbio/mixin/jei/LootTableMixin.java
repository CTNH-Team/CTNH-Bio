package com.moguang.ctnhbio.mixin.jei;

import com.moguang.ctnhbio.utils.LootTableAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LootTable.class)
public class LootTableMixin implements LootTableAccess {
    @Unique
    private ResourceLocation ctnhbio$randomSequence; // 用于保存参数

    @Inject(method = "<init>", at = @At("RETURN"))
    private void captureRandomSequence(LootContextParamSet p_287716_, ResourceLocation p_287737_, LootPool[] p_287700_, LootItemFunction[] p_287663_, CallbackInfo ci) {
        this.ctnhbio$randomSequence = p_287737_;
    }

    @Override
    public ResourceLocation getRandomSequence() {
        return this.ctnhbio$randomSequence;
    }
}
