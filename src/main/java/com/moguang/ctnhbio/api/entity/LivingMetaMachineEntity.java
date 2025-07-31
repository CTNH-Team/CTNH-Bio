package com.moguang.ctnhbio.api.entity;

import com.moguang.ctnhbio.api.IHostAwareEntity;
import com.moguang.ctnhbio.api.ILivingEntityHost;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

public class LivingMetaMachineEntity extends LivingEntity implements IHostAwareEntity {

    ILivingEntityHost<LivingMetaMachineEntity> holder;
    public boolean ifInit = false;

    public LivingMetaMachineEntity(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);

    }

    public static LivingMetaMachineEntity create(EntityType<? extends LivingEntity> type, Level level) {
        return new LivingMetaMachineEntity(type, level);
    }

    @Override
    public ILivingEntityHost<?> getHost() {
        return holder;
    }

    @Override
    public void setHost(ILivingEntityHost<?> host) {
        holder = (ILivingEntityHost<LivingMetaMachineEntity>) host;
    }



    public void setPos(BlockPos pos, Vec3 offset) {
        super.setPos(pos.getX()+offset.x, pos.getY()+offset.y, pos.getZ()+offset.z);
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        if (holder instanceof LivingMetaMachineBlockEntity blockEntity &&
                blockEntity.getMetaMachine() instanceof BasicLivingMachine machine)
        {
            assert name != null;
            machine.setName(name.getString());
        }
    }

    @Override
    public void die(DamageSource p_21014_) {
        super.die(p_21014_);
        if (holder != null) {
            holder.onHostedEntityRemoved(this); // 通知宿主
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.ARMOR, 0.0D);
    }

    public void initAttributes(double maxHealth, double armor){
        if(!ifInit)
        {
            ifInit = true;
            getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
            setHealth((float) maxHealth);
            getAttribute(Attributes.ARMOR).setBaseValue(armor);
        }

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
    public boolean isCustomNameVisible() {return true;}

    @Override
    public @NotNull HumanoidArm getMainArm() {
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


        //this.setDeltaMovement(0, 0, 0);

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
        return false;
    }

    @Override
    public void checkInsideBlocks() {
    }

    @Override
    public boolean isInWall() {
        return false;
    }
}
