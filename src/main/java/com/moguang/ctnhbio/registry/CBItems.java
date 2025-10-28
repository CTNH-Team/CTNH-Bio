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
import com.moguang.ctnhbio.data.CBDatagen;
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
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.*;

import static com.gregtechceu.gtceu.common.data.GTItems.attach;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

@Suffix("tooltip")
public class CBItems {
    static {
        REGISTRATE.creativeModeTab(() -> CBCreativeModeTabs.ITEM);
    }
    public static void init() {
        CBMaterialItems.generateTools();
    }
    public static final ItemEntry<StyleItem> WETWARE_CAPACITOR = REGISTRATE.item("wetware_capacitor", StyleItem::new)
            .cnlang("湿件电容")
            .lang("Wetware-Capacitor")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();
    public static final ItemEntry<StyleItem> WETWARE_TRANSISTOR = REGISTRATE.item("wetware_transistor", StyleItem::new)
            .cnlang("湿件晶体管")
            .lang("Wetware-Transistor")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();
    public static final ItemEntry<StyleItem> WETWARE_RESISTOR = REGISTRATE.item("wetware_resistor", StyleItem::new)
            .cnlang("湿件电阻")
            .lang("Wetware-Resistor")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();
    public static final ItemEntry<StyleItem> WETWARE_INDUCTOR = REGISTRATE.item("wetware_inductor", StyleItem::new)
            .cnlang("湿件电感")
            .lang("Wetware-Inductor")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();
    public static final ItemEntry<StyleItem> WETWARE_DIODE = REGISTRATE.item("wetware_diode", StyleItem::new)
            .cnlang("湿件二极管")
            .lang("Wetware-Diode")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();
    public static final ItemEntry<StyleItem> WETWARE_CIRCUIT_BOARD = REGISTRATE.item("wetware_circuit_board", StyleItem::new)
            .cnlang("湿件电路基板")
            .lang("Wet Circuit Board")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();
    public static final ItemEntry<StyleItem> WETWARE_PRINTED_CIRCUIT_BOARD = REGISTRATE.item("wetware_printed_circuit_board", StyleItem::new)
            .cnlang("湿件印刷电路基板")
            .lang("Wet Printed Circuit Board")
            .properties(p -> new Item.Properties().rarity(ModRarities.RARE))
            .register();

    @CN({
            "§bMV生物芯片",
            "精密如血管回路的金属骨架，每一道凹槽都在规律脉动，仿佛在输送某种不可见的生命能量。"
    })
    @EN({
            "§bMV Biochip",
            "A metal skeleton intricate as vascular circuits, with every groove pulsing rhythmically, as if transporting some invisible life energy."
    })
    static Lang[] synetcore;
    public static ItemEntry<ComponentItem> SYNET_CORE = REGISTRATE
            .item("synet_core", ComponentItem::create)
            .cnlang("脉络核心")
            .lang("Synet Core")
            .properties(p -> new Item.Properties().rarity(ModRarities.VERY_RARE))
            .onRegister(attach(new TooltipBehavior(list -> {
                list.add(synetcore[0].translate());
                list.add(synetcore[1].translate());
            })))
            .register();
    @CN({
            "§6HV生物芯片",
            "不断自我折叠的几何结构，表面浮现出违反欧几里得定律的棱角，在静止中持续完成着不可能的空间变换。"
    })
    @EN({
            "§6HV Biochip",
            "A geometric structure perpetually folding upon itself, its surface revealing angles that defy Euclidean laws, continuously performing impossible spatial transformations in stillness."
    })
    static Lang[] metacore;
    public static ItemEntry<ComponentItem> META_CORE = REGISTRATE
            .item("meta_core", ComponentItem::create)
            .cnlang("拓扑核心")
            .lang("Meta Core")
            .properties(p -> new Item.Properties().rarity(ModRarities.VERY_RARE))
            .onRegister(attach(new TooltipBehavior(list -> {
                list.add(metacore[0].translate());
                list.add(metacore[1].translate());
            })))
            .register();
    @CN({
            "§dEV生物芯片",
            "半透明外壳下悬浮着无数晶体碎片，它们持续剥落又再生，每次破碎都折射出不同的光谱。"
    })
    @EN({
            "§dEV Biochip",
            "Beneath its semi-transparent shell float countless crystal fragments, constantly shedding and regenerating, each fracture refracting different light spectra."
    })
    static Lang[] novacore;
    public static ItemEntry<ComponentItem> NOVA_CORE = REGISTRATE
            .item("nova_core", ComponentItem::create)
            .cnlang("灵蜕核心")
            .lang("Nova Core")
            .properties(p -> new Item.Properties().rarity(ModRarities.VERY_RARE))
            .onRegister(attach(new TooltipBehavior(list -> {
                list.add(novacore[0].translate());
                list.add(novacore[1].translate());
            })))
            .register();
    @CN({
            "§1IV生物芯片",
            "光滑如镜面的球体内部，有星云状物质在缓慢旋转，凝视越久越能看见其中浮现出观测者自身的轮廓。"
    })
    @EN({
            "§1IV Biochip",
            "Within the mirror-smooth sphere, nebular matter slowly rotates - the longer you gaze, the clearer your own silhouette emerges within it."
    })
    static Lang[] omnicore;
    public static ItemEntry<ComponentItem> OMNI_CORE = REGISTRATE
            .item("omni_core", ComponentItem::create)
            .cnlang("终观核心")
            .lang("Omni Core")
            .properties(p -> new Item.Properties().rarity(ModRarities.VERY_RARE))
            .onRegister(attach(new TooltipBehavior(list -> {
                list.add(omnicore[0].translate());
                list.add(omnicore[1].translate());
            })))
            .register();

    public static ItemEntry<OrganicVialItem> ORGANIC_VIAL = REGISTRATE.item("organic_vial", OrganicVialItem::new)
            .cnlang("有机%s试管")
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
            .cnlang("有机%s烧杯")
            .lang("Organic %s Beaker")
            .setData(ProviderType.ITEM_MODEL, NonNullBiConsumer.noop())
            .color(() -> GTItems::cellColor)
            .onRegister(attach(
                    OrganicFluidStats.create(FluidType.BUCKET_VOLUME / 2, 373, false, true, false, false, true),

                    cellName()))
            .register();

    public static ItemEntry<ComponentItem> WETWARE_PROCESSOR_COMPUTER_UNFINISHED = REGISTRATE
            .item("wetware_processor_computer_unfinished", ComponentItem::create)
            .lang("wetware_processor_computer_unfinished")
            //.properties(p -> new Item.Properties().rarity(ModRarities.VERY_RARE))
            .onRegister(attach(new TooltipBehavior(list -> {
;
            })))

            .register();

    public static ItemEntry<ComponentItem> WETWARE_PROCESSOR_MAINFRAME_UNFINISHED = REGISTRATE
            .item("wetware_processor_mainframe_unfinished", ComponentItem::create)
            .lang("wetware_processor_mainframe_unfinished")
            //.properties(p -> new Item.Properties().rarity(ModRarities.VERY_RARE))
//            .onRegister(attach(new TooltipBehavior(list -> {
//                list.add(Component.translatable("ctnhbio.ev_machine.tooltip").withStyle(ChatFormatting.LIGHT_PURPLE));
//                list.add(Component.translatable("ctnhbio.nova_core.tooltip").withStyle(ChatFormatting.GRAY));
//            })))
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
