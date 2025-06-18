package com.moguang.ctnhbio.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class EnglishLangHandler {
    public static void init(RegistrateLangProvider provider){
        provider.add("config.jade.plugin_gtceu.living_machine_status_provider", "[CTNH] Living Machine Status");
        provider.add("ctnhbio.living_machine", "living machine");
    }
}
