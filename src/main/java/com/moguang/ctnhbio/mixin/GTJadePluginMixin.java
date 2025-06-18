package com.moguang.ctnhbio.mixin;

import com.gregtechceu.gtceu.integration.jade.GTJadePlugin;
import com.gregtechceu.gtceu.integration.jade.provider.ElectricContainerBlockProvider;
import com.moguang.ctnhbio.jade.LivingMachineStatusProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;

@Mixin(GTJadePlugin.class)
public class GTJadePluginMixin {

    @Inject(
            method = "register",
            at = @At("HEAD"),
            remap = false
    )
    public void registerMixin(IWailaCommonRegistration registration, CallbackInfo ci){
        registration.registerBlockDataProvider(new LivingMachineStatusProvider(), BlockEntity.class);
    }

    @Inject(
            method = "registerClient",
            at = @At("HEAD"),
            remap = false
    )
    public void registerMixin(IWailaClientRegistration registration, CallbackInfo ci){
        registration.registerBlockComponent(new LivingMachineStatusProvider(), Block.class);
        //registration.registerBlockDataProvider(new , BlockEntity.class);
    }
}
