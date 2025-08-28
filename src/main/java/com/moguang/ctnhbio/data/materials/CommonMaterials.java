package com.moguang.ctnhbio.data.materials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.moguang.ctnhbio.CTNHBio;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class CommonMaterials {


    public static Material WEIRD_PIXEL_DUST;
    public static Material BLOODSTEEL;

    public static void register() {
        WEIRD_PIXEL_DUST = new Material.Builder(CTNHBio.id("weird_pixel_dust"))
                .dust()
                .color(0xC0D848)
                .secondaryColor(0x789C38)
                .iconSet(ROUGH)
                .components(Carbon, 1, Hydrogen, 4, Oxygen, 3)
                .flags(NO_SMASHING)
                .buildAndRegister();
        BLOODSTEEL = new Material.Builder(CTNHBio.id("bloodsteel"))
                .dust()
                .ingot()
                .liquid()
                .color(0x8B0000)
                .secondaryColor(0x5E1914)
                .iconSet(METALLIC)
                .flags(GENERATE_PLATE, GENERATE_GEAR, DISABLE_DECOMPOSITION, GENERATE_ROD, GENERATE_FRAME, GENERATE_DENSE)
                .components(
                        GTMaterials.Iron, 3,
                        GTMaterials.Chromium, 1,
                        GTMaterials.Neodymium, 1,
                        GTMaterials.Carbon, 2,
                        GTMaterials.Oxygen, 1,
                        GTMaterials.Vanadium, 1
                )
                .blast(b -> b.temp(7300, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[GTValues.EV], 700)
                        .vacuumStats(VA[HV]))
                .buildAndRegister()
                .setFormula("Fe3CrNd*C2O-V", true);  // 化学式标记
    }
}
