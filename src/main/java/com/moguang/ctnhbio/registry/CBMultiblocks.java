package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

import com.github.elenterius.biomancy.BiomancyMod;
import com.github.elenterius.biomancy.init.ModBlocks;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.block.LivingMultiMetaMachineBlock;
import com.moguang.ctnhbio.api.item.LivingMetaMachineItem;
import com.moguang.ctnhbio.api.machine.multiblock.CBPartAbility;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
import com.moguang.ctnhbio.api.recipe.CBRecipeModifier;
import com.moguang.ctnhbio.client.Renderer.LivingMetaMachineBERProvider;
import com.moguang.ctnhbio.client.Text.ModelOutputLine;
import com.moguang.ctnhbio.machine.bioobservation.HostileObserverMachine;
import com.moguang.ctnhbio.machine.greatflesh.GreatFleshMachine;
import com.moguang.ctnhbio.machine.multiblock.CogniAssemblerMachine;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Suffix;

import static com.gregtechceu.gtceu.api.pattern.Predicates.autoAbilities;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

@Suffix("tooltip")
public class CBMultiblocks {

    // spotless off
    public static MultiblockMachineDefinition GREAT_FLESH;
    public static MultiblockMachineDefinition CIRCULATORY_SYSTEM;
    public static MultiblockMachineDefinition COGNI_ASSEMBLER;
    public static MultiblockMachineDefinition WEATHERER;
    public static MultiblockMachineDefinition HOSTILE_OBSERVER;

    public static void init() {
        GREAT_FLESH = REGISTRATE
                .biomultiblock("great_flesh",
                        GreatFleshMachine::new,
                        (p, d) -> new LivingMultiMetaMachineBlock(p, d, true),
                        (b, p) -> new LivingMetaMachineItem(b, p, "great_flesh"))
                .cnLangValue("巨型肉块")
                .langValue("Giant Flesh")
                .recipeType(CBRecipeTypes.GREAT_FLESH)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("AAA", "AAA", "AAA")
                        .aisle("AAA", "AAA", "AAA")
                        .aisle("AAA", "A@A", "AAA")
                        .where("A", Predicates.blocks(ModBlocks.FLESH.get()).setMinGlobalLimited(10)
                                .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                        // .or(Predicates.blocks(Blocks.AIR))

                        )
                        .where("@", Predicates.controller(Predicates.blocks(definition.get())))

                        .build())

