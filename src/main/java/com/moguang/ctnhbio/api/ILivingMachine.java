package com.moguang.ctnhbio.api;

import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;

public interface ILivingMachine {
    double getNutrientAmount();
    double getNutrientCapacity();
    LivingMetaMachineEntity getMachineEntity();
}
