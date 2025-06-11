package com.moguang.ctnhbio;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.moguang.ctnhbio.registry.CBRegistrate;

public class CTNHBioGTAddon implements IGTAddon {
    @Override
    public CBRegistrate getRegistrate() {
        return CTNHBio.REGISTRATE;
    }

    @Override
    public void initializeAddon() {

    }

    @Override
    public String addonModId() {
        return CTNHBio.MODID;
    }
}
