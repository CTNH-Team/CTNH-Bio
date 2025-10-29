package com.moguang.ctnhbio.machine.bioobservation;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.moguang.ctnhbio.api.machine.trait.NotifiableEntityContainer;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;

public class HostileObserverMachine extends WorkableElectricMultiblockMachine{

    public HostileObserverMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        //add traits
        new NotifiableEntityContainer(this, getAABB(), IO.IN,false);
    }

    public AABB getAABB() {
        final Direction b = getFrontFacing().getOpposite();
        final Direction l = b.getCounterClockWise();
        final Direction u = Direction.UP;

        return new AABB(
                getPos().relative(b,0).relative(l,5).relative(u,0),
                getPos().relative(b,10).relative(l,-5).relative(u,10)
        );
    }
}
