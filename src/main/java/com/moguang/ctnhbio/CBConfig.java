package com.moguang.ctnhbio;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.format.ConfigFormats;

@Config(id = CTNHBio.MODID)
public class CBConfig {
    public static CBConfig INSTANCE;
    private static final Object LOCK = new Object();

    public static void init() {
        synchronized (LOCK) {
            if (INSTANCE == null) {
                INSTANCE = Configuration.registerConfig(CBConfig.class, ConfigFormats.yaml()).getConfigInstance();
            }
        }
    }
}
