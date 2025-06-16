package com.moguang.ctnhbio.common.machine;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.gregtechceu.gtceu.common.data.GTDamageTypes;
import com.gregtechceu.gtceu.common.machine.electric.BatteryBufferMachine;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.moguang.ctnhbio.common.entity.BasicLivingMachineEntity;
import com.moguang.ctnhbio.registry.CBEntities;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import java.lang.reflect.Field;
import java.util.Objects;

public class BasicLivingMachine extends SimpleTieredMachine {

    public BasicLivingMachineEntity machineEntity;

    public BasicLivingMachine(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction, Object... args) {
        super(holder, tier, tankScalingFunction, args);



    }

    @Override
    public boolean shouldWeatherOrTerrainExplosion() {
        return false;
    }

    @Override
    public void doExplosion(float explosionPower) {
        float inputTier = explosionPower -1;
        if(inputTier - tier >= 2) {
            this.machineEntity.die(GTDamageTypes.ELECTRIC.source(this.getLevel()));
        }
        else {
            this.energyContainer.changeEnergy(GTValues.V[tier]);
            this.machineEntity.hurt(GTDamageTypes.ELECTRIC.source(this.getLevel()), tier);
        }


    }

    @Override
    public void onMachinePlaced(@Nullable LivingEntity player, ItemStack stack) {
        super.onMachinePlaced(player, stack);
        this.machineEntity = new BasicLivingMachineEntity(CBEntities.BASIC_LIVING_MACHINE_ENTITY.get(), this.getLevel());
        machineEntity.setAnchor(this);
        Objects.requireNonNull(this.getLevel()).addFreshEntity(machineEntity);
        //System.out.println(this.holder.getDefinition().getId());
//        Field field = null;
//        try {
//            field = NotifiableEnergyContainer.class.getDeclaredField("inputVoltage");
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        }
//        field.setAccessible(true); // 关闭 Java 的访问检查
//
//        try {
//            field.setLong(this.energyContainer, 4 * field.getLong(this.energyContainer));
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }

        //energyContainer = createEnergyContainer(args);
    }


    @Override
    public int getMaxOverclockTier() {
        //this.energyContainer.getInputVoltage()
        return GTUtil.getTierByVoltage(4 * Math.max(energyContainer.getInputVoltage(), energyContainer.getOutputVoltage()));

    }

    @Override
    public void onMachineRemoved() {
        super.onMachineRemoved();
        if (machineEntity != null) {
            machineEntity.discard();

            machineEntity = null;
        }
    }

    //protected class EnergyBatteryTrait extends BatteryBufferMachine.EnergyBatteryTrait{



}
