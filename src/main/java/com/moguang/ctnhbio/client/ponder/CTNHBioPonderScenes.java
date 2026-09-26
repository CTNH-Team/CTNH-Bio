package com.moguang.ctnhbio.client.ponder;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBMultiblocks;

public final class CTNHBioPonderScenes {

    private CTNHBioPonderScenes() {}

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(CBMultiblocks.COGNI_ASSEMBLER.getId())
                .addStoryBoard("cogni_assembler/common", CogniAssembler::common, CTNHBioPonderTags.BioMultiblock);

        CTNHBio.LOGGER.info("Bio Ponder scenes initialized");
    }
}
