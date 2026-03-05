package com.moguang.ctnhbio.machine.braininavat;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationProvider;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IDropSaveMachine;
import com.gregtechceu.gtceu.utils.GTUtil;

import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import static com.gregtechceu.gtceu.api.GTValues.RNG;

public class BrainInAVatMachine extends BasicLivingMachine implements IOpticalComputationProvider, IDropSaveMachine {

    public record Quad(int CWUt, double NUt, long EUt, byte chanceToDoubt) {

        public static Quad tier(int tier) {
            int CWUt = (tier >= GTValues.HV ? 1 << (tier - GTValues.HV) : 0);
            double NUt = CWUt / 20.0;
            long EUt = GTValues.VA[tier];
            byte chanceToDoubt = (byte) (tier >= GTValues.IV ? (tier - GTValues.IV + 1) : 0);
            return new Quad(CWUt, NUt, EUt, chanceToDoubt);
        }
    }

    final Quad q;
    @Persisted
    @DescSynced
    protected long lastWorkingTime = -1;
    boolean isDoubted = false;

    @Persisted
    public float maxHealth = 0;

    public boolean oc = false;

    private boolean isOverclockedNow() {
        return GTUtil.getTierByVoltage(getOverclockVoltage()) > getTier();
    }

    @Override
    public void saveToItem(CompoundTag tag) {
        if (holder instanceof LivingMetaMachineBlockEntity<?> blockEntity && blockEntity.getMachineEntity() != null) {
            maxHealth = blockEntity.getMachineEntity().getMaxHealth();
        }
        if (maxHealth != 0) {
            tag.putFloat("maxHealth", maxHealth);
        }
        IDropSaveMachine.super.saveToItem(tag);
    }

    @Override
    public void loadFromItem(CompoundTag tag) {
        IDropSaveMachine.super.loadFromItem(tag);
        if (tag.contains("maxHealth")) {
            maxHealth = tag.getFloat("maxHealth");
        }
        if (maxHealth != 0 &&
                holder instanceof LivingMetaMachineBlockEntity<?> blockEntity &&
                blockEntity.getMachineEntity() != null) {
            blockEntity.getMachineEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
        }
    }

    public BrainInAVatMachine(IMachineBlockEntity holder, int tier, Object... args) {
        super(holder, tier, args);
        q = Quad.tier(tier);
    }

    protected Object workingSound;

    @OnlyIn(Dist.CLIENT)
    public void updateSound() {
        // if (isActive() && shouldWorkingPlaySound()) {
        // var sound = getRecipeType().getSound();
        // if (workingSound instanceof AutoReleasedSound soundEntry) {
        // if (soundEntry.soundEntry == sound && !soundEntry.isStopped()) {
        // return;
        // }
        // soundEntry.release();
        // workingSound = null;
        // }
        // if (sound != null) {
        // workingSound = sound.playAutoReleasedSound(
        // () -> shouldWorkingPlaySound() && isActive() && !isInValid() &&
        // getLevel().isLoaded(getPos()) &&
        // MetaMachine.getMachine(getLevel(), getPos()) == this,
        // getPos(), true, 0, 1, 1);
        // }
        // } else if (workingSound instanceof AutoReleasedSound soundEntry) {
        // soundEntry.release();
        // workingSound = null;
        // }
    }

    @Override
    public void clientTick() {
        super.clientTick();
        updateSound();
    }

    @Override
    public int requestCWUt(int cwut, boolean simulate, @NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        boolean overclocked = isOverclockedNow();
        var ret = isWorkingEnabled() && consume(simulate, overclocked);
        if (!ret) return 0;

        if (!simulate) {
            if (getLevel() != null) {
                lastWorkingTime = getLevel().getGameTime();
                onChanged();
            }
            long nowTick = getLevel() != null ? getLevel().getGameTime() : getOffsetTimer();
            if (nowTick % 20 == 0 && !isDoubted && q.chanceToDoubt > 0 &&
                    RNG.nextInt(Byte.MAX_VALUE) <= q.chanceToDoubt)
                isDoubted = true;

            if (overclocked) {
                applyOvervoltageDamageOncePerTick(getOffsetTimer());
            }
        }
        int output;
        if (overclocked) {
            output = 2 * q.CWUt;
        } else {
            output = q.CWUt / (isDoubted ? 2 : 1);
        }
        return output;
    }

    @Override
    public int getMaxCWUt(@NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        boolean overclocked = isOverclockedNow();
        int output = overclocked ? 2 * q.CWUt : (isDoubted ? q.CWUt / 2 : q.CWUt);
        return isWorkingEnabled() ? output : 0;
    }

    @Override
    public boolean canBridge(@NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        return true;
    }

    @Override
    public boolean isActive() {
        return getLevel() != null && lastWorkingTime >= getLevel().getGameTime();
    }

    @Override
    protected boolean allowNetworkVoltageHistory() {
        return true;
    }

    @Override
    protected boolean shouldApplyOvervoltageDamage() {
        return false;
    }

    @Override
    public void setWorkingEnabled(boolean isWorkingAllowed) {
        super.setWorkingEnabled(isWorkingAllowed);
        isDoubted = false;
    }

    // Utils
    private boolean consume(boolean simulate, boolean overclocked) {
        var nut = overclocked ? 4 * q.NUt : q.NUt;
        var eut = overclocked ? 4 * q.EUt : q.EUt;

        return simulate ? getStorage().getAmount() >= nut && energyContainer.getEnergyStored() >= eut :
                getStorage().extract(nut) >= nut && energyContainer.removeEnergy(eut) >= eut;
    }
}
