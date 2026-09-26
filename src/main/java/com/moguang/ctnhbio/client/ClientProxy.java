package com.moguang.ctnhbio.client;

import net.createmod.ponder.foundation.PonderIndex;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import com.moguang.ctnhbio.client.ponder.CTNHBioPonderPlugin;
import com.moguang.ctnhbio.common.CommonProxy;

public class ClientProxy extends CommonProxy {

    public ClientProxy() {
        super();
        init();
    }

    public static void init() {}

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> PonderIndex.addPlugin(new CTNHBioPonderPlugin()));
    }
}
