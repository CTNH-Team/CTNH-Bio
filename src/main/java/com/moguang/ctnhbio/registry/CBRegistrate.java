package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.api.registry.registrate.MultiblockMachineBuilder;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.machine.greatflesh.GreatFleshBlockEntity;

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
                (type, pos, state) -> new GreatFleshBlockEntity(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get()));
    }
}
