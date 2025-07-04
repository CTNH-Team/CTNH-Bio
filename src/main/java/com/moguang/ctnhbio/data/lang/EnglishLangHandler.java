package com.moguang.ctnhbio.data.lang;

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
    }
}
