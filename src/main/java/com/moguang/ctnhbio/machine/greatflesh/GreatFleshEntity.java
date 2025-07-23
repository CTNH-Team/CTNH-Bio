package com.moguang.ctnhbio.machine.greatflesh;

import com.github.elenterius.biomancy.entity.mob.PrimordialFleshkin;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GreatFleshEntity extends LivingMetaMachineEntity implements GeoAnimatable {
    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GreatFleshEntity(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    @Override
    public boolean isCustomNameVisible() {return false;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object o) {
        return 0;
    }
}
