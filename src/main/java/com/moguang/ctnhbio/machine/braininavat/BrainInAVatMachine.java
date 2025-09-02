package com.moguang.ctnhbio.machine.braininavat;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationProvider;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IDropSaveMachine;
import com.gregtechceu.gtceu.api.sound.AutoReleasedSound;
import com.lowdragmc.lowdraglib.syncdata.IFieldUpdateListener;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.annotation.RequireRerender;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import dev.toma.configuration.config.Configurable;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

import static com.gregtechceu.gtceu.api.GTValues.RNG;

public class BrainInAVatMachine extends BasicLivingMachine implements IOpticalComputationProvider, IDropSaveMachine {

    public record Quad(int CWUt,
                       double NUt,
                       long EUt,
                       byte chanceToDoubt //自我怀疑的概率,0~128
    ){
        public static Quad tier(int tier){
            int CWUt = (tier>= GTValues.HV?1<<(tier-GTValues.HV):0);
            double NUt = CWUt / 20.0;
            long EUt = GTValues.VA[tier];
            byte chanceToDoubt = (byte) (tier>=GTValues.IV?(tier-GTValues.IV+1):0);
            return new Quad(CWUt,NUt,EUt,chanceToDoubt);
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

    @Override
    public void saveToItem(CompoundTag tag) {
        if(holder instanceof LivingMetaMachineBlockEntity<?> blockEntity
        && blockEntity.getMachineEntity() != null)
        {
            maxHealth = blockEntity.getMachineEntity().getMaxHealth();
        }
        if(maxHealth != 0)
        {
            tag.putFloat("maxHealth", maxHealth);
        }
        IDropSaveMachine.super.saveToItem(tag);
    }

    @Override
    public void loadFromItem(CompoundTag tag) {
        IDropSaveMachine.super.loadFromItem(tag);
        if(tag.contains("maxHealth"))
        {
            maxHealth = tag.getFloat("maxHealth");
        }
        if( maxHealth != 0 &&
                holder instanceof LivingMetaMachineBlockEntity<?> blockEntity
                && blockEntity.getMachineEntity() != null)
        {
            blockEntity.getMachineEntity().getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
        }

    }

    @Override
    public void doExplosion(float explosionPower) {
        super.doExplosion(explosionPower);
        oc = true;
    }

    public BrainInAVatMachine(IMachineBlockEntity holder, int tier, Object... args) {
        super(holder, tier, args);
        q = Quad.tier(tier);
    }

    protected Object workingSound;

    @OnlyIn(Dist.CLIENT)
    public void updateSound() {
//        if (isActive() && shouldWorkingPlaySound()) {
//            var sound = getRecipeType().getSound();
//            if (workingSound instanceof AutoReleasedSound soundEntry) {
//                if (soundEntry.soundEntry == sound && !soundEntry.isStopped()) {
//                    return;
//                }
//                soundEntry.release();
//                workingSound = null;
//            }
//            if (sound != null) {
//                workingSound = sound.playAutoReleasedSound(
//                        () -> shouldWorkingPlaySound() && isActive() && !isInValid() &&
//                                getLevel().isLoaded(getPos()) &&
//                                MetaMachine.getMachine(getLevel(), getPos()) == this,
//                        getPos(), true, 0, 1, 1);
//            }
//        } else if (workingSound instanceof AutoReleasedSound soundEntry) {
//            soundEntry.release();
//            workingSound = null;
//        }
    }

    @Override
    public void clientTick() {
        super.clientTick();
        updateSound();
    }



    @Override
    public int requestCWUt(int cwut, boolean simulate, @NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        var ret = isWorkingEnabled() && consume(simulate);
        if(!ret ) return 0;

        if(!simulate){
            if(getLevel()!=null) {
                lastWorkingTime = getLevel().getGameTime();
                onChanged();
            }
            if(!isDoubted && q.chanceToDoubt > 0 && RNG.nextInt(Byte.MAX_VALUE) <= q.chanceToDoubt) isDoubted = true;
        }
        int output;
        if(oc)
        {
            output = 2*q.CWUt;
        }
        else {
            output = q.CWUt  / (isDoubted? 2 : 1);
        }
        if(!simulate) oc = false;
        return  output;
    }

    @Override
    public int getMaxCWUt(@NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        int output = oc ? 2*q.CWUt : q.CWUt;
        return isWorkingEnabled() ? output : 0;
    }

    @Override
    public boolean canBridge(@NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        return true;
    }

    @Override
    public boolean isActive() {
        return getLevel()!=null && lastWorkingTime >= getLevel().getGameTime();
    }

    @Override
    public void setWorkingEnabled(boolean isWorkingAllowed) {
        super.setWorkingEnabled(isWorkingAllowed);
        isDoubted = false;
    }

    //Utils
    private boolean consume(boolean simulate){
        var nut = oc ? 4*q.NUt : q.NUt;
        var eut = oc ? 4*q.EUt : q.EUt;

        return simulate ? getStorage().getAmount() >= nut && energyContainer.getEnergyStored() >= eut :
                getStorage().extract(nut) >= nut && energyContainer.removeEnergy(eut) >= eut;
    }


}
