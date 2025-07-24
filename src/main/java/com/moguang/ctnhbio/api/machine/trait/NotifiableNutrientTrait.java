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
    @Persisted
    private final SynchronizedNutrientStorage sharedStorage;
    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            NotifiableNutrientTrait.class, NotifiableRecipeHandlerTrait.MANAGED_FIELD_HOLDER);
    public IO io;
    public NotifiableNutrientTrait(MetaMachine machine, SynchronizedNutrientStorage sharedStorage, IO io) {
        super(machine);
        this.sharedStorage = sharedStorage;
        this.io = io;
    }

    @Override
    public IO getHandlerIO() {
        return io;
    }
    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public List<Double> handleRecipeInner(IO io, GTRecipe gtRecipe, List<Double> list, boolean simulate) {
        if (io == IO.OUT) {
            for (var it = list.listIterator(); it.hasNext();) { // 使用索引遍历
                double trait = it.next();
                if (trait == 0) {
                    it.remove();
                    continue;
                }
                double consume = Math.min(sharedStorage.getLeft(), trait);
                if (!simulate) {
                    sharedStorage.add(consume);
                }
                if (consume == trait) {
                    it.remove();
                    continue;
                }
                it.set(trait - consume);
            }
        }
        else if (io == IO.IN){
            for (var it = list.listIterator(); it.hasNext();) {
                double trait = it.next();
                if (trait == 0) {
                    it.remove();
                    continue;
                }
                double cost = Math.min(sharedStorage.getAmount(), trait);
                if (!simulate) {
                    sharedStorage.extract(cost);
                }
                if (cost == trait) {
                    it.remove();
                    continue;
                }
                it.set(trait - cost);
            }
        }
        return list.isEmpty()? null : list;
    }

    @Override
    public @NotNull List<Object> getContents() {
        return List.of(sharedStorage.getAmount());
    }

    @Override
    public double getTotalContentAmount() {
        return sharedStorage.getAmount();
    }

    @Override
    public NutrientRecipeCapability getCapability() {
        return NutrientRecipeCapability.CAP;
    }
}
