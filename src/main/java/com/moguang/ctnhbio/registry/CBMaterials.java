package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.moguang.ctnhbio.data.materials.OrganicMaterials;

public class CBMaterials {
    public static void init() {
        OrganicMaterials.register();
    }
}
