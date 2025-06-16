package com.moguang.ctnhbio.client;

import com.moguang.ctnhbio.common.CommonProxy;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientProxy extends CommonProxy {
    public ClientProxy() {
        super();
        init();
    }

    public static void init() {

    }

    @SubscribeEvent
    public void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
//        event.registerEntityRenderer(CBEntities.BASIC_MOB.get(),
//                BasicLivingMachineEntityRenderer::new
//        );

    }
}
