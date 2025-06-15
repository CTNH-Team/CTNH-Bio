package com.moguang.ctnhbio.client;

import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.client.Renderer.BasicLivingMachineRenderer;
import com.moguang.ctnhbio.common.machine.BasicLivingMachineBlock;
import com.moguang.ctnhbio.event.ForgeEventHandler;
import com.moguang.ctnhbio.registry.CBEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CTNHBio.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegister {
    @Deprecated
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

    }
    @Deprecated
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(CBEntities.BASIC_LIVING_MACHINE_ENTITY.get(),
                BasicLivingMachineRenderer::new
        );
    }

}
