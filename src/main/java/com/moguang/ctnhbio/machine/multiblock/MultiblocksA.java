package com.moguang.ctnhbio.machine.multiblock;

import com.github.elenterius.biomancy.BiomancyMod;
import com.github.elenterius.biomancy.init.ModBlocks;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.block.LivingMultiMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
import com.moguang.ctnhbio.machine.bioobservation.HostileObserverMachine;
import com.moguang.ctnhbio.machine.greatflesh.GreatFleshMachine;
import com.moguang.ctnhbio.machine.greatflesh.GreatFleshRenderer;
import com.moguang.ctnhbio.registry.CBBlocks;
import com.moguang.ctnhbio.registry.CBPartAbility;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ForgeRegistries;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class MultiblocksA {
    public static void init() {}
    public static MultiblockMachineDefinition BASIC_MULTI_LIVING_MACHINE = REGISTRATE.biomultiblock("basic_multi_living_machine", WorkableLivingMultiblockMachine::new)
            .recipeType(CBRecipeTypes.GREAT_FLESH)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAA", "AAA", "AAA")
                    .aisle("AAA", "AAA", "AAA")
                    .aisle("AAA", "A@A", "AAA")
                    .where("A", Predicates.blocks(ModBlocks.FLESH.get()).setMinGlobalLimited(10)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.blocks(Blocks.AIR)))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))

                    .build())

            .workableCasingModel(BiomancyMod.createRL("block/flesh"), GTCEu.id("block/multiblock/assembly_line"))
            .simpleModel(new ResourceLocation("minecraft", "block/air"))
            .additionalDisplay((controller, components) -> {
                if(controller instanceof WorkableLivingMultiblockMachine machine){
                    components.add(Component.translatable("jade.nutrient.info",
                            Component.translatable(FormattingUtil.formatNumbers(machine.getNutrientAmount())).setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
                }
            })
            .hasBER(false)
//            .onBlockEntityRegister(beType -> {
//                @SuppressWarnings("unchecked")
//                BlockEntityType<LivingMetaMachineBlockEntity> typed = (BlockEntityType<LivingMetaMachineBlockEntity>) (BlockEntityType<?>)beType;
//                BlockEntityRenderers.register(typed, ctx -> new GreatFleshRenderer(ctx));
//            })
            .register();

    public static MultiblockMachineDefinition GREAT_FLESH = REGISTRATE
            .biomultiblock("great_flesh",
                    GreatFleshMachine::new)
            .recipeType(CBRecipeTypes.GREAT_FLESH)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAA", "AAA", "AAA")
                    .aisle("AAA", "AAA", "AAA")
                    .aisle("AAA", "A@A", "AAA")
                    .where("A", Predicates.blocks(ModBlocks.FLESH.get()).setMinGlobalLimited(10)
                            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                            .or(Predicates.blocks(Blocks.AIR)))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))

                    .build())

            .workableCasingModel(BiomancyMod.createRL("block/flesh"), GTCEu.id("block/multiblock/assembly_line"))
            //.simpleModel(new ResourceLocation("minecraft", "block/air"))
            .additionalDisplay((controller, components) -> {
                if(controller instanceof WorkableLivingMultiblockMachine machine){
                    components.add(Component.translatable("jade.nutrient.info",
                            Component.translatable(FormattingUtil.formatNumbers(machine.getNutrientAmount())).setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
                }
            })
            .hasBER(false)
            .onBlockEntityRegister(beType -> {
                @SuppressWarnings("unchecked")
                BlockEntityType<LivingMetaMachineBlockEntity> typed = (BlockEntityType<LivingMetaMachineBlockEntity>) (BlockEntityType<?>)beType;
                BlockEntityRenderers.register(typed, ctx -> new GreatFleshRenderer(ctx));
            })
            .register();



    public static MultiblockMachineDefinition CIRCULATORY_SYSTEM = REGISTRATE
            .biomultiblock("circulatory_system",
                    WorkableLivingMultiblockMachine::new,
                    LivingMultiMetaMachineBlock::new,
                    MetaMachineItem::new
            )
            .recipeType(CBRecipeTypes.GREAT_FLESH)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "BCCCB", "BCCCB", "BCCCB", "AAAAA")
                    .aisle("ADDDA", "CEEEC", "C###C", "C###C", "ADDDA")
                    .aisle("ADDDA", "CEFEC", "C#G#C", "C#F#C", "ADDDA")
                    .aisle("ADDDA", "CEEEC", "C###C", "C###C", "ADDDA")
                    .aisle("AAAAA", "BC@CB", "BCCCB", "BCCCB", "AAAAA")
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("biomancy:acid_fluid_block"))))
                    .where("C", Predicates.blocks(CBBlocks.IMPERMEABLE_MEMBRANE.get())
                           .or(Predicates.autoAbilities(definition.getRecipeTypes()))
                    )
                    .where("F", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:primal_flesh_casing"))))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("G", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:nichrome_coil_block"))))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:bio_acid_casing"))))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:ornate_flesh_casing"))))
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:flesh_casing"))))
                    .build())

            .workableCasingModel(CTNHBio.id("block/casings/ornate_flesh_casing"),
                    CTNHBio.id("block/multiblock/red"))
            .appearanceBlock(CBBlocks.ORNATE_FLESH_CASING)
            //.simpleModel(new ResourceLocation("minecraft", "block/acacia_log"))
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
            .recipeType(CBRecipeTypes.GREAT_FLESH)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAA", "BCACB", "BDADB", "BCACB", "AAAAA")
                    .aisle("AEEEA", "CFGFC", "DFGFD", "CFGFC", "AEEEA")
                    .aisle("AEEEA", "AGGGA", "HGGGH", "AGGGA", "AEEEA")
                    .aisle("AEEEA", "CE@EC", "DEIED", "CEEEC", "AEEEA")
                    .aisle("AAAAA", "B###B", "B###B", "B###B", "AAAAA")
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("kubejs:flesh_casing_fence"))))
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("gtceu:opv_machine_casing"))))
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:primal_flesh_casing"))))
                    .where("H", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:ornate_flesh_casing"))))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:ornate_flesh_casing"))))
                    .where("#", Predicates.any())
                    .where("F", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:acid_flesh_casing"))))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("biomancy:flesh_fence"))))
                    .where("I", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:primal_flesh_casing"))))
                    .where("G", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("biomancy:smooth_primal_flesh"))))
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
            .recipeType(CBRecipeTypes.GREAT_FLESH)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#BBBBB#", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                    .aisle("BCCCCCB", "#CDDDC#", "#C###C#", "#E###E#", "#E###E#", "#E###E#", "#EE#EE#", "#######")
                    .aisle("BCFGFCB", "#DFFFD#", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "#EF#FE#", "##H#H##")
                    .aisle("BCGGGCB", "#DFGFD#", "###G###", "###E###", "###E###", "###E###", "###B###", "#######")
                    .aisle("BCFGFCB", "#DFFFD#", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "#EF#FE#", "##H#H##")
                    .aisle("BCCCCCB", "#CD@DC#", "#C###C#", "#E###E#", "#E###E#", "#E###E#", "#EE#EE#", "#######")
                    .aisle("#BBBBB#", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("biomancy:ornate_flesh_slab"))))
                    .where("H", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("biomancy:flesh_spike"))))
                    .where("G", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:primal_flesh_casing"))))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("#", Predicates.any())
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:ornate_flesh_casing"))))
                    .where("F", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:acid_flesh_casing"))))
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("biomancy:flesh_fence"))))
                    .where("D", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("ctnhbio:flesh_casing"))))
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
            .multiblock("hostile_observer", HostileObserverMachine::new)
            .recipeType(CBRecipeTypes.HOSTILE_OBSERVATION)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAAAAAAAAAA", "###############", "###############", "###############", "###############", "###############")
                    .aisle("AAAAAAAAABBBBBA", "#CCCCC#########", "#CAAAC#########", "#CADAC#########", "#CAAAC#########", "#CCCCC#########")
                    .aisle("AAAAAAAAABBBBBA", "#CEEEC####CCC##", "#F###G####CGC##", "#F###B####CGC##", "#F###G####CGC##", "#CFFFC####CCC##")
                    .aisle("AAAABBBBBBBBBBA", "#CEEEB####CHC##", "#F###B####GHG##", "#F###B####GHG##", "#F###B####GHG##", "#CFFFC####CBC##")
                    .aisle("AAAAAAAAABBBBBA", "#CEEEC####CCC##", "#F###G####CFC##", "#F###B####CHC##", "#F###G####CFC##", "#CFFFC####CCC##")
                    .aisle("AAAAAAAAABBBBBA", "#CEEEC####CCC##", "#F###C####FFF##", "#F###C####FHF##", "#F###C####FFF##", "#CFFFC####FCF##")
                    .aisle("AAAAAAAAABAAABA", "#CEEEC###CCCCC#", "#F###F###CIFIC#", "#F###CCCCCIHIC#", "#F###F###CIFIC#", "#CFFFC###CCCCC#")
                    .aisle("AAAAAABBBBAAABA", "#CEEEC###CCCCC#", "#F###CCCCCIFIJ#", "#F###HHHHHHHHJ#", "#F###CCCCCIFIJ#", "#CFFFC###CBBBC#")
                    .aisle("AAAAAAAAABAAABA", "#CEEEC###CCCCC#", "#F###F###CICIC#", "#F###CCCCCCNCC#", "#F###F###CICIC#", "#CFFFC###CCCCC#")
                    .aisle("AAAAAAAAABBBBBA", "#CC@CC####BBB##", "#CFFFC#########", "#CFFFC#########", "#CFFFC#########", "#CCCCC#########")
                    .aisle("AAAAAAAAAAAAAAA", "###############", "###############", "###############", "###############", "###############")
                    .aisle("C#####CAAAAAAAA", "###############", "###############", "###############", "###############", "###############")
                    .aisle("#######AAAAAAAA", "##############A", "##############A", "##############A", "##############A", "##############A")
                    .where("G", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse("gtceu:stainless_steel_frame"))))
                    .where("J", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse("gtceu:computer_heat_vent"))))
                    .where("@", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("D", Predicates.abilities(PartAbility.COMPUTATION_DATA_RECEPTION))
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse("gtceu:computer_heat_vent"))))
                    .where("A", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse("gtceu:high_power_casing"))))
                    .where("C", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse("gtceu:advanced_computer_casing"))))
                    .where("E", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse("gtceu:computer_casing"))))
                    .where("F", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse("gtceu:fusion_glass"))))
                    .where("H", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse("gtceu:computer_heat_vent"))))
                    .where("I", Predicates.blocks(ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse("gtceu:nonconducting_casing"))))
                    .where("N",Predicates.abilities(CBPartAbility.NEURAL_MODEL_ACCESSOR))
                    .build())

            .hasBER(false)
            .sidedWorkableCasingModel(GTCEu.id("block/casings/hpca/advanced_computer_casing"),
                    GTCEu.id("block/multiblock/research_station"))
            .appearanceBlock(GTBlocks.HIGH_POWER_CASING)
            .register();
}
