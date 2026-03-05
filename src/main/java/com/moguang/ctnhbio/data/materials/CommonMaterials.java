package com.moguang.ctnhbio.data.materials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.CBElements;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CommonMaterials {

    public static Material WEIRD_PIXEL_DUST;
    public static Material BLOODSTEEL;
    public static Material Ennea_Protoplasm;

    public static void register() {
        WEIRD_PIXEL_DUST = REGISTRATE.material(CTNHBio.id("weird_pixel_dust"))
                .lang("Weird Pixel Dust")
                .cnlang("非常怪异的尘埃")
                .dust()
                .color(0xC0D848)
                .secondaryColor(0x789C38)
                .iconSet(ROUGH)
                .components(Carbon, 1, Hydrogen, 4, Oxygen, 3)
                .flags(NO_SMASHING)
                .buildAndRegister();
        BLOODSTEEL = REGISTRATE.material(CTNHBio.id("bloodsteel"))
                .lang("Blood Steel")
                .cnlang("血髓钢")
                .dust()
                .ingot()
                .liquid()
                .color(0x8B0000)
                .secondaryColor(0x5E1914)
                .iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_GEAR, DISABLE_DECOMPOSITION, GENERATE_ROD, GENERATE_FRAME,
                        GENERATE_DENSE)
                .blast(b -> b.temp(7300, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[GTValues.EV], 700)
                        .vacuumStats(VA[HV]))
                .buildAndRegister()
                .setFormula("Fe3CrNd*C2O-V", true);  // 化学式标记

        Ennea_Protoplasm = REGISTRATE.material(CTNHBio.id("ennea_protoplasm"))
                .lang("Enneasilicon-based Protoplasm")
                .cnlang("九硅基原生质")
                .ingot().fluid()
                .color(0x681624)
                .secondaryColor(0x5a0816)
                .iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_DENSE, GENERATE_SMALL_GEAR)
                .element(CBElements.Esi)
                .buildAndRegister();
    }
}
