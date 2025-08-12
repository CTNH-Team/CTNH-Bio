package com.moguang.ctnhbio.machine.multiblock;

import com.github.elenterius.biomancy.BiomancyMod;
import com.github.elenterius.biomancy.init.ModBlocks;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.block.LivingMultiMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
import com.moguang.ctnhbio.machine.digester.DigesterBlockEntityRenderer;
import com.moguang.ctnhbio.machine.greatflesh.GreatFleshMachine;
import com.moguang.ctnhbio.machine.greatflesh.GreatFleshRenderer;
import com.moguang.ctnhbio.registry.CBBlocks;
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
            //.simpleModel(new ResourceLocation("minecraft", "block/acacia_log"))
            .additionalDisplay((controller, components) -> {
                if(controller instanceof WorkableLivingMultiblockMachine machine){
                    components.add(Component.translatable("jade.nutrient.info",
                            Component.translatable(FormattingUtil.formatNumbers(machine.getNutrientAmount())).setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
                }
            })
            .hasBER(false)
            .register();
}
