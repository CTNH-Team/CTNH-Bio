package com.moguang.ctnhbio.api;

import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;

public interface ILivingMachine {
    double getNutrientAmount();
    double getNutrientCapacity();
    void extractNutrient(double amount);
    void addNutrient(double amount);
    LivingMetaMachineEntity getMachineEntity();
}
