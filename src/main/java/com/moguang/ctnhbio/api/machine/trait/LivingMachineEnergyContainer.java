package com.moguang.ctnhbio.api.machine.trait;

import com.gregtechceu.gtceu.api.machine.trait.RecipeAmperageEnergyContainer;
import com.gregtechceu.gtceu.utils.GTUtil;

import net.minecraft.core.Direction;

import com.moguang.ctnhbio.api.machine.BasicLivingMachine;

public class LivingMachineEnergyContainer extends RecipeAmperageEnergyContainer {

    private final BasicLivingMachine livingMachine;

    public LivingMachineEnergyContainer(BasicLivingMachine machine, long maxCapacity,
                                        long maxInputVoltage, long maxInputAmperage,
                                        long maxOutputVoltage, long maxOutputAmperage) {
        super(machine, maxCapacity, maxInputVoltage, maxInputAmperage, maxOutputVoltage, maxOutputAmperage);
        this.livingMachine = machine;
    }

    public static LivingMachineEnergyContainer makeReceiverContainer(BasicLivingMachine machine, long maxCapacity,
                                                                     long maxInputVoltage, long maxInputAmperage) {
        return new LivingMachineEnergyContainer(machine, maxCapacity, maxInputVoltage, maxInputAmperage, 0L, 0L);
    }

    @Override
    public long acceptEnergyFromNetwork(Direction side, long voltage, long amperage) {
        var latestTimeStamp = getMachine().getOffsetTimer();
        if (lastTimeStamp < latestTimeStamp) {
            amps = 0;
            lastTimeStamp = latestTimeStamp;
        }

        if (amps >= getInputAmperage()) return 0;

        long canAccept = getEnergyCapacity() - getEnergyStored();
        if (voltage <= 0L || !(side == null || inputsEnergy(side))) return 0;

        int machineTier = GTUtil.getTierByVoltage(getInputVoltage());
        int incomingTier = GTUtil.getTierByVoltage(voltage);

        livingMachine.onEnergyPacketSeen(voltage, latestTimeStamp, incomingTier);

        if (incomingTier > machineTier + 1) {
            livingMachine.onFatalOvervoltage(latestTimeStamp);
            return Math.min(amperage, getInputAmperage() - amps);
        }

        if (canAccept < voltage) return 0;

        long amperesAccepted = Math.min(canAccept / voltage, Math.min(amperage, getInputAmperage() - amps));
        if (amperesAccepted <= 0) return 0;

        setEnergyStored(getEnergyStored() + voltage * amperesAccepted);
        amps += amperesAccepted;

        livingMachine.onEnergyPacketAccepted(voltage, latestTimeStamp, incomingTier);
        return amperesAccepted;
    }
}
