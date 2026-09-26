package com.moguang.ctnhbio.client.ponder;

import com.gregtechceu.gtceu.GTCEu;

import net.createmod.ponder.api.scene.SceneBuilder;

import com.moguang.ctnhbio.CTNHBio;
import tech.vixhentx.mcmod.ctnhlib.client.ponder.CTNHPonderSceneBuilder;

public class CTNHBioPonderSceneBuilder extends CTNHPonderSceneBuilder {

    public CTNHBioPonderSceneBuilder(SceneBuilder builder) {
        super(builder, CTNHBio.MODID, CTNHBioPonderSceneBuilder::registerLang);
    }

    private static void registerLang(String key, String en, String cn) {
        if (GTCEu.isDataGen()) {
            CTNHBio.REGISTRATE.genLang(key, en, cn);
        }
    }
}
