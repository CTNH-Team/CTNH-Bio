package com.moguang.ctnhbio.common.machine;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.moguang.ctnhbio.common.entity.BasicLivingMachineEntity;
import com.moguang.ctnhbio.registry.CBEntities;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BasicLivingMachineBlock extends SimpleTieredMachine {

    public BasicLivingMachineEntity machineEntity;

    public BasicLivingMachineBlock(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction, Object... args) {
        super(holder, tier, tankScalingFunction, args);


    }

    @Override
    public void onMachinePlaced(@Nullable LivingEntity player, ItemStack stack) {
        super.onMachinePlaced(player, stack);
        this.machineEntity = new BasicLivingMachineEntity(CBEntities.BASIC_LIVING_MACHINE_ENTITY.get(), this.getLevel());
        machineEntity.setAnchor(this);
        Objects.requireNonNull(this.getLevel()).addFreshEntity(machineEntity);
        //System.out.println(this.holder.getDefinition().getId());
    }



    @Override
    public void onMachineRemoved() {
        super.onMachineRemoved();
        if (machineEntity != null) {
            machineEntity.discard();
            machineEntity = null;
        }
    }

}
