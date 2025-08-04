package com.moguang.ctnhbio.machine.braininavat;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IOpticalComputationProvider;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class BrainInAVat extends BasicLivingMachine implements IOpticalComputationProvider {

    int CWUtToProduce;

    public BrainInAVat(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction, double capacity, Object... args) {
        super(holder, tier, tankScalingFunction, capacity, args);
//        this.outputFacingItems = null;
//        this.outputFacingFluids = null;
        CWUtToProduce = (tier>= GTValues.HV?1<<(tier-GTValues.HV):0);
    }

//    @Override
//    public void setOutputFacingItems(@Nullable Direction outputFacing) {}
//
//    @Override
//    public void setOutputFacingFluids(@Nullable Direction outputFacing) {}

    @Override
    public int requestCWUt(int cwut, boolean simulate, @NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        if (isActive()) {
            return Math.min(cwut, CWUtToProduce);
        }
        return 0;
    }

    @Override
    public int getMaxCWUt(@NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        return isActive() ? CWUtToProduce : 0;
    }

    @Override
    public boolean canBridge(@NotNull Collection<IOpticalComputationProvider> seen) {
        seen.add(this);
        return true;
    }
}
