package com.moguang.ctnhbio.machine.multiblock;

import com.github.elenterius.biomancy.BiomancyMod;
import com.github.elenterius.biomancy.init.ModBlocks;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
import com.moguang.ctnhbio.registry.CBBlocks;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

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
            .register();

    public static MultiblockMachineDefinition COGNITIVE_ASSEMBLER = REGISTRATE.biomultiblock("cognitive_assembler", WorkableLivingMultiblockMachine::new)
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
            .appearanceBlock(CBBlocks.FLESH_CASING)
            .register();
}
