package com.moguang.ctnhbio.data;

import com.moguang.ctnhbio.data.lang.LangHandler;
import com.moguang.ctnhbio.data.tags.BlockTags;
import com.moguang.ctnhbio.data.tags.FluidTags;
import com.moguang.ctnhbio.data.tags.ItemTags;
import com.tterrag.registrate.providers.ProviderType;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBDatagen {

    public static void init() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, BlockTags::init);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, ItemTags::init);
        REGISTRATE.addDataGenerator(ProviderType.FLUID_TAGS, FluidTags::init);

        LangHandler.process();
    }
}
