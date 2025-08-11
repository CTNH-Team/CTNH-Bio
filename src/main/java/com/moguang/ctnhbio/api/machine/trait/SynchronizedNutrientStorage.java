package com.moguang.ctnhbio.api.machine.trait;

import com.lowdragmc.lowdraglib.syncdata.IManaged;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.FieldManagedStorage;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import lombok.Getter;

public class SynchronizedNutrientStorage implements IManaged {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(SynchronizedNutrientStorage.class);
    @Getter
    private final FieldManagedStorage syncStorage = new FieldManagedStorage(this);
    @Persisted
    private double amount;
    @Getter
    @Persisted
    private double capacity;

    public SynchronizedNutrientStorage(double capacity) {
        this.amount = 0;
        this.capacity = capacity;
    }

    public double getAmount() {
        return amount;
    }
    public double getLeft() {
        return capacity - amount;
    }

    public double add(double toAdd) {
        toAdd = Math.min(toAdd, capacity - amount);
        amount += toAdd;
        return toAdd;
    }

    public double extract(double toExtract) {
        toExtract = Math.min(toExtract, amount);
        amount -= toExtract;
        return toExtract;
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
    @Override
    public void onChanged() {

    }
}
