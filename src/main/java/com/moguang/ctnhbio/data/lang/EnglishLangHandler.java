package com.moguang.ctnhbio.data.lang;

import com.moguang.ctnhbio.registry.CBItems;
import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.data.lang.LangHandler.replace;


public class EnglishLangHandler {
    public static void init(RegistrateLangProvider provider){

        provider.add("config.jade.plugin_gtceu.living_machine_status_provider", "[CTNH] Living Machine Status");
        provider.add("ctnhbio.living_machine", "living machine");
        provider.add("item.gtceu.tool.boning_knife", "%s Boning Knife");
        //replace(provider, );
        provider.add("emi.category.ctnhbio.despoil_loot", "Despoil");
        provider.add("ctnhbio.nutrient_bar.info", "Nutrient Slot");
        provider.add("jade.nutrient.info", "Nutrient：");

        provider.add("recipe.condition.effect.tooltip", "Potion Effect : %s");
        provider.add("ctnhbio.recipe.nutrient", "Nutrient Consumption: %d");
        provider.add("ctnhbio.jade.nutrient_stored", "%s / %s");

        provider.add("ctnhbio.fluid.type_organic.tooltip", "§aBioactive Fluid");
        provider.add("ctnhbio.fluid_pipe.can_handle_organic", "§aCan handle Bioactive Fluid");
        provider.add("ctnhbio.fluid_pipe.cannot_handle_organic", "§4Bioactive Fluid may be inactivated");

        provider.add("ctnhbio.mv_machine.tooltip", "MV Biochip");
        provider.add("ctnhbio.hv_machine.tooltip", "HV Biochip");
        provider.add("ctnhbio.ev_machine.tooltip", "EV Biochip");
        provider.add("ctnhbio.iv_machine.tooltip", "IV Biochip");
        provider.add("ctnhbio.advanced_ram_wafer.tooltip", "Enhanced RAM Wafer");
        provider.add("ctnhbio.advanced_ram_chip.tooltip", "Advanced Random Access Memory");

        provider.add("ctnhbio.synet_core.tooltip", "A metal skeleton intricate as vascular circuits, with every groove pulsing rhythmically, as if transporting some invisible life energy.");
        provider.add("ctnhbio.meta_core.tooltip", "A geometric structure perpetually folding upon itself, its surface revealing angles that defy Euclidean laws, continuously performing impossible spatial transformations in stillness.");
        provider.add("ctnhbio.nova_core.tooltip", "Beneath its semi-transparent shell float countless crystal fragments, constantly shedding and regenerating, each fracture refracting different light spectra.");
        provider.add("ctnhbio.omni_core.tooltip", "Within the mirror-smooth sphere, nebular matter slowly rotates - the longer you gaze, the clearer your own silhouette emerges within it.");

    }
}
