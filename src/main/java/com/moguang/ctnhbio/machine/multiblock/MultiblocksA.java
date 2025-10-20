package com.moguang.ctnhbio.machine.multiblock;

import com.github.elenterius.biomancy.BiomancyMod;
import com.github.elenterius.biomancy.init.ModBlocks;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.block.LivingMultiMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.item.LivingMetaMachineItem;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
import com.moguang.ctnhbio.api.recipe.CBRecipeModifier;
import com.moguang.ctnhbio.client.Renderer.ColorableMachineBlockEntityRenderer;
import com.moguang.ctnhbio.client.Renderer.ColorableMachineItemRenderer;
import com.moguang.ctnhbio.client.Renderer.LivingMetaMachineBERProvider;
import com.moguang.ctnhbio.client.model.GreatFleshModel;
import com.moguang.ctnhbio.machine.greatflesh.GreatFleshMachine;

import com.moguang.ctnhbio.registry.CBBlocks;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

import static com.gregtechceu.gtceu.api.pattern.Predicates.autoAbilities;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class MultiblocksA {
    public static void init() {}

    public static MultiblockMachineDefinition GREAT_FLESH = REGISTRATE
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
                            //.or(Predicates.blocks(Blocks.AIR))


                    )
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))

                    .build())

            .workableCasingModel(BiomancyMod.createRL("block/flesh"), GTCEu.id("block/multiblock/assembly_line"))
            //.simpleModel(ResourceLocation.tryBuild("minecraft", "block/air"))
            .additionalDisplay((controller, components) -> {
                if(controller instanceof WorkableLivingMultiblockMachine machine){
                    components.add(Component.translatable("jade.nutrient.info",
                            Component.translatable(FormattingUtil.formatNumbers(machine.getNutrientAmount())).setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
                }
            })
            .hasBER(false)
            .onBlockEntityRegister(beType -> {
                if (FMLEnvironment.dist == Dist.CLIENT) {
                    LivingMetaMachineBERProvider.registerRenderer(beType, "great_flesh", false);
                }
            })
            .register();



    public static MultiblockMachineDefinition CIRCULATORY_SYSTEM = REGISTRATE
            .biomultiblock("circulatory_system",
                    WorkableLivingMultiblockMachine::new,
                    LivingMultiMetaMachineBlock::new,
                    MetaMachineItem::new
            )
            .cnLangValue("循环系统")
            .recipeType(CBRecipeTypes.BIO_REACTOR_RECIPES)
            .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, CBRecipeModifier::batchMode)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "BCCCB", "BCCCB", "BCCCB", "AAAAA")
                    .aisle("ADDDA", "CEEEC", "C###C", "C###C", "ADDDA")
                    .aisle("ADDDA", "CEFEC", "C#G#C", "C#F#C", "ADDDA")
                    .aisle("ADDDA", "CEEEC", "C###C", "C###C", "ADDDA")
                    .aisle("AAAAA", "BC@CB", "BCCCB", "BCCCB", "AAAAA")
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("biomancy:acid_fluid_block"))))
                    .where("C", Predicates.blocks(CBBlocks.IMPERMEABLE_MEMBRANE.get())

                    )
                    .where("F", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:primal_flesh_casing"))))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("G", Predicates.heatingCoils())
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:bio_acid_casing"))))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:ornate_flesh_casing")))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                    )
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:flesh_casing"))))
                    .build())

            .workableCasingModel(CTNHBio.id("block/casings/ornate_flesh_casing"),
                    CTNHBio.id("block/multiblock/red"))
            .appearanceBlock(CBBlocks.ORNATE_FLESH_CASING)
            //.simpleModel(ResourceLocation.tryBuild("minecraft", "block/acacia_log"))
            .additionalDisplay((controller, components) -> {
                if(controller instanceof WorkableLivingMultiblockMachine machine){
                    components.add(Component.translatable("jade.nutrient.info",
                            Component.translatable(FormattingUtil.formatNumbers(machine.getNutrientAmount())).setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
                }
            })
            .register();

    public static MultiblockMachineDefinition COGNI_ASSEMBLER = REGISTRATE
            .biomultiblock("cogni_assembler",
                    WorkableLivingMultiblockMachine::new,
                    LivingMultiMetaMachineBlock::new,
                    MetaMachineItem::new
            )
            .cnLangValue("意识装配机")
            .recipeTypes(CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES,CBRecipeTypes.CONSCIOUSNESS_ASSEMBLY)
            .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, CBRecipeModifier::batchMode)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "BCACB", "BDADB", "BCACB", "AAAAA")
                    .aisle("AEEEA", "CFGFC", "DFGFD", "CFGFC", "AEEEA")
                    .aisle("AEEEA", "AGGGA", "HGGGH", "AGGGA", "AEEEA")
                    .aisle("AEEEA", "CE@EC", "DEIED", "CEEEC", "AEEEA")
                    .aisle("AAAAA", "B###B", "B###B", "B###B", "AAAAA")
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("kubejs:flesh_casing_fence"))))
                    .where("B", Predicates.blocks(CBBlocks.SYNAPTIC_CASING.get()))
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:primal_flesh_casing")))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes())))
                    .where("H", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:ornate_flesh_casing"))))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:ornate_flesh_casing")))

                    )
                    .where("#", Predicates.any())
                    .where("F", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:acid_flesh_casing"))))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("biomancy:flesh_fence"))))
                    .where("I", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:primal_flesh_casing"))))
                    .where("G", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("biomancy:smooth_primal_flesh"))))
                    .build())

            .workableCasingModel(CTNHBio.id("block/casings/primal_flesh_casing"),
                    CTNHBio.id("block/multiblock/red"))
            .appearanceBlock(CBBlocks.PRIMAL_FLESH_CASING)
            .additionalDisplay((controller, components) -> {
                if(controller instanceof WorkableLivingMultiblockMachine machine){
                    components.add(Component.translatable("jade.nutrient.info",
                            Component.translatable(FormattingUtil.formatNumbers(machine.getNutrientAmount())).setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
                }
            })
            .register();

    public static MultiblockMachineDefinition WEATHERER = REGISTRATE
            .biomultiblock("weatherer",
                    WorkableLivingMultiblockMachine::new,
                    LivingMultiMetaMachineBlock::new,
                    MetaMachineItem::new
            )
            .cnLangValue("风化器")
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
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("biomancy:ornate_flesh_slab"))))
                    .where("H", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("biomancy:flesh_spike"))))
                    .where("G", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:primal_flesh_casing"))))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("#", Predicates.any())
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:ornate_flesh_casing")))
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                    )
                    .where("F", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:acid_flesh_casing"))))
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("biomancy:flesh_fence"))))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:flesh_casing"))))
                    .build())

            .workableCasingModel(CTNHBio.id("block/casings/flesh_casing"),
                    CTNHBio.id("block/multiblock/red"))
            .appearanceBlock(CBBlocks.FLESH_CASING)
            .additionalDisplay((controller, components) -> {
                if(controller instanceof WorkableLivingMultiblockMachine machine){
                    components.add(Component.translatable("jade.nutrient.info",
                            Component.translatable(FormattingUtil.formatNumbers(machine.getNutrientAmount())).setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
                }
            })
            .register();

    public static MultiblockMachineDefinition HOSTILE_OBSERVER = REGISTRATE
            .multiblock("hostile_observer", WorkableElectricMultiblockMachine::new)
            .cnLangValue("敌意观测站")
            .recipeType(CBRecipeTypes.GREAT_FLESH)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("################AAAAAAAAAAA################", "################AAAAAAAAAAA################", "################AAAAAAAAAAA################", "################AAABBBBBAAA################", "################ABBBCCCBBBA################", "################ABDDDDDDDBA################", "################ABBBCCCBBBA################", "################AAABBBBBAAA################", "################AAAAAAAAAAA################", "################AAAAAAAAAAA################", "################AAAAAAAAAAA################")
                    .aisle("################AAABACABAAA################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################ABBBABABBBA################")
                    .aisle("################AAABACABAAA################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################ABAAABAAABA################")
                    .aisle("AAAAAAA#########ABBBACABBBA#########AAAAAAA", "ACCCCCA#########A#########A#########ACCCCCA", "ACBBBCA#########A#########A#########ACEEECA", "ACBDDCA#########A#########A#########ACEEECA", "ACBDDCA#########A#########A#########ACEEECA", "ACBBBCA#########A#########A#########ACEEECA", "ACDDBCA#########A#########A#########ACEEECA", "ACDDBCA#########A#########A#########ACEEECA", "ACBBBCA#########A#########A#########ACEEECA", "ACCCCCA#########A#########A#########ACCCCCA", "AAAAAAA#########ABAAABAAABA#########AAAAAAA")
                    .aisle("AAAAAAA#########AAAAACAAAAA#########AAAAAAA", "F#####C#########A#########A#########CCCCCCA", "F###B#C#########A#########A#########CBBBBBA", "F#####CAAAAAAAAAA#########AAAAAAAAAACB###BA", "F#####CAEEEEEEEAC#########CAEEEEEEEACB###BA", "F#B#B#CAEEEEEEEAC#########CAEEEEEEEACB###BA", "F#####CAEEEEEEEAC#########CAEEEEEEEACB###BA", "F#####CAAAAAAAAAA#########AAAAAAAAAACB###BA", "F#B###C#########A#########A#########CBBBBBA", "F#####C#########A#########A#########CCCCCCA", "ABABBBA#########AAAAABAAAAA#########ABABABA")
                    .aisle("AAAAAAA#########ADDDDDDDDDA#########AAAAAAA", "F#####C#########A####B####A#########CCCCCCA", "FBBBBBC#########A####B####A#########CBDDDBA", "FCDCBBBEEEEEEEEAA####B####AAEEEEEEEABBDDD#A", "FCBCDCBCCCCCCCCAC####B####CACCCCCCCCB#DDD#A", "FBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB#DDD#A", "FCDCBCBCCCCCCCCCC####B####CCCCCCCCCAB#DDD#A", "FCBCDBBEEEEEEEEEA####B####AAEEEEEEEABBDDD#A", "FBBBBBC#########A####B####A#########CBDDDBA", "F#####C#########A####B####A#########CCCCCCA", "ABABABA#########ADDDDDDDDDA#########ABABABA")
                    .aisle("AAAAAAA#########AAAAACAAAAA#########AAAAAAA", "F#####C#########A#########A#########CCCCCCA", "F###B#C#########A#########A#########CBBBBBA", "F#####CAAAAAAAAAA#########AAAAAAAAAACB###BA", "F#####CAEEEEEEEAC#########CAEEEEEEEACB###BA", "F#B#B#CAEEEEEEEAC#########CAEEEEEEEACB###BA", "F#####CAEEEEEEEAC#########CAEEEEEEEACB###BA", "F#####CAAAAAAAAAA#########AAAAAAAAAACB###BA", "F#B###C#########A#########A#########CBBBBBA", "F#####C#########A#########A#########CCCCCCA", "ABBBABA#########AAAAABAAAAA#########ABBBBBA")
                    .aisle("AAAAAAA#########ABBBACABBBA#########AAAAAAA", "ACCCCCA#########A#########A#########ACCGCCA", "ACBBBCA#########A#########A#########ACEEECA", "ACBDDCA#########A#########A#########ACEEECA", "ACBDDCA#########A#########A#########ACEEECA", "ACBBBCA#########A#########A#########ACEEECA", "ACDDBCA#########A#########A#########ACEEECA", "ACDDBCA#########A#########A#########ACEEECA", "ACBBBCA#########A#########A#########ACEEECA", "ACCCCCA#########A#########A#########ACCCCCA", "AAAAAAA#########ABAAABAAABA#########AAAAAAA")
                    .aisle("################AAABACABAAA################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################ABAAABAAABA################")
                    .aisle("################AAABACABAAA################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################A#########A################", "################ABBBABABBBA################")
                    .aisle("################AAAAA@AAAAA################", "################AEEEEBEEEEA################", "################AEEEEBEEEEA################", "################AEEEEBEEEEA################", "################AEEEEBEEEEA################", "################AEEEEBEEEEA################", "################ABBBBBBBBBA################", "################ABDDBCBDDBA################", "################ABDDBCBDDBA################", "################ABBBBBBBBBA################", "################AAAAAAAAAAA################")
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:consciousness_linker"))))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:neural_cooling_conduit"))))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("#", Predicates.any())
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:consciousness_controller"))))
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:consciousness_sensor_glass"))))
                    .where("F", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:neural_cooling_conduit")))
                            .or(Predicates.abilities(PartAbility.COMPUTATION_DATA_RECEPTION).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1).setMaxGlobalLimited(2).setPreviewCount(1))
                    )
                    .where("G", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("minecraft:grass_block"))))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("ctnhbio:neural_network_casing"))))
                    .build())
            .allowFlip(false)
            .allowExtendedFacing(false)
            .workableCasingModel(CTNHBio.id("block/casings/neural_cooling_conduit"),
                    CTNHBio.id("block/multiblock/red"))
            .appearanceBlock(CBBlocks.NEURAL_COOLING_CONDUIT)
            .additionalDisplay((controller, components) -> {
                if(controller instanceof WorkableLivingMultiblockMachine machine){
                    components.add(Component.translatable("jade.nutrient.info",
                            Component.translatable(FormattingUtil.formatNumbers(machine.getNutrientAmount())).setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
                }
            })
            .register();
}
