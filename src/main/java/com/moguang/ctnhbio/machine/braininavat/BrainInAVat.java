package com.moguang.ctnhbio.machine.braininavat;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

public class BrainInAVat extends BasicLivingMachine {
    public BrainInAVat(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction, Object... args) {
        super(holder, tier, tankScalingFunction, args);
        this.outputFacingItems = null;
        this.outputFacingFluids = null;
    }

    @Override
    public void setOutputFacingItems(@Nullable Direction outputFacing) {}

    @Override
    public void setOutputFacingFluids(@Nullable Direction outputFacing) {}
}
