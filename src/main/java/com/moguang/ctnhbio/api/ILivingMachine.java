package com.moguang.ctnhbio.api;

import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;

public interface ILivingMachine {

    float getNutrientAmount();

    float getNutrientCapacity();

    void extractNutrient(float amount);

    void addNutrient(float amount);

    LivingMetaMachineEntity getMachineEntity();
}
