package com.moguang.ctnhbio.api.machine.trait;

import com.lowdragmc.lowdraglib.syncdata.IManaged;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.FieldManagedStorage;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.gregtechceu.gtceu.utils.ManagedFieldHolderMap;

import lombok.Getter;

public class SynchronizedNutrientStorage implements IManaged {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = ManagedFieldHolderMap.createManagedFieldHolder(
            SynchronizedNutrientStorage.class);
    @Getter
    private final FieldManagedStorage syncStorage = new FieldManagedStorage(this);
    @Persisted
    private double amount;
    @Getter
    @Persisted
    private double capacity;

    protected final Runnable listener;

    public SynchronizedNutrientStorage(double capacity, Runnable listener) {
        this.amount = 0;
        this.capacity = capacity;
        this.listener = listener;
    }

    public double getAmount() {
        return amount;
    }

    public double getLeft() {
        return capacity - amount;
    }

    public double add(double toAdd) {
        toAdd = Math.min(toAdd, capacity - amount);
        if (toAdd > 0) {
            onChanged();
        }
        amount += toAdd;
        return toAdd;
    }

    public double extract(double toExtract) {
        toExtract = Math.min(toExtract, amount);
        amount -= toExtract;
        if (toExtract > 0) {
            onChanged();
        }
        return toExtract;
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return ManagedFieldHolderMap.getManagedFieldHolder(getClass());
    }

    @Override
    public void onChanged() {
        listener.run();
    }
}
