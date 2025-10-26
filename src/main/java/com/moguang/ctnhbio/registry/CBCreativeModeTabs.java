package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.moguang.ctnhbio.CTNHBio;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Prefix;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

@Prefix("creativemodetab")
public class CBCreativeModeTabs {
    public static void init() {

    }

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
