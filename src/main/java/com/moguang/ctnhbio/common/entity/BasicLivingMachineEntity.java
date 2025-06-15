package com.moguang.ctnhbio.common.entity;

import com.moguang.ctnhbio.common.machine.BasicLivingMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class BasicLivingMachineEntity extends LivingEntity {
    private BasicLivingMachineBlock machineBlock;

    public BasicLivingMachineEntity(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
        //this.anchor = BlockPos.ZERO;
        this.noPhysics = true;
        //this.setBoundingBox(new AABB(this.blockPosition()));

    }

    public void setAnchor(BasicLivingMachineBlock block) {
        this.machineBlock = block;
        var anchor = block.getPos();
        this.setPos(anchor.getX() + 0.5, anchor.getY(), anchor.getZ() + 0.5);
    }

//    @Override
//    public InteractionResult interact(Player player, InteractionHand hand) {
//        // 你自己优先响应的逻辑，比如显示 HUD
//        if (!this.level().isClientSide) {
//            if (player.isCrouching()) {
//                player.sendSystemMessage(Component.literal("我是一台活体机器。"));
//                return InteractionResult.SUCCESS;
//            }
//        }
//
//        // 否则尝试将交互“伪造”为对方块的右键事件
//        return machineBlock.getBlockState().use(this.level(), player, hand, new BlockPos(this.blockPosition()));
//    }


    @Override
    public void tick() {
        super.tick();
//        if (!level().isClientSide && machineBlock == null
//        ) {
//            this.discard();
//        }
    }

    @Override
    public void die(DamageSource source)
    {
        super.die(source);
        if (machineBlock != null) {
            level().destroyBlock(machineBlock.getPos(), true);
        }
    }


//    @Override
//    public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
//        return EntityDimensions.fixed(1.0F, 1.0F);
//    }


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
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

//    @Override
//    public void checkInsideBlocks() {
//    }

//    @Override
//    public boolean isInWall() {
//        return false;
//    }
//
//    @Override
    public boolean isPickable() {
        return true;
    }
}