                .workableCasingModel(BiomancyMod.createRL("block/flesh"), GTCEu.id("block/multiblock/assembly_line"))
                // .simpleModel(ResourceLocation.tryBuild("minecraft", "block/air"))
                .additionalDisplay((controller, components) -> {
                    if (controller instanceof WorkableLivingMultiblockMachine machine) {
                        components.add(Component.translatable("jade.nutrient.info",
                                Component.translatable(FormattingUtil.formatNumbers(machine.getNutrientAmount()))
                                        .setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
                    }
                })
                .hasBER(false)
                .onBlockEntityRegister(beType -> {
                    if (FMLEnvironment.dist == Dist.CLIENT) {
                        LivingMetaMachineBERProvider.registerRenderer(beType, "great_flesh", false);
                    }
                })
                .register();

        CIRCULATORY_SYSTEM = REGISTRATE
                .biomultiblock("circulatory_system",
                        WorkableLivingMultiblockMachine::new,
                        LivingMultiMetaMachineBlock::new,
                        MetaMachineItem::new)
                .cnLangValue("循环系统")
                .langValue("Circulatory System")
                .recipeType(CBRecipeTypes.BIO_REACTOR_RECIPES)
                .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, CBRecipeModifier::batchMode)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("AAAAA", "BCCCB", "BCCCB", "BCCCB", "AAAAA")
                        .aisle("ADDDA", "CEEEC", "C###C", "C###C", "ADDDA")
                        .aisle("ADDDA", "CEFEC", "C#G#C", "C#F#C", "ADDDA")
                        .aisle("ADDDA", "CEEEC", "C###C", "C###C", "ADDDA")
                        .aisle("AAAAA", "BC@CB", "BCCCB", "BCCCB", "AAAAA")
                        .where("E",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("biomancy:acid_fluid_block"))))
                        .where("C", Predicates.blocks(CBBlocks.IMPERMEABLE_MEMBRANE.get())

                        )
                        .where("F",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("ctnhbio:primal_flesh_casing"))))
                        .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                        .where("G", Predicates.heatingCoils())
                        .where("D",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("ctnhbio:bio_acid_casing"))))
                        .where("A",
                                Predicates
                                        .blocks(ForgeRegistries.BLOCKS
                                                .getValue(ResourceLocation.parse("ctnhbio:ornate_flesh_casing")))
                                        .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                        .where("#", Predicates.any())
                        .where("B",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("ctnhbio:flesh_casing"))))
                        .build())

                .workableCasingModel(CTNHBio.id("block/casings/ornate_flesh_casing"),
                        CTNHBio.id("block/multiblock/red"))
                .appearanceBlock(CBBlocks.ORNATE_FLESH_CASING)
                // .simpleModel(ResourceLocation.tryBuild("minecraft", "block/acacia_log"))
                .additionalDisplay((controller, components) -> {
                    if (controller instanceof WorkableLivingMultiblockMachine machine) {
                        components.add(Component.translatable("jade.nutrient.info",
                                Component.translatable(FormattingUtil.formatNumbers(machine.getNutrientAmount()))
                                        .setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
                    }
                })
                .register();

        COGNI_ASSEMBLER = REGISTRATE
                .biomultiblock("cogni_assembler",
                        CogniAssemblerMachine::new,
                        LivingMultiMetaMachineBlock::new,
                        MetaMachineItem::new)
                .cnLangValue("意识装配机")
                .langValue("Cogni Assembler")
                .recipeTypes(CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES, CBRecipeTypes.COGNI_ASSEMBLY_STEP)
                .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, CBRecipeModifier::batchMode)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("AAAAA", "BCACB", "BDADB", "BCACB", "AAAAA")
                        .aisle("AEEEA", "CFGFC", "DFGFD", "CFGFC", "AEEEA")
                        .aisle("AEEEA", "AGGGA", "HGGGH", "AGGGA", "AEEEA")
                        .aisle("AEEEA", "CE@EC", "DEIED", "CEEEC", "AEEEA")
                        .aisle("AAAAA", "B###B", "B###B", "B###B", "AAAAA")
                        .where("C",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("kubejs:flesh_casing_fence"))))
                        .where("B", Predicates.blocks(CBBlocks.SYNAPTIC_CASING.get()))
                        .where("E", Predicates.blocks(CBBlocks.PRIMAL_FLESH_CASING.get())
                                .or(Predicates.autoAbilities(definition.getRecipeTypes(), false, false, true, true,
                                        true, true))
                                .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setExactLimit(1).setPreviewCount(1)))
                        .where("H",
                                Predicates
                                        .blocks(ForgeRegistries.BLOCKS
                                                .getValue(ResourceLocation.parse("ctnhbio:ornate_flesh_casing")))
                                        .or(Predicates.blocks(CBMachines.PARABIOTIC_BRIDGE.get())))
                        .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                        .where("A", Predicates.blocks(
                                ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:ornate_flesh_casing")))

                        )
                        .where("#", Predicates.any())
                        .where("F",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("ctnhbio:acid_flesh_casing"))))
                        .where("D",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("biomancy:flesh_fence"))))
                        .where("I", Predicates.abilities(CBPartAbility.NEURAL_MODEL_ACCESSOR)
                                .or(Predicates.blocks(CBBlocks.PRIMAL_FLESH_CASING.get())))
                        .where("G",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("biomancy:smooth_primal_flesh"))))
                        .build())

                .workableCasingModel(CTNHBio.id("block/casings/primal_flesh_casing"),
                        CTNHBio.id("block/multiblock/red"))
                .appearanceBlock(CBBlocks.PRIMAL_FLESH_CASING)
                .additionalDisplay((controller, components) -> {
                    if (controller instanceof WorkableLivingMultiblockMachine machine) {
                        components.add(Component.translatable("jade.nutrient.info",
                                Component.translatable(FormattingUtil.formatNumbers(machine.getNutrientAmount()))
                                        .setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
                    }
                })
                .partSorter(CogniAssemblerMachine::partSorter)
                .register();

        WEATHERER = REGISTRATE
                .biomultiblock("weatherer",
                        WorkableLivingMultiblockMachine::new,
                        LivingMultiMetaMachineBlock::new,
                        MetaMachineItem::new)
                .cnLangValue("风化器")
                .langValue("Weatherer")
                .recipeType(CBRecipeTypes.DECOMPOSER_RECIPES)
                .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, CBRecipeModifier::batchMode)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("#BBBBB#", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                        .aisle("BCCCCCB", "#CDDDC#", "#C###C#", "#E###E#", "#E###E#", "#E###E#", "#EE#EE#", "#######")
                        .aisle("BCFGFCB", "#DFFFD#", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "#EF#FE#", "##H#H##")
                        .aisle("BCGGGCB", "#DFGFD#", "###G###", "###E###", "###E###", "###E###", "###B###", "#######")
                        .aisle("BCFGFCB", "#DFFFD#", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "#EF#FE#", "##H#H##")
                        .aisle("BCCCCCB", "#CD@DC#", "#C###C#", "#E###E#", "#E###E#", "#E###E#", "#EE#EE#", "#######")
                        .aisle("#BBBBB#", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                        .where("B",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("biomancy:ornate_flesh_slab"))))
                        .where("H",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("biomancy:flesh_spike"))))
                        .where("G",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("ctnhbio:primal_flesh_casing"))))
                        .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                        .where("#", Predicates.any())
                        .where("C",
                                Predicates
                                        .blocks(ForgeRegistries.BLOCKS
                                                .getValue(ResourceLocation.parse("ctnhbio:ornate_flesh_casing")))
                                        .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                        .where("F",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("ctnhbio:acid_flesh_casing"))))
                        .where("E",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("biomancy:flesh_fence"))))
                        .where("D",
                                Predicates.blocks(ForgeRegistries.BLOCKS
                                        .getValue(ResourceLocation.parse("ctnhbio:flesh_casing"))))
                        .build())

                .workableCasingModel(CTNHBio.id("block/casings/flesh_casing"),
                        CTNHBio.id("block/multiblock/red"))
                .appearanceBlock(CBBlocks.FLESH_CASING)
                .additionalDisplay((controller, components) -> {
                    if (controller instanceof WorkableLivingMultiblockMachine machine) {
                        components.add(Component.translatable("jade.nutrient.info",
                                Component.translatable(FormattingUtil.formatNumbers(machine.getNutrientAmount()))
                                        .setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
                    }
                })
                .register();

        HOSTILE_OBSERVER = REGISTRATE
                .multiblock("hostile_observer", HostileObserverMachine::new)
                .cnLangValue("敌意观测站")
                .langValue("Hostile Observer")
                .recipeType(CBRecipeTypes.HOSTILE_OBSERVATION)
                .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH,
                        HostileObserverMachine::hostileObserverMachineModifier)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("################AAAAAAAAAAA################",
                                "################ABBBBBBBBBA################",
                                "################ABBBBBBBBBA################",
                                "################ABBCCCCCBBA################",
                                "################ACCCDDDCCCA################",
                                "################ACEEEEEEECA################",
                                "################ACCCDDDCCCA################",
                                "################ABBCCCCCBBA################",
                                "################ABBBBBBBBBA################",
                                "################ABBBBBBBBBA################",
                                "################AAAAAAAAAAA################")
                        .aisle("################AAACADACAAA################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################ACCCACACCCA################")
                        .aisle("################AAACADACAAA################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################ACBBBCBBBCA################")
                        .aisle("AAAAAAA#########ACCCADACCCA#########AAAAAAA",
                                "ADDDDDA#########B#########B#########ADDDDDA",
                                "ADCCCDA#########B#########B#########ADBBBDA",
                                "ADCEEDA#########B#########B#########ADBBBDA",
                                "ADCEEDA#########B#########B#########ADBBBDA",
                                "ADCCCDA#########B#########B#########ADBBBDA",
                                "ADEECDA#########B#########B#########ADBBBDA",
                                "ADEECDA#########B#########B#########ADBBBDA",
                                "ADCCCDA#########B#########B#########ADBBBDA",
                                "ADDDDDA#########B#########B#########ADDDDDA",
                                "AAAAAAA#########ACBBBCBBBCA#########AAAAAAA")
                        .aisle("AAAAAAA#########AAAAADAAAAA#########AAAAAAA",
                                "F#####D#########A#########A#########DDDDDDD",
                                "F###C#D#########A#########A#########DCCCCCD",
                                "D#####DAAAAAAAAAA#########AAAAAAAAAADC###CD",
                                "D#####DABBBBBBBAD#########DABBBBBBBADC###CD",
                                "D#C#C#DABBBBBBBAD#########DABBBBBBBADC###CD",
                                "D#####DABBBBBBBAD#########DABBBBBBBADC###CD",
                                "D#####DAAAAAAAAAA#########AAAAAAAAAADC###CD",
                                "D#C###D#########A#########A#########DCCCCCD",
                                "D#####D#########A#########A#########DDDDDDD",
                                "AGGGGGA#########AABBBCBBBAA#########AGGGGGA")
                        .aisle("AAAAAAA#########AEEEEEEEEEA#########AAAAAAA",
                                "F#####D#########A#########A#########DDDDDDD",
                                "FCCCCCD#########A#########A#########DCEEECD",
                                "DDEDCCCBBBBBBBBAA#########AABBBBBBBACCEEE#D",
                                "DDCDEDCDDDDDDDDAD#########DADDDDDDDDC#EEE#D",
                                "DCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC#EEE#D",
                                "DDEDCDCDDDDDDDDDD#########DDDDDDDDDAC#EEE#D",
                                "DDCDECCBBBBBBBBBA#########AABBBBBBBACCEEE#D",
                                "DCCCCCD#########A#########A#########DCEEECD",
                                "D#####D#########A#########A#########DDDDDDD",
                                "AGAGAGA#########AEEEEEEEEEA#########AGAGAGA")
                        .aisle("AAAAAAA#########AAAAADAAAAA#########AAAAAAA",
                                "F#####D#########A#########A#########DDDDDDD",
                                "F###C#D#########A#########A#########DCCCCCD",
                                "D#####DAAAAAAAAAA#########AAAAAAAAAADC###CD",
                                "D#####DABBBBBBBAD#########DABBBBBBBADC###CD",
                                "D#C#C#DABBBBBBBAD#########DABBBBBBBADC###CD",
                                "D#####DABBBBBBBAD#########DABBBBBBBADC###CD",
                                "D#####DAAAAAAAAAA#########AAAAAAAAAADC###CD",
                                "D#C###D#########A#########A#########DCCCCCD",
                                "D#####D#########A#########A#########DDDDDDD",
                                "AGGGGGA#########AABBBCBBBAA#########AGGGGGA")
                        .aisle("AAAAAAA#########ACCCADACCCA#########AAAAAAA",
                                "ADDHDDA#########B#########B#########ADDIDDA",
                                "ADCCCDA#########B#########B#########ADBBBDA",
                                "ADCEEDA#########B#########B#########ADBBBDA",
                                "ADCEEDA#########B#########B#########ADBBBDA",
                                "ADCCCDA#########B#########B#########ADBBBDA",
                                "ADEECDA#########B#########B#########ADBBBDA",
                                "ADEECDA#########B#########B#########ADBBBDA",
                                "ADCCCDA#########B#########B#########ADBBBDA",
                                "ADDDDDA#########B#########B#########ADDDDDA",
                                "AAAAAAA#########ACBBBCBBBCA#########AAAAAAA")
                        .aisle("################AAACADACAAA################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################ACBBBCBBBCA################")
                        .aisle("################AAACADACAAA################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################B#########B################",
                                "################ACCCACACCCA################")
                        .aisle("################AFFFF@FFFFA################",
                                "################ABBBBCBBBBA################",
                                "################ABBBBCBBBBA################",
                                "################ABBBBCBBBBA################",
                                "################ABBBBCBBBBA################",
                                "################ABBBBCBBBBA################",
                                "################ACCCCCCCCCA################",
                                "################ACEECBCEECA################",
                                "################ACEECBCEECA################",
                                "################ACCCCCCCCCA################",
                                "################AAAAAAAAAAA################")
                        .where("F", Predicates.blocks(CBBlocks.NEURAL_COOLING_CONDUIT.get())
                                .or(Predicates.autoAbilities(CBRecipeTypes.HOSTILE_OBSERVATION))
                                .or(Predicates.autoAbilities(false, false, true))
                                .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setPreviewCount(1)))
                        .where("#", Predicates.any())
                        .where("C", Predicates.blocks(CBBlocks.CONSCIOUSNESS_LINKER.get()))
                        .where("D", Predicates.blocks(CBBlocks.NEURAL_COOLING_CONDUIT.get()))
                        .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                        .where("H", Predicates.abilities(PartAbility.COMPUTATION_DATA_RECEPTION))
                        .where("I", Predicates.abilities(CBPartAbility.NEURAL_MODEL_ACCESSOR))
                        .where("G", Predicates.blocks(CBBlocks.CONSCIOUSNESS_LINKER.get())
                                .or(Predicates.blocks(CBBlocks.NEURAL_NETWORK_CASING.get())))
                        .where("#", Predicates.any())
                        .where("E", Predicates.blocks(CBBlocks.CONSCIOUSNESS_CONTROLLER.get()))
                        .where("B", Predicates.blocks(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS.get()))
                        .where("A", Predicates.blocks(CBBlocks.NEURAL_NETWORK_CASING.get()))
                        .build())
                .allowFlip(false)
                .allowExtendedFacing(false)
                .workableCasingModel(CTNHBio.id("block/casings/neural_cooling_conduit"),
                        GTCEu.id("block/multiblock/research_station"))
                .appearanceBlock(CBBlocks.NEURAL_COOLING_CONDUIT)
                .additionalDisplay(ModelOutputLine::addModelOutputLine)
                .tooltips(
                        no_overclock.translate().withStyle(ChatFormatting.DARK_RED),
                        observer_info.translate().withStyle(ChatFormatting.WHITE),
                        observer_chance_boost.translate().withStyle(ChatFormatting.YELLOW))
                .register();
    }

    @CN("观测实体来为数据模型收集数据,可通过并行控制仓同时观测多个相同实体")
    @EN("Observe entities to collect data for data model，and can execute parallel observations of multiple identical entities with Parallel Control Hatches")
    static Lang observer_info;

    @CN("并行观测时，产出概率将乘以并行数")
    @EN("In parallel observation, the output chance is multiplied by parallel amount")
    static Lang observer_chance_boost;

    @CN("无法超频")
    @EN("NO Overclock")
    static Lang no_overclock;

    // spotless on
}
