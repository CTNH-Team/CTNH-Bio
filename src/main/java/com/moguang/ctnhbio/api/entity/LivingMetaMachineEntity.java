package com.moguang.ctnhbio.api.entity;

import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;

import net.createmod.catnip.math.VecHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import com.moguang.ctnhbio.api.IHostAwareEntity;
import com.moguang.ctnhbio.api.ILivingMachine;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

public class LivingMetaMachineEntity extends LivingEntity implements IHostAwareEntity {

    private static final String HOST_POS_TAG = "HostPos";
    private static final int HOST_MISSING_GRACE_TICKS = 100;

    LivingMetaMachineBlockEntity holder;
    @Nullable
    private BlockPos hostPos;
    public boolean ifInit = false;
    private int missingHostTicks;

    public LivingMetaMachineEntity(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    public static LivingMetaMachineEntity create(EntityType<? extends LivingEntity> type, Level level) {
        return new LivingMetaMachineEntity(type, level);
    }

    @Override
    @Nullable
    public LivingMetaMachineBlockEntity getHost() {
        return holder;
    }

    @Nullable
    public BasicLivingMachine getMachine() {
        if (holder != null && holder.getMetaMachine() instanceof BasicLivingMachine basicLivingMachine) {
            return basicLivingMachine;
        }
        return null;
    }

    @Override
    public void setHost(@Nullable LivingMetaMachineBlockEntity host) {
        holder = host;
        if (host != null) {
            hostPos = host.getHostPos();
            missingHostTicks = 0;
        }
    }

    @Nullable
    public BlockPos getStoredHostPos() {
        return hostPos;
    }

    public boolean hasStoredHostPos(BlockPos pos) {
        return hostPos != null && hostPos.equals(pos);
    }

    public void setPos(BlockPos pos, Vec3 offset) {
        super.setPos(pos.getX() + offset.x, pos.getY() + offset.y, pos.getZ() + offset.z);
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        if (holder != null && holder.getMetaMachine() instanceof BasicLivingMachine machine) {
            assert name != null;
            machine.setName(name.getString());
        }
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        if (holder != null) {
            holder.onHostedEntityRemoved(this, source); // 通知宿主
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        if (result && holder != null && !level().isClientSide) {
            holder.onHostedEntityHurt();
            if (getMachine() != null && source.is(DamageTypes.PLAYER_ATTACK)) {
                var logic = getMachine().getRecipeLogic();
                if (logic.getLastRecipe() != null) {
                    var r = getRandom().nextInt(100);
                    if (r < 3) {
                        logic.setProgress(logic.getProgress() + 1000);
                    } else if (r < 30) {
                        logic.setProgress(logic.getProgress() + 20);
                    } else if (r > 95) {
                        logic.reset();
                    } else if (r > 70) {
                        logic.setProgress(Math.max(logic.getProgress() - 20, 0));
                    }
                }
            }
        }
        return result;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.ARMOR, 0.0D);
    }

    public void initAttributes(double maxHealth, double armor) {
        if (!ifInit) {
            ifInit = true;
            getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
            setHealth((float) maxHealth);
            getAttribute(Attributes.ARMOR).setBaseValue(armor);
        }
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected boolean updateInWaterStateAndDoFluidPushing() {
        return false;
    }

    @Override
    public void updateSwimming() {}

    @Override
    public boolean isCustomNameVisible() {
        return true;
    }

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
    public void setItemSlot(EquipmentSlot equipmentSlot, ItemStack itemStack) {}

    @Override
    public void aiStep() {}

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
    public void checkInsideBlocks() {}

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (hostPos != null) {
            tag.putLong(HOST_POS_TAG, hostPos.asLong());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains(HOST_POS_TAG)) {
            hostPos = BlockPos.of(tag.getLong(HOST_POS_TAG));
        } else {
            hostPos = null;
        }
        holder = null;
        missingHostTicks = 0;
    }

    private void refreshHostBinding() {
        if (holder != null && (holder.isRemoved() || !holder.getHostPos().equals(hostPos))) {
            holder = null;
        }
        if (holder == null && hostPos != null && tickCount % 20 == 0) {
            if (level().getBlockEntity(hostPos) instanceof LivingMetaMachineBlockEntity blockEntity) {
                blockEntity.bindHostedEntity(this);
            }
        }
        if (holder == null && hostPos != null) {
            missingHostTicks++;
            if (missingHostTicks >= HOST_MISSING_GRACE_TICKS) {
                discard();
            }
        } else {
            missingHostTicks = 0;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            refreshHostBinding();
        }
        if (tickCount % 40 == 0)
            if (getHealth() < getMaxHealth()) {
                if (getHost() != null && getHost().getHostMachine() instanceof ILivingMachine livingMachine) {
                    boolean structureOperational = !(livingMachine instanceof IMultiController controller) ||
                            controller.isStructureOperational();
                    if (structureOperational && livingMachine.getNutrientAmount() >= 2) {
                        livingMachine.extractNutrient(2);
                        heal(1);
                        var pos = getHost().getHostPos().getCenter();
                        Vec3 v = pos.add(0, 1f, 0)
                                .add(VecHelper.offsetRandomly(Vec3.ZERO, level().random, 1)
                                        .multiply(1, 0.2f, 1)
                                        .normalize()
                                        .scale(1f));
                        level().addParticle(ParticleTypes.HEART, v.x, v.y, v.z, 0, 0.1f, 0);
                    }
                }
            }
    }

    @Override
    public boolean isInWall() {
        return false;
    }
}
