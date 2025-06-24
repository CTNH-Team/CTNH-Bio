package com.moguang.ctnhbio.client;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.client.Renderer.BasicLivingMachineEntityRenderer;
import com.moguang.ctnhbio.registry.CBEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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

//        event.registerEntityRenderer(CBEntities.LIVING_META_MACHINE_ENTITY.get(),
//                BasicLivingMachineEntityRenderer::new
//        );
    }

}
