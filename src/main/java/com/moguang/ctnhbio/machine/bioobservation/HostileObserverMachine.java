package com.moguang.ctnhbio.machine.bioobservation;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
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


    public static ModifierFunction hostileObserverMachineModifier(MetaMachine machine, GTRecipe gtRecipe){
        return recipe -> {
            Content model = recipe.outputs.get(ModelRecipeCapability.CAP).get(0);
            if(model != null)
            {
                GTRecipe newRecipe = recipe.copy();
                Content newModel = new Content(model.content, model.chance*recipe.parallels, 10000, 0);
                newRecipe.outputs.get(ModelRecipeCapability.CAP).set(0, newModel);
                return newRecipe;
            }
            return recipe;
        };
    }
}
