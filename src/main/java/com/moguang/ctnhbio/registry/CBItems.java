package com.moguang.ctnhbio.registry;

import com.github.elenterius.biomancy.init.ModRarities;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.IComponentItem;
import com.gregtechceu.gtceu.api.item.component.ICustomDescriptionId;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.api.item.component.IRecipeRemainder;
import com.gregtechceu.gtceu.api.item.component.ThermalFluidStats;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.item.ItemFluidContainer;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;
import com.gregtechceu.gtceu.common.registry.GTRegistration;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.moguang.ctnhbio.api.item.component.OrganicFluidStats;
import com.moguang.ctnhbio.api.item.component.StyleItem;
import com.moguang.ctnhbio.common.item.OrganicVialItem;
import com.moguang.ctnhbio.utils.VialCraftingRemainingItem;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.FluidUtil;

import static com.gregtechceu.gtceu.common.data.GTItems.attach;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBItems {
    public static void init() {
        CBMaterialItems.generateTools();
    }
    public static final ItemEntry<StyleItem> BIO_CAPACITOR = REGISTRATE.item("bio_capacitor", StyleItem::new)
            .lang("Bio-Capacitor")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();
    public static final ItemEntry<StyleItem> BIO_TRANSISTOR = REGISTRATE.item("bio_transistor", StyleItem::new)
            .lang("Bio-Transistor")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();
    public static final ItemEntry<StyleItem> BIO_RESISTOR = REGISTRATE.item("bio_resistor", StyleItem::new)
            .lang("Bio-Resistor")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();
    public static final ItemEntry<StyleItem> BIO_INDUCTOR = REGISTRATE.item("bio_inductor", StyleItem::new)
            .lang("Bio-Inductor")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();
    public static final ItemEntry<StyleItem> BIO_DIODE = REGISTRATE.item("bio_diode", StyleItem::new)
            .lang("Bio-Diode")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();
    public static final ItemEntry<StyleItem> WETWARE_CIRCUIT_BOARD = REGISTRATE.item("wetware_circuit_board", StyleItem::new)
            .lang("Wet Circuit Board")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();
    public static final ItemEntry<StyleItem> WETWARE_PRINTED_CIRCUIT_BOARD = REGISTRATE.item("wetware_printed_circuit_board", StyleItem::new)
            .lang("Wet Printed Circuit Board")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();

    public static ItemEntry<ComponentItem> SYNET_CORE = REGISTRATE
            .item("synet_core", ComponentItem::create)
            .lang("Synet Core")
            .properties(p -> new Item.Properties().rarity(ModRarities.VERY_RARE))
            .onRegister(attach(new TooltipBehavior(list -> {
                list.add(Component.translatable("ctnhbio.mv_machine.tooltip").withStyle(ChatFormatting.AQUA));
                list.add(Component.translatable("ctnhbio.synet_core.tooltip").withStyle(ChatFormatting.GRAY));
            })))
            .register();
    public static ItemEntry<ComponentItem> META_CORE = REGISTRATE
            .item("meta_core", ComponentItem::create)
            .lang("Meta Core")
            .properties(p -> new Item.Properties().rarity(ModRarities.VERY_RARE))
            .onRegister(attach(new TooltipBehavior(list -> {
                list.add(Component.translatable("ctnhbio.hv_machine.tooltip").withStyle(ChatFormatting.GOLD));
                list.add(Component.translatable("ctnhbio.meta_core.tooltip").withStyle(ChatFormatting.GRAY));
            })))
            .register();
    public static ItemEntry<ComponentItem> NOVA_CORE = REGISTRATE
            .item("nova_core", ComponentItem::create)
            .lang("Nova Core")
            .properties(p -> new Item.Properties().rarity(ModRarities.VERY_RARE))
            .onRegister(attach(new TooltipBehavior(list -> {
                list.add(Component.translatable("ctnhbio.ev_machine.tooltip").withStyle(ChatFormatting.LIGHT_PURPLE));
                list.add(Component.translatable("ctnhbio.nova_core.tooltip").withStyle(ChatFormatting.GRAY));
            })))
            .register();
    public static ItemEntry<ComponentItem> OMNI_CORE = REGISTRATE
            .item("omni_core", ComponentItem::create)
            .lang("Omni Core")
            .properties(p -> new Item.Properties().rarity(ModRarities.VERY_RARE))
            .onRegister(attach(new TooltipBehavior(list -> {
                list.add(Component.translatable("ctnhbio.iv_machine.tooltip").withStyle(ChatFormatting.DARK_BLUE));
                list.add(Component.translatable("ctnhbio.omni_core.tooltip").withStyle(ChatFormatting.GRAY));
            })))
            .register();

    public static ItemEntry<OrganicVialItem> ORGANIC_VIAL = REGISTRATE.item("organic_vial", OrganicVialItem::new)
            .lang("Organic %s Vial")
            .setData(ProviderType.ITEM_MODEL, NonNullBiConsumer.noop())
            .color(() -> GTItems::cellColor)
            .onRegister(attach(
                    OrganicFluidStats.create(FluidType.BUCKET_VOLUME / 10, 310, false, false, false, false, false),
                    new VialCraftingRemainingItem(),
                    cellName()
                    ))
            .register();

    public static ItemEntry<ComponentItem> ORGANIC_BEAKER = REGISTRATE.item("organic_beaker", ComponentItem::create)
            .lang("Organic %s Beaker")
            .setData(ProviderType.ITEM_MODEL, NonNullBiConsumer.noop())
            .color(() -> GTItems::cellColor)
            .onRegister(attach(
                    OrganicFluidStats.create(FluidType.BUCKET_VOLUME / 2, 373, false, true, false, false, true),

                    cellName()))
            .register();


    public static <T extends IComponentItem> NonNullConsumer<T> attach(IItemComponent components) {
        return item -> item.attachComponents(components);
    }

    public static <T extends IComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {
        return item -> item.attachComponents(components);
    }

    public static ICustomDescriptionId cellName() {
        return new ICustomDescriptionId() {

            @Override
            public Component getItemName(ItemStack stack) {
                Component prefix = FluidUtil.getFluidContained(stack).map(FluidStack::getDisplayName)
                        .orElse(Component.translatable("gtceu.fluid.empty"));
                return Component.translatable(stack.getDescriptionId(), prefix);
            }
        };
    }
}
