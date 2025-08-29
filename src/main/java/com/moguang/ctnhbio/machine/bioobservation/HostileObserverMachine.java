package com.moguang.ctnhbio.machine.bioobservation;

import com.gregtechceu.gtceu.api.capability.IOpticalComputationProvider;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationReceiver;
import com.gregtechceu.gtceu.api.capability.forge.GTCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.moguang.ctnhbio.machine.multiblock.part.NeuralModelAccessorMachine;
import com.moguang.ctnhbio.api.machine.trait.NotifiableEntityContainer;
import lombok.Getter;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Optional;

public class HostileObserverMachine extends WorkableElectricMultiblockMachine implements IOpticalComputationReceiver {
    //prop
    private AABB aabb; //b1~b7 l1~r1 u1~u3
    //traits
    @SuppressWarnings("unused")
    private NotifiableEntityContainer inputEntity;
    @SuppressWarnings("unused")
    private NeuralModelAccessorMachine modelAccessor;
    @Getter
    private IOpticalComputationProvider computationProvider;
    public HostileObserverMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public void onStructureInvalid() {
        //ensure it wasn't the one broken
        getParts().stream()
                .filter(NeuralModelAccessorMachine.class::isInstance)
                .map(NeuralModelAccessorMachine.class::cast)
                .findAny()
                .ifPresent(accessor-> accessor.setLocked(false));

        aabb = null;
        inputEntity = null;
        modelAccessor = null;
        computationProvider = null;

        super.onStructureInvalid();
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        getParts().stream()
                .map(part->part.self()
                        .holder
                        .self()
                        .getCapability(GTCapability.CAPABILITY_COMPUTATION_PROVIDER))
                .filter(LazyOptional::isPresent)
                .map(LazyOptional::resolve)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny()
                .ifPresent(prov->computationProvider=prov);

        getParts().stream()
                .filter(NeuralModelAccessorMachine.class::isInstance)
                .map(NeuralModelAccessorMachine.class::cast)
                .findAny()
                .ifPresent(accessor->{
                    modelAccessor = accessor;
                    addHandlerList(RecipeHandlerList.of(IO.IN, accessor.getItemHolder()));
                    addHandlerList(RecipeHandlerList.of(IO.OUT, accessor.getItemHolder()));
                });

        inputEntity = new NotifiableEntityContainer(this, getAABB(), IO.IN,false);
        addHandlerList(RecipeHandlerList.of(IO.IN, inputEntity));
    }

    public AABB getAABB() {
        if (aabb == null) {
            final Direction b = getFrontFacing().getOpposite();
            final Direction l = b.getCounterClockWise();
            final Direction u = Direction.UP;

            aabb = new AABB(
                    getPos().relative(b,1).relative(l,1).relative(u,1),
                    getPos().relative(b,7).relative(l,-1).relative(u,3)
            );
        }
        return aabb;
    }
}
