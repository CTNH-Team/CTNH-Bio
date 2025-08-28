package com.moguang.ctnhbio.machine.braininavat;

import com.gregtechceu.gtceu.common.data.GTDamageTypes;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class Brain extends LivingMetaMachineEntity {
    public Brain(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {

        boolean result = super.hurt(source, amount);
        if(result && source.is(GTDamageTypes.ELECTRIC.key) && !level().isClientSide)
        {
            if (getHealth()>1) {
                getAttribute(Attributes.MAX_HEALTH).setBaseValue(getHealth());
            }

        }


        return result;
    }
}
