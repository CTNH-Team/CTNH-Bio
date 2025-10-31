package com.moguang.ctnhbio.data;

import com.gregtechceu.gtceu.api.data.chemical.Element;

import static com.gregtechceu.gtceu.common.data.GTElements.createAndRegister;

public class CBElements {
    public static Element Esi;

    public static void init() {
        Esi = createAndRegister(126, 126, -1, null, "Enneasicium", "Esi", false);
    }
}
