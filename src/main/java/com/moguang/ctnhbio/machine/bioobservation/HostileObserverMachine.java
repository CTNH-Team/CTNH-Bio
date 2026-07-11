package com.moguang.ctnhbio.machine.bioobservation;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.RecipeElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NetworkedComputationContainer;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.AABB;

import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
import com.moguang.ctnhbio.api.machine.trait.NotifiableEntityContainer;

public class HostileObserverMachine extends RecipeElectricMultiblockMachine {

    public HostileObserverMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        // add traits
        new NotifiableEntityContainer(this, getAABB(), IO.IN);
        new NetworkedComputationContainer(this, IO.IN);
    }

    public AABB getAABB() {
        final Direction b = getFrontFacing().getOpposite();
        final Direction l = b.getCounterClockWise();
        final Direction u = Direction.UP;

        return new AABB(
                getPos().relative(b, 0).relative(l, 5).relative(u, 0),
                getPos().relative(b, 10).relative(l, -5).relative(u, 10));
    }

    public static Component hostileObserverMachineModifier(MetaMachine machine, RecipeHandlerGroup group,
                                                           GTRecipe recipe) {
        var outputs = recipe.outputs.get(ModelRecipeCapability.CAP);
        if (outputs != null && !outputs.isEmpty()) {
            outputs.set(0, outputs.get(0).copyWithChance(outputs.get(0).getChance() * recipe.parallels));
        }
        return null;
    }
}
