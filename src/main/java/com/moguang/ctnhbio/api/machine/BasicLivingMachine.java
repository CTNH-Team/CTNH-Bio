package com.moguang.ctnhbio.api.machine;

import com.google.common.collect.Tables;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.*;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.editor.EditableMachineUI;
import com.gregtechceu.gtceu.api.gui.editor.EditableUI;
import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.ui.GTRecipeTypeUI;
import com.gregtechceu.gtceu.common.data.GTDamageTypes;
import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.utils.Position;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.gui.CBGuiTextures;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.registry.CBEntities;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.function.BiFunction;

public class BasicLivingMachine extends SimpleTieredMachine {

    private LivingMetaMachineEntity machineEntity;

    public BasicLivingMachine(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction, Object... args) {
        super(holder, tier, tankScalingFunction, args);
        //getMachineEntity();
    }

    public LivingMetaMachineEntity getMachineEntity() {
        if(machineEntity == null) {
            machineEntity = ((LivingMetaMachineBlockEntity) holder).getLivingMachine();
        }
        return machineEntity;
    }

    @Override
    public boolean shouldWeatherOrTerrainExplosion() {
        return false;
    }

    @Override
    public void doExplosion(float explosionPower) {
        float inputTier = explosionPower -1;
        if(inputTier - tier >= 2) {
            if(machineEntity != null && machineEntity.isAlive())
            {
                machineEntity.hurt(GTDamageTypes.ELECTRIC.source(getLevel()), machineEntity.getMaxHealth());
                //machineEntity.;
            }
        }
        else {
            this.energyContainer.changeEnergy(GTValues.V[tier + 1]);
            this.machineEntity.hurt(GTDamageTypes.ELECTRIC.source(this.getLevel()), tier);
        }


    }

    @Override
    public int getMaxOverclockTier() {
        //this.energyContainer.getInputVoltage()
        return GTUtil.getTierByVoltage(4 * Math.max(energyContainer.getInputVoltage(), energyContainer.getOutputVoltage()));

    }

//    @Override
//    public void onMachineRemoved() {
//        super.onMachineRemoved();
//        //this.holder
//        if (machineEntity != null) {
//            machineEntity.discard();
//
//            machineEntity = null;
//        }
//    }

    //protected class EnergyBatteryTrait extends BatteryBufferMachine.EnergyBatteryTrait{

    //////////////////////////////////////
    // ************ GUI ****************//
    //////////////////////////////////////


    @Override
    public Widget createMainPage(FancyMachineUIWidget widget) {
        return super.createMainPage(widget);
    }
    public static BiFunction<ResourceLocation, GTRecipeType, EditableMachineUI> EDITABLE_UI_CREATOR_BIO = Util
            .memoize((path, recipeType) -> new EditableMachineUI("bio", path, () -> {
                WidgetGroup template = recipeType.getRecipeUI().createEditableUITemplate(false, false).createDefault();
                SlotWidget batterySlot = createBioBatterySlot().createDefault();
                WidgetGroup group = new WidgetGroup(0, 0, template.getSize().width,
                        Math.max(template.getSize().height, 78));
                template.setSelfPosition(new Position(0, (group.getSize().height - template.getSize().height) / 2));
                batterySlot.setSelfPosition(new Position(group.getSize().width / 2 - 9, group.getSize().height - 18));
                group.addWidget(batterySlot);
                group.addWidget(template);

                // TODO fix this.
                // if (ConfigHolder.INSTANCE.machines.ghostCircuit) {
                // SlotWidget circuitSlot = createCircuitConfigurator().createDefault();
                // circuitSlot.setSelfPosition(new Position(120, 62));
                // group.addWidget(circuitSlot);
                // }

                return group;
            }, (template, machine) -> {
                if (machine instanceof SimpleTieredMachine tieredMachine) {
                    var storages = Tables.newCustomTable(new EnumMap<>(IO.class),
                            LinkedHashMap<RecipeCapability<?>, Object>::new);
                    storages.put(IO.IN, ItemRecipeCapability.CAP, tieredMachine.importItems.storage);
                    storages.put(IO.OUT, ItemRecipeCapability.CAP, tieredMachine.exportItems.storage);
                    storages.put(IO.IN, FluidRecipeCapability.CAP, tieredMachine.importFluids);
                    storages.put(IO.OUT, FluidRecipeCapability.CAP, tieredMachine.exportFluids);
                    storages.put(IO.IN, CWURecipeCapability.CAP, tieredMachine.importComputation);
                    storages.put(IO.OUT, CWURecipeCapability.CAP, tieredMachine.exportComputation);

                    tieredMachine.getRecipeType().getRecipeUI().createEditableUITemplate(false, false).setupUI(template,
                            new GTRecipeTypeUI.RecipeHolder(tieredMachine.recipeLogic::getProgressPercent,
                                    storages,
                                    new CompoundTag(),
                                    Collections.emptyList(),
                                    false, false));
                    createBatterySlot().setupUI(template, tieredMachine);
                    // createCircuitConfigurator().setupUI(template, tieredMachine);
                }
            }));
    protected static EditableUI<SlotWidget, BasicLivingMachine> createBioBatterySlot() {
        return new EditableUI<>("battery_slot", SlotWidget.class, () -> {
            var slotWidget = new SlotWidget();
            slotWidget.setBackground(CBGuiTextures.SLOT_BIO, GuiTextures.CHARGER_OVERLAY);
            return slotWidget;
        }, (slotWidget, machine) -> {
            slotWidget.setHandlerSlot(machine.chargerInventory, 0);
            slotWidget.setCanPutItems(true);
            slotWidget.setCanTakeItems(true);
            slotWidget.setHoverTooltips(LangHandler.getMultiLang("gtceu.gui.charger_slot.tooltip",
                    GTValues.VNF[machine.getTier()], GTValues.VNF[machine.getTier()]).toArray(Component[]::new));
        });
    }
}
