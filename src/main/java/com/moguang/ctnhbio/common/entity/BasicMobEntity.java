package com.moguang.ctnhbio.common.entity;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class BasicMobEntity extends Mob {
    public BasicMobEntity(EntityType<? extends Mob> type, Level level) {
        super(type, level);
        //this.setBoundingBox(new AABB(this.blockPosition()));
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.fixed(3.0F, 3.0F);
    }

    @Override
    protected void registerGoals() {
        //this.goalSelector.addGoal(1, new FloatGoal(this));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }
    @Override
    public boolean isAffectedByPotions() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isAffectedByFluids() {
        return false;
    }

    @Override
    public void knockback(double strength, double xRatio, double zRatio) {
        // 禁用击退
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.hurtTime = 0;
        this.hurtMarked = false;

        double x = Math.floor(this.getX()) + 0.5;
        double y = Math.floor(this.getY());
        double z = Math.floor(this.getZ()) + 0.5;

        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public void checkInsideBlocks() {
    }

    @Override
    public boolean isInWall() {
        return false;
    }

    @Override
    public boolean isPickable() {
        return false;
    }
}
