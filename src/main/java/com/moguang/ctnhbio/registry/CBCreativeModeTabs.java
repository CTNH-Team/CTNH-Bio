package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.moguang.ctnhbio.CTNHBio;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBCreativeModeTabs {
    public static void init() {

    }
    public static RegistryEntry<CreativeModeTab> ITEM = REGISTRATE.defaultCreativeTab("item",
                    builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("item", REGISTRATE))
                            .icon(() -> new ItemStack(CBItems.WETWARE_PRINTED_CIRCUIT_BOARD.get()))
                            .title(REGISTRATE.addLang("itemGroup", CTNHBio.id("item"), "CTNH_Bio Items"))
                            .build())
            .register();
}
