package com.moguang.ctnhbio.registry;

import com.moguang.ctnhbio.data.materials.CommonMaterials;
import com.moguang.ctnhbio.data.materials.OrganicMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class CBMaterials {

    public static void init() {
        OrganicMaterials.register();
        CommonMaterials.register();
    }
}
