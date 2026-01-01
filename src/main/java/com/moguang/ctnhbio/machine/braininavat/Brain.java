package com.moguang.ctnhbio.machine.braininavat;

import com.gregtechceu.gtceu.common.data.GTDamageTypes;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Brain extends LivingMetaMachineEntity implements GeoAnimatable {
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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object o) {
        return 0;
    }
}
