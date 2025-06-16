package com.moguang.ctnhbio.common.entity;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.moguang.ctnhbio.common.machine.BasicLivingMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Collections;

public class BasicLivingMachineEntity extends LivingEntity {
    public BlockPos anchor;
    public BasicLivingMachine machine;

    public BasicLivingMachineEntity(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
        //this.anchor = BlockPos.ZERO;
        this.noPhysics = true;
        this.setCustomNameVisible(true);

        //machine.createUIWidget()
        //this.setBoundingBox(new AABB(this.blockPosition()));

    }

    public BasicLivingMachine getMachine() {

        if(this.anchor != null &&
                this.level().getBlockEntity(this.anchor) instanceof MetaMachineBlockEntity blockEntity &&
                blockEntity.getMetaMachine() instanceof BasicLivingMachine machine)
        {
            machine.machineEntity = this;
            return machine;
        }
        return null;
    }

    @Override
    public AABB getBoundingBoxForCulling() {
        return this.getBoundingBox().inflate(5.0);
    }

    public void setAnchor(BasicLivingMachine livingMachine) {

        this.anchor = livingMachine.getPos();
        this.machine = livingMachine;
        this.getPersistentData().putLong("anchor", anchor.asLong());

        this.setPos(anchor.getX() + 0.5, anchor.getY(), anchor.getZ() + 0.5);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D * machine.getTier());
        this.setHealth(this.getMaxHealth());
    }

    @Override
    public void tick() {
        super.tick();
        if (anchor == null) {
            anchor = BlockPos.of(getPersistentData().getLong("anchor"));
        }
        if (!level().isClientSide) {
            if (machine == null) {
                machine = this.getMachine();
            }
        }
    }

    @Override
    public void die(DamageSource source)
    {
        super.die(source);
        if (this.getMachine() != null) {
            //level().destroyBlock(this.getMachine().getPos(), true);
            level().removeBlock(this.getMachine().getPos(), false);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D);
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
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
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
    public Iterable<ItemStack> getArmorSlots() {
        return Collections.emptyList();
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot equipmentSlot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot equipmentSlot, ItemStack itemStack) {

    }

    @Override
    public void aiStep() {
        super.aiStep();
//        this.hurtTime = 0;
//        this.hurtMarked = false;

        double x = Math.floor(this.getX()) + 0.5;
        double y = Math.floor(this.getY());
        double z = Math.floor(this.getZ()) + 0.5;

        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean shouldBeSaved() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }
}
