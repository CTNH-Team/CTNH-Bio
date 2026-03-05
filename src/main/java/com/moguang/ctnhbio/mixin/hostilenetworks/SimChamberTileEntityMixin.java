package com.moguang.ctnhbio.mixin.hostilenetworks;

import com.gregtechceu.gtceu.api.GTValues;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import dev.shadowsoffire.hostilenetworks.data.CachedModel;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import dev.shadowsoffire.hostilenetworks.tile.SimChamberTileEntity;
import dev.shadowsoffire.placebo.cap.ModifiableEnergyStorage;
import dev.shadowsoffire.placebo.menu.SimpleDataSlots;
import org.spongepowered.asm.mixin.*;

@Mixin(value = SimChamberTileEntity.class, remap = false)
abstract class SimChamberTileEntityMixin extends BlockEntity {

    @Final
    @Shadow
    protected SimChamberTileEntity.SimItemHandler inventory;
    @Final
    @Shadow
    protected ModifiableEnergyStorage energy;
    @Final
    @Shadow
    protected SimpleDataSlots data;
    @Shadow
    protected CachedModel currentModel;
    @Shadow
    protected int runtime;
    @Shadow
    protected boolean predictionSuccess;
    @Shadow
    protected SimChamberTileEntity.FailureState failState;

    public SimChamberTileEntityMixin(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Shadow
    protected abstract CachedModel getOrLoadModel(ItemStack stack);

    @Unique
    SimChamberTileEntity self = (SimChamberTileEntity) (Object) this;

    @Unique
    private static int SIM_COST = 4 * (int) GTValues.V[GTValues.IV];

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        ItemStack model = inventory.getStackInSlot(0);
        if (!model.isEmpty()) {
            CachedModel oldModel = currentModel;
            currentModel = getOrLoadModel(model);
            if (oldModel != currentModel) {
                runtime = 0;
            }

            if (currentModel.isValid()) {
                if (currentModel.getTier() != ModelTier.FAULTY && currentModel.getTier() != ModelTier.BASIC) {
                    failState = SimChamberTileEntity.FailureState.FAULTY;
                    runtime = 0;
                    return;
                }

                if (runtime == 0) {
                    if (self.canStartSimulation()) {
                        runtime = 300;
                        predictionSuccess = level.random.nextFloat() <= currentModel.getAccuracy();
                        inventory.getStackInSlot(1).shrink(1);
                        setChanged();
                    }
                } else if (energy.getEnergyStored() > SIM_COST) {
                    failState = SimChamberTileEntity.FailureState.NONE;
                    if (--runtime == 0) {

                        ModelTier tier = currentModel.getTier();
                        if (tier != tier.next()) {
                            int newData = currentModel.getData() + 1;
                            currentModel.setData(newData);
                        }

                        DataModelItem.setIters(model, DataModelItem.getIters(model) + 1);
                        setChanged();
                    } else {
                        energy.setEnergy(energy.getEnergyStored() - SIM_COST);
                        setChanged();
                    }
                } else {
                    failState = SimChamberTileEntity.FailureState.ENERGY_MID_CYCLE;
                }

                return;
            }
        }

        failState = SimChamberTileEntity.FailureState.MODEL;
        runtime = 0;
    }
}
