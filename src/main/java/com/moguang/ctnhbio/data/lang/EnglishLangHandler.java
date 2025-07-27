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

    }
}
