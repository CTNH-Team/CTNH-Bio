package com.moguang.ctnhbio.machine.braininavat;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.computation.ComputationProducer;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IDropSaveMachine;
import com.gregtechceu.gtceu.api.machine.trait.DirectComputationPortTrait;

import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;

import static com.gregtechceu.gtceu.api.GTValues.RNG;

public class BrainInAVatMachine extends BasicLivingMachine implements ComputationProducer, IDropSaveMachine {

    public record Quad(int CWUt,
                       float NUt,
                       long EUt,
                       byte chanceToDoubt // 自我怀疑的概率,0~128
    ) {

        public static Quad tier(int tier) {
            int CWUt = (tier >= GTValues.HV ? 1 << (tier - GTValues.HV) : 0);
            float NUt = CWUt / 20.0f;
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
    private float maxHealth = 0;

    public boolean oc = false;

    public BrainInAVatMachine(IMachineBlockEntity holder, int tier, Object... args) {
        super(holder, tier, args);
        q = Quad.tier(tier);
        attachTrait(new DirectComputationPortTrait(this, true, this, null));
    }

    public float getStoredMaxHealth() {
        return maxHealth;
    }

    public void captureMaxHealthFromEntity() {
        if (holder instanceof LivingMetaMachineBlockEntity blockEntity && blockEntity.getHostedEntity() != null) {
            maxHealth = blockEntity.getHostedEntity().getMaxHealth();
        }
    }

    @Override
    public void saveToItem(CompoundTag tag) {
        captureMaxHealthFromEntity();
        if (maxHealth != 0) {
            tag.putFloat("maxHealth", maxHealth);
        }
        IDropSaveMachine.super.saveToItem(tag);
    }

    @Override
    public void loadFromItem(CompoundTag tag) {
        IDropSaveMachine.super.loadFromItem(tag);
        maxHealth = tag.contains("maxHealth") ? tag.getFloat("maxHealth") : 0;
    }

    @Override
    public void doExplosion(float explosionPower) {
        super.doExplosion(explosionPower);
        oc = true;
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
        // updateSound();
    }

    @Override
    public int getOfferedCWUt() {
        int offered = getCurrentCWUt();
        return isWorkingEnabled() && canConsume(offered) ? offered : 0;
    }

    @Override
    public void applyProducedCWUt(int allocatedCWUt) {
        int produced = Math.min(Math.max(allocatedCWUt, 0), getOfferedCWUt());
        if (produced == 0 || !consume(produced)) return;

        if (getLevel() != null) {
            lastWorkingTime = getLevel().getGameTime();
            onChanged();
        }
        long nowTick = getLevel() != null ? getLevel().getGameTime() : getOffsetTimer();
        if (nowTick % 20 == 0 && !isDoubted && q.chanceToDoubt > 0 &&
                RNG.nextInt(Byte.MAX_VALUE) <= q.chanceToDoubt) {
            isDoubted = true;
        }
        oc = false;
    }

    @Override
    public boolean isActive() {
        return getLevel() != null && lastWorkingTime >= getLevel().getGameTime();
    }

    @Override
    public void setWorkingEnabled(boolean isWorkingAllowed) {
        super.setWorkingEnabled(isWorkingAllowed);
        isDoubted = false;
    }

    // Utils
    private int getCurrentCWUt() {
        return oc ? 2 * q.CWUt : q.CWUt / (isDoubted ? 2 : 1);
    }

    private boolean canConsume(int producedCWUt) {
        if (producedCWUt == 0) return false;
        float ratio = (float) producedCWUt / getCurrentCWUt();
        return getNutrientHandler().getAmount() >= q.NUt * ratio &&
                energyContainer.getEnergyStored() >= Math.ceil(q.EUt * ratio);
    }

    private boolean consume(int producedCWUt) {
        if (!canConsume(producedCWUt)) return false;
        float ratio = (float) producedCWUt / getCurrentCWUt();
        float nutrient = q.NUt * ratio;
        long energy = (long) Math.ceil(q.EUt * ratio);
        return getNutrientHandler().extract(nutrient) >= nutrient && energyContainer.removeEnergy(energy) >= energy;
    }
}
