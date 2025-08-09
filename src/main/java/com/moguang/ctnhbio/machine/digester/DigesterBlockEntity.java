package com.moguang.ctnhbio.machine.digester;

import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class DigesterBlockEntity extends LivingMetaMachineBlockEntity implements GeoBlockEntity {

    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public DigesterBlockEntity(BlockEntityType type, BlockPos pos, BlockState blockState, EntityType entityType) {
        super(type, pos, blockState, entityType);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
