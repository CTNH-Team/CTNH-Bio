package com.moguang.ctnhbio.machine.bioreactor;

import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class BioReactorEntity  extends LivingMetaMachineEntity {
    public BioReactorEntity(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }
}
