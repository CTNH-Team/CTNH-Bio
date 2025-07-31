package com.moguang.ctnhbio.common;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.item.tool.CBToolType;
import com.moguang.ctnhbio.data.CBDatagen;
import com.moguang.ctnhbio.data.materials.OrganicMaterials;
import com.moguang.ctnhbio.registry.CBCreativeModeTabs;
import com.moguang.ctnhbio.registry.CBEntities;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBSerums;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("removal")
public class CommonProxy {
    public CommonProxy() {
        init();
        IEventBus modEventBus = FMLJavaModLoadingContext
                .get().getModEventBus();
        CBSerums.SERUMS.register(modEventBus);
    }
    public static void init() {
        CBEntities.init();

        //Object unused = CBToolType.BONING_KNIFE;

        CBCreativeModeTabs.init();
        CBDatagen.init();

        CTNHBio.REGISTRATE.registerRegistrate();

    }

    @SubscribeEvent
    public void modConstruct(FMLConstructModEvent event) {
        // this is done to delay initialization of content to be after KJS has set up.


    }

}
