package com.moguang.ctnhbio.common;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBCreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("removal")
public class CommonProxy {
    public CommonProxy() {
        init();
        IEventBus modEventBus = FMLJavaModLoadingContext
                .get().getModEventBus();
    }
    public static void init() {
        CBCreativeModeTabs.init();
        CTNHBio.REGISTRATE.registerRegistrate();
    }
}
