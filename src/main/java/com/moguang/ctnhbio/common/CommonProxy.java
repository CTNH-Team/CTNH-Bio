package com.moguang.ctnhbio.common;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.item.tool.CBToolType;
import com.moguang.ctnhbio.registry.CBCreativeModeTabs;
import com.moguang.ctnhbio.registry.CBEntities;
import com.moguang.ctnhbio.registry.CBItems;
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
        CBEntities.init();
        //Object unused = CBToolType.BONING_KNIFE;
        CBCreativeModeTabs.init();
        CTNHBio.REGISTRATE.registerRegistrate();

    }
}
