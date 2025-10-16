package com.moguang.ctnhbio.data;

import com.moguang.ctnhbio.data.lang.ChineseLangHandler;
import com.moguang.ctnhbio.data.lang.EnglishLangHandler;

import com.moguang.ctnhbio.data.tags.BlockTags;
import com.moguang.ctnhbio.data.tags.FluidTags;
import com.moguang.ctnhbio.data.tags.ItemTags;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.world.item.Item;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;
import static tech.vixhentx.mcmod.ctnhlib.registrate.data.ProviderTypes.CNLANG;

public class CBDatagen {
    public static void init() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, EnglishLangHandler::init);
        REGISTRATE.addDataGenerator(CNLANG, ChineseLangHandler::init);
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, BlockTags::init);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, ItemTags::init);
        REGISTRATE.addDataGenerator(ProviderType.FLUID_TAGS, FluidTags::init);

        //REGISTRATE.addLangProcessor();
    }
}
