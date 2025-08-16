package com.moguang.ctnhbio.data;

import com.moguang.ctnhbio.data.lang.ChineseLangHandler;
import com.moguang.ctnhbio.data.lang.EnglishLangHandler;
import com.moguang.ctnhbio.data.lang.RegistrateCNLangProvider;
import com.moguang.ctnhbio.data.tags.BlockTags;
import com.moguang.ctnhbio.data.tags.FluidTags;
import com.moguang.ctnhbio.data.tags.ItemTags;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.world.item.Item;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBDatagen {
    public static final ProviderType<RegistrateCNLangProvider> CNLANG = ProviderType.register("ctnhbio_cn_lang", (p, e) -> new RegistrateCNLangProvider(p, e.getGenerator().getPackOutput()));

    public static void init() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, EnglishLangHandler::init);
        REGISTRATE.addDataGenerator(CNLANG, ChineseLangHandler::init);
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, BlockTags::init);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, ItemTags::init);
        REGISTRATE.addDataGenerator(ProviderType.FLUID_TAGS, FluidTags::init);
    }
}
