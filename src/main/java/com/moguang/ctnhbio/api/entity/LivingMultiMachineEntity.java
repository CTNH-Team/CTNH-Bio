package com.moguang.ctnhbio.api.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class LivingMultiMachineEntity extends LivingMetaMachineEntity{
    public LivingMultiMachineEntity(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }
    public static LivingMultiMachineEntity create(EntityType<? extends LivingEntity> type, Level level) {
        return new LivingMultiMachineEntity(type, level);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.ARMOR, 4.0D);
    }
}
