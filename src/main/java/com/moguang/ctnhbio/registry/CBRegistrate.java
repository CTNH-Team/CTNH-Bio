package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.moguang.ctnhbio.CTNHBio;

public class CBRegistrate extends GTRegistrate {
    protected CBRegistrate() {
        super(CTNHBio.MODID);
    }
    public static CBRegistrate create() {
        return new CBRegistrate();
    }
}
