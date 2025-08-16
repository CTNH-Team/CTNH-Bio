package com.moguang.ctnhbio.machine.braininavat;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationProvider;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import static com.gregtechceu.gtceu.api.GTValues.RNG;

public class BrainInAVatMachine extends BasicLivingMachine implements IOpticalComputationProvider {

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
    long lastWorkingTime = -1;
    boolean isDoubted = false;

    public BrainInAVatMachine(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction, double capacity, Object... args) {
        super(holder, tier, tankScalingFunction, capacity, args);
        q = Quad.tier(tier);
    }

    @Override
    public int requestCWUt(int cwut, boolean simulate, @NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        var ret = isWorkingEnabled() && consume(simulate);
        if(ret&&!simulate){
            if(getLevel()!=null) lastWorkingTime = getLevel().getGameTime();
            if(!isDoubted && q.chanceToDoubt > 0 && RNG.nextInt(Byte.MAX_VALUE) <= q.chanceToDoubt) isDoubted = true;
        }
        return (ret? q.CWUt : 0) / (isDoubted? 2 : 1);
    }

    @Override
    public int getMaxCWUt(@NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        return isWorkingEnabled() ? q.CWUt : 0;
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
        return simulate ? getStorage().getAmount() >= q.NUt && energyContainer.getEnergyStored() >= q.EUt :
                getStorage().extract(q.NUt) >= q.NUt && energyContainer.removeEnergy(q.EUt) >= q.EUt;
    }
}
