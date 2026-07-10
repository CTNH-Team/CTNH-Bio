package com.moguang.ctnhbio.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import com.moguang.ctnhbio.api.capability.recipe.NutrientRecipeCapability;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotifiableNutrientHandler extends NotifiableRecipeHandlerTrait<Float> {

    @Getter
    @Persisted
    private float amount;
    @Getter
    @Persisted
    private final float capacity;

    public NotifiableNutrientHandler(MetaMachine machine, float capacity) {
        super(machine);
        this.capacity = capacity;
    }

    public float getLeft() {
        return capacity - amount;
    }

    public float add(float toAdd) {
        float added = Math.max(0, Math.min(toAdd, getLeft()));
        if (added > 0) {
            amount += added;
            notifyListeners();
        }
        return added;
    }

    public float extract(float toExtract) {
        float extracted = Math.max(0, Math.min(toExtract, amount));
        if (extracted > 0) {
            amount -= extracted;
            notifyListeners();
        }
        return extracted;
    }

    @Override
    public IO getHandlerIO() {
        return IO.BOTH;
    }

    @Override
    public boolean handleRecipe(IO io, GTRecipe recipe, List<Float> left, boolean simulate) {
        boolean changed = false;
        for (var iterator = left.listIterator(); iterator.hasNext();) {
            float nutrient = iterator.next();
            float transferred = io == IO.IN ? Math.min(amount, nutrient) : Math.min(getLeft(), nutrient);
            if (!simulate && transferred > 0) {
                if (io == IO.IN) {
                    amount -= transferred;
                } else {
                    amount += transferred;
                }
                changed = true;
            }
            if (transferred == nutrient) {
                iterator.remove();
            } else {
                iterator.set(nutrient - transferred);
            }
        }
        if (!simulate && changed) {
            notifyListeners();
        }
        return left.isEmpty();
    }

    @Override
    public @NotNull List<Object> getContents() {
        return List.of(amount);
    }

    @Override
    public double getTotalContentAmount() {
        return amount;
    }

    @Override
    public NutrientRecipeCapability getCapability() {
        return NutrientRecipeCapability.CAP;
    }
}
