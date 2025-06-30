package com.moguang.ctnhbio.api.entity;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.common.data.GTDamageTypes;
import com.moguang.ctnhbio.api.IHostAwareEntity;
import com.moguang.ctnhbio.api.ILivingEntityHost;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.UUID;

public class LivingMetaMachineEntity extends LivingEntity implements IHostAwareEntity {

    ILivingEntityHost<LivingMetaMachineEntity> holder;

    protected LivingMetaMachineEntity(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);

    }

    public static LivingMetaMachineEntity createLivingMetaMachineEntity(EntityType<? extends LivingEntity> type, Level level) {
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

//    @Override
//    public void onRemovedFromWorld() {
//        if (holder != null) {
//            holder.onHostedEntityRemoved(this); // 通知宿主
//        }
//        super.onRemovedFromWorld();
//    }


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
        return true;
    }

    @Override
    public void checkInsideBlocks() {
    }

    @Override
    public boolean isInWall() {
        return false;
    }
}
