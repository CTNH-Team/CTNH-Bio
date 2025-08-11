package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.block.IMachineBlock;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.api.registry.registrate.MultiblockMachineBuilder;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.machine.greatflesh.GreatFleshBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CBRegistrate extends GTRegistrate {
    protected CBRegistrate() {
        super(CTNHBio.MODID);
    }
    public static CBRegistrate create() {
        return new CBRegistrate();
    }
    public MultiblockMachineBuilder biomultiblock(String name, Function<IMachineBlockEntity, ? extends MultiblockControllerMachine> metaMachine) {
        return new MultiblockMachineBuilder(this, name, metaMachine,
                LivingMetaMachineBlock::new, MetaMachineItem::new,
                (type, pos, state) -> new LivingMetaMachineBlockEntity<>(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get()));
    }

    @Override @NotNull @ParametersAreNonnullByDefault
    public <DEFINITION extends MachineDefinition> MachineBuilder<DEFINITION> machine(String name, Function<ResourceLocation, DEFINITION> definitionFactory, Function<IMachineBlockEntity, MetaMachine> metaMachine, BiFunction<BlockBehaviour.Properties, DEFINITION, IMachineBlock> blockFactory, BiFunction<IMachineBlock, Item.Properties, MetaMachineItem> itemFactory, TriFunction<BlockEntityType<?>, BlockPos, BlockState, IMachineBlockEntity> blockEntityFactory) {
        return super.machine(name, definitionFactory, metaMachine, blockFactory, itemFactory, blockEntityFactory)
                .hasBER(false);
    }

    @Override @NotNull @ParametersAreNonnullByDefault
    public MachineBuilder<MachineDefinition> machine(String name, Function<IMachineBlockEntity, MetaMachine> metaMachine) {
        return super.machine(name, metaMachine)
                .hasBER(false);
    }
}
