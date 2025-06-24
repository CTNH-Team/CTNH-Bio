package com.moguang.ctnhbio.api.entity;

import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.common.data.GTDamageTypes;
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

public class LivingMetaMachineEntity extends LivingEntity {
    private BlockPos anchor;
    private BasicLivingMachine livingMachine;
    @Setter
    private boolean initialized = false;
    private int ageWithoutInit = 0;

    protected LivingMetaMachineEntity(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
        //this.anchor = BlockPos.ZERO;
        //this.noPhysics = true;

        //System.out.println("LivingMetaMachineEntity: " + this.getUUID() + level.isClientSide);
        //machine.createUIWidget()
        //this.setBoundingBox(new AABB(this.blockPosition()));
    }



    public static LivingMetaMachineEntity createLivingMetaMachineEntity(EntityType<? extends LivingEntity> type, Level level) {
        return new LivingMetaMachineEntity(type, level);
    }

    public void bound(BlockPos pos, BasicLivingMachine machine) {
        anchor = pos;
        livingMachine = machine;
        getPersistentData().putLong("anchor", anchor.asLong());
        getPersistentData().putBoolean("initialized", true);

        setPos(anchor.getX() + 0.5, anchor.getY(), anchor.getZ() + 0.5);
        getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D * machine.getTier());
        setHealth(getMaxHealth());
        initialized = true;
        System.out.println("bound: " + initialized);
    }

    public BlockPos getAnchor() {
        if(anchor == null)
        {
            anchor = BlockPos.of(getPersistentData().getLong("anchor"));
        }
        return anchor;
    }

    public BasicLivingMachine getMachine() {

        if(livingMachine == null &&
                getAnchor() != null &&
                this.level().getBlockEntity(this.anchor) instanceof MetaMachineBlockEntity blockEntity &&
                blockEntity.getMetaMachine() instanceof BasicLivingMachine machine)
        {
            livingMachine = machine;
            livingMachine.holder.notifyBlockUpdate();
        }
        return livingMachine;
    }

    @Override
    public AABB getBoundingBoxForCulling() {
        return this.getBoundingBox().inflate(5.0);
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        if(level().getBlockState(blockPosition()).getBlock() instanceof LivingMetaMachineBlock block)
        {
            //block.setName(name);
        }
    }

    @Override
    public void tick() {
        super.tick();

        //System.out.println("tick " + getUUID() + " "+ level().isClientSide);
        if(level().isClientSide) return;
        initialized = getPersistentData().getBoolean("initialized");
        //System.out.println("tick: " + initialized);
        if(initialized)
        {
            ageWithoutInit = 0;
            initialized = true;
            if (!level().isClientSide) {
                //getAnchor();
                getMachine();
            }
//            double x = Math.floor(anchor.getX()) + 0.5;
//            double y = Math.floor(anchor.getY());
//            double z = Math.floor(anchor.getZ()) + 0.5;
//
//            this.setPos(x, y, z);
            this.setDeltaMovement(Vec3.ZERO);
        }
        else {
            ageWithoutInit++;
            if (ageWithoutInit > 40) {  // 2秒后自我删除
                this.discard();
            }
        }

    }

    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        if(!level().isClientSide && getMachine() != null)
        {
            level().removeBlock(livingMachine.getPos(), false);
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
