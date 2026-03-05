package com.moguang.ctnhbio;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import com.moguang.ctnhbio.client.ClientProxy;
import com.moguang.ctnhbio.common.CommonProxy;
import com.moguang.ctnhbio.registry.CBRegistrate;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;
import tech.vixhentx.mcmod.ctnhlib.langprovider.LangProcessor;

@SuppressWarnings("removal")
@Mod(CTNHBio.MODID)
public class CTNHBio {

    public static final String MODID = "ctnhbio";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final CBRegistrate REGISTRATE = CBRegistrate.create();

    public CTNHBio() {
        LangProcessor langProcessor = new LangProcessor(REGISTRATE);
        langProcessor.processAll();
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.tryParse(MODID + ":" + name);
    }
}
