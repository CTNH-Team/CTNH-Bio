package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.materials.OrganicMaterials;

import static com.gregtechceu.gtceu.api.GTValues.LuV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.SHINY;
import static com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey.POLYMER;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class CBMaterials {

    public static void init() {
        OrganicMaterials.register();
    }
    //普通材料
    public static final Material PYRROLE = new Material.Builder(CTNHBio.id("pyrrole"))
            .liquid(new FluidBuilder()
                    .color(0xD4AF37)
                    .temperature(398)
            )
            .components(Carbon, 4, Hydrogen, 5, Nitrogen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material FENTONS_REAGENT = new Material.Builder(CTNHBio.id("fentons_reagent"))
            .liquid(new FluidBuilder()
                    .color(0x7FFFD4)
                    .temperature(303)
            )
            .components(Iron2Chloride, 1, HydrogenPeroxide, 1)
            .flags(EXPLOSIVE)
            .buildAndRegister();
    public static final Material POLYPYRROLE = new Material.Builder(CTNHBio.id("polypyrrole"))
            .dust()
            .ingot()
            .liquid()
            .color(0x1A1A1A)
            .iconSet(SHINY)
            .components(Carbon, 4, Hydrogen, 3, Nitrogen, 1)
            .cableProperties(128, 1, 1)
            .buildAndRegister();
    public static final Material BLUE_TITANIUM_ALLOY = new Material.Builder(CTNHBio.id("blue_titanium_alloy"))
            .dust()
            .ingot(5)
            .liquid()
            .color(0x1E90FF)
            .iconSet(METALLIC)
            .components(
                    Titanium, 32,
                    Tantalum, 4,
                    Gadolinium, 1
            )
            .flags(GENERATE_FOIL, GENERATE_SPRING)
            .blast(b -> b.temp(6000, BlastProperty.GasTier.HIGH)
                    .blastStats(VA[GTValues.LuV], 3000)
                    .vacuumStats(VA[LuV]))
            .cableProperties(2048, 8, 16)
            .buildAndRegister();
}
