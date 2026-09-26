package com.moguang.ctnhbio.client.ponder;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

import com.moguang.ctnhbio.CTNHBio;

public class CTNHBioPonderPlugin implements PonderPlugin {

    @Override
    public String getModId() {
        return CTNHBio.MODID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        CTNHBioPonderScenes.register(helper);
    }

    @Override
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
        CTNHBioPonderTags.register(helper);
    }
}
