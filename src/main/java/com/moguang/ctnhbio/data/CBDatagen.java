package com.moguang.ctnhbio.data;

import com.moguang.ctnhbio.data.lang.ChineseLangHandler;
import com.moguang.ctnhbio.data.lang.EnglishLangHandler;
import com.moguang.ctnhbio.data.tags.BlockTags;
import com.moguang.ctnhbio.data.tags.FluidTags;
import com.moguang.ctnhbio.data.tags.ItemTags;
import com.tterrag.registrate.providers.ProviderType;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;
import static tech.vixhentx.mcmod.ctnhlib.registrate.data.ProviderTypes.CNLANG;

public class CBDatagen {

    public static void init() {
        REGISTRATE.addLangProcessor()
                .addDataGenerator(ProviderType.LANG, EnglishLangHandler::init)
                .addDataGenerator(CNLANG, ChineseLangHandler::init)
                .addDataGenerator(ProviderType.BLOCK_TAGS, BlockTags::init)
                .addDataGenerator(ProviderType.ITEM_TAGS, ItemTags::init)
                .addDataGenerator(ProviderType.FLUID_TAGS, FluidTags::init);
    }
}
