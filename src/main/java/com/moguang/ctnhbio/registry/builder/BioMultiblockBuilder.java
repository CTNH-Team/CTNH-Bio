package com.moguang.ctnhbio.registry.builder;

import com.gregtechceu.gtceu.api.block.IMachineBlock;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.api.registry.registrate.MultiblockMachineBuilder;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
import com.moguang.ctnhbio.data.lang.Infos;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Function;

public class BioMultiblockBuilder extends MultiblockMachineBuilder {
    public BioMultiblockBuilder(GTRegistrate registrate, String name, Function<IMachineBlockEntity, ? extends MultiblockControllerMachine> metaMachine, BiFunction<BlockBehaviour.Properties, MultiblockMachineDefinition, IMachineBlock> blockFactory, BiFunction<IMachineBlock, Item.Properties, MetaMachineItem> itemFactory, TriFunction<BlockEntityType<?>, BlockPos, BlockState, IMachineBlockEntity> blockEntityFactory) {
        super(registrate, name, metaMachine, blockFactory, itemFactory, blockEntityFactory);
    }
    public BioMultiblockBuilder nutrientInfo(){
        this.additionalDisplay((controller, components) -> {
            if(controller instanceof WorkableLivingMultiblockMachine livingMultiblockMachine){
                components.add(Infos.Jade.Nutrient.translate(
                        Component.literal(FormattingUtil.formatNumbers(livingMultiblockMachine.getNutrientAmount())).setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))));
            }
        });
        return this;
    }
}
