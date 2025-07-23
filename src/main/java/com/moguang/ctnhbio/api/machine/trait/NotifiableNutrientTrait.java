package com.moguang.ctnhbio.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.moguang.ctnhbio.api.capability.NutrientRecipeCapability;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotifiableNutrientTrait extends NotifiableRecipeHandlerTrait<Double> {
    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            NotifiableNutrientTrait.class, NotifiableRecipeHandlerTrait.MANAGED_FIELD_HOLDER);
    @Persisted
    public double nutrientCapacity;
    @Setter
    @Getter
    @Persisted
    public double nutrientAmount;
    public NotifiableNutrientTrait(MetaMachine machine, double capacity) {
        super(machine);
        this.nutrientCapacity = capacity;
        this.nutrientAmount = 0;
    }
    public double getLeft() {
        return nutrientCapacity - nutrientAmount;
    }

    @Override
    public IO getHandlerIO() {
        return IO.BOTH;
    }
    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public List<Double> handleRecipeInner(IO io, GTRecipe gtRecipe, List<Double> list, boolean simulate) {
        if (io == IO.IN) {
            for (var it = list.listIterator(); it.hasNext();) { // 使用索引遍历
                double trait = it.next();
                if (trait == 0) {
                    it.remove();
                    continue;
                }
                double consume = Math.min(getLeft(), trait);
                if (!simulate) {
                    nutrientAmount += consume;
                }
                if (consume == trait) {
                    it.remove();
                    continue;
                }
                it.set(trait - consume);
            }
        }
        else {
            for (var it = list.listIterator(); it.hasNext();) {
                double trait = it.next();
                if (trait == 0) {
                    it.remove();
                    continue;
                }
                double cost = Math.max(0, trait);
                if (!simulate) {
                    nutrientAmount -= cost;
                }
                if (cost == trait) {
                    it.remove();
                    continue;
                }
                it.set(trait - cost);
            }
        }
        return list;
    }

    @Override
    public @NotNull List<Object> getContents() {
        return List.of(nutrientAmount);
    }

    @Override
    public double getTotalContentAmount() {
        return nutrientAmount;
    }

    @Override
    public NutrientRecipeCapability getCapability() {
        return NutrientRecipeCapability.CAP;
    }
}
