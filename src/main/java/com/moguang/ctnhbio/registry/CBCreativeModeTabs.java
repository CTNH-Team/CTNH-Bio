package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import com.ctnhlang.*;
import com.tterrag.registrate.util.entry.RegistryEntry;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

@Category("creativemodetab")
public class CBCreativeModeTabs {

    public static void init() {}

    @EN("CTNH-Bio Items")
    @CN("CTNH-Bio 物品")
    static Lang itemGroup;
    public static RegistryEntry<CreativeModeTab> ITEM = REGISTRATE.defaultCreativeTab("item",
            builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("item", REGISTRATE))
                    .icon(() -> new ItemStack(CBItems.WETWARE_PRINTED_CIRCUIT_BOARD.get()))
                    .title(itemGroup.translate())
                    .build())
            .register();
}
