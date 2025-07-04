package com.moguang.ctnhbio.api.machine;

import com.google.common.collect.Tables;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.*;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.UITemplate;
import com.gregtechceu.gtceu.api.gui.editor.EditableMachineUI;
import com.gregtechceu.gtceu.api.gui.editor.EditableUI;
import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.widget.PredicatedImageWidget;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.TieredEnergyMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.ui.GTRecipeTypeUI;
import com.gregtechceu.gtceu.common.data.GTDamageTypes;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.utils.BlockInfo;
import com.lowdragmc.lowdraglib.utils.Position;
import com.lowdragmc.lowdraglib.utils.TrackedDummyWorld;
import com.moguang.ctnhbio.api.INutrientMachine;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.gui.CBGuiTextures;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.api.gui.CBRecipeTypeUI;
import com.moguang.ctnhbio.api.gui.LivingMachineUIWidget;
import com.moguang.ctnhbio.registry.CBEntities;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;

public class BasicLivingMachine extends SimpleTieredMachine implements INutrientMachine {
    @Getter
    private final int nutrientAmount;
    @Getter
    private final int nutrientCapacity;

    private LivingMetaMachineEntity machineEntity;
    @Setter
    private String name = null;

    public BasicLivingMachine(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction, Object... args) {
        super(holder, tier, tankScalingFunction, args);
        this.nutrientCapacity = 100;
        this.nutrientAmount = 0;
        getMachineEntity();
    }

    public LivingMetaMachineEntity getMachineEntity() {
        if(machineEntity == null) {
            machineEntity = ((LivingMetaMachineBlockEntity) holder).getHostedEntity();
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


    //////////////////////////////////////
    // ************ GUI ****************//
    //////////////////////////////////////


    @Override
    public ModularUI createUI(Player entityPlayer) {
         return new ModularUI(176, 166, this, entityPlayer).widget(new LivingMachineUIWidget(this, 176, 166));
    }

    public static BiFunction<ResourceLocation, GTRecipeType, EditableMachineUI> EDITABLE_UI_CREATOR_BIO = Util
            .memoize((path, recipeType) -> new EditableMachineUI("bio", path, () -> {
                WidgetGroup template = new CBRecipeTypeUI(recipeType).createEditableUITemplate(false, false).createDefault();
                ProgressWidget nutrientBar = createNutrientBar().createDefault();
                WidgetGroup group = new WidgetGroup(0, 0, template.getSize().width,
                        Math.max(template.getSize().height, 78));
                template.setSelfPosition(new Position(0, (group.getSize().height - template.getSize().height) / 2));
                nutrientBar.setSelfPosition(new Position(group.getSize().width / 2 - 20, template.getPositionY() + (template.getSizeHeight() - nutrientBar.getSizeHeight()) / 2));
                group.addWidget(nutrientBar);
                group.addWidget(template);

                // TODO fix this.
//                 if (ConfigHolder.INSTANCE.machines.ghostCircuit) {
//                 SlotWidget circuitSlot = createCircuitConfigurator().createDefault();
//                 circuitSlot.setSelfPosition(new Position(120, 62));
//                 group.addWidget(circuitSlot);
//                 }

                return group;
            }, (template, machine) -> {
                if (machine instanceof BasicLivingMachine livingMachine) {
                    var storages = Tables.newCustomTable(new EnumMap<>(IO.class),
                            LinkedHashMap<RecipeCapability<?>, Object>::new);
                    storages.put(IO.IN, ItemRecipeCapability.CAP, livingMachine.importItems.storage);
                    storages.put(IO.OUT, ItemRecipeCapability.CAP, livingMachine.exportItems.storage);
                    storages.put(IO.IN, FluidRecipeCapability.CAP, livingMachine.importFluids);
                    storages.put(IO.OUT, FluidRecipeCapability.CAP, livingMachine.exportFluids);
                    storages.put(IO.IN, CWURecipeCapability.CAP, livingMachine.importComputation);
                    storages.put(IO.OUT, CWURecipeCapability.CAP, livingMachine.exportComputation);

                    livingMachine.getRecipeType().getRecipeUI().createEditableUITemplate(false, false).setupUI(template,
                            new GTRecipeTypeUI.RecipeHolder(livingMachine.recipeLogic::getProgressPercent,
                                    storages,
                                    new CompoundTag(),
                                    Collections.emptyList(),
                                    false, false));
                    createNutrientBar().setupUI(template, livingMachine);
                    // createCircuitConfigurator().setupUI(template, livingMachine);
                }
            }));
    protected static EditableUI<SlotWidget, BasicLivingMachine> createNutrientSlot() {
        return new EditableUI<>("nutrient_slot", SlotWidget.class, () -> {
            var slotWidget = new SlotWidget();
            slotWidget.setBackground(CBGuiTextures.SLOT_BIO, CBGuiTextures.NUTRIENT_SLOT_OVERLAY);
            return slotWidget;
        }, (slotWidget, machine) -> {
            slotWidget.setHandlerSlot(machine.chargerInventory, 0);
            slotWidget.setCanPutItems(true);
            slotWidget.setCanTakeItems(true);
            slotWidget.setHoverTooltips(LangHandler.getMultiLang("gtceu.gui.charger_slot.tooltip",
                    GTValues.VNF[machine.getTier()], GTValues.VNF[machine.getTier()]).toArray(Component[]::new));
        });
    }
    protected static EditableUI<ProgressWidget, BasicLivingMachine> createNutrientBar() {
        return new EditableUI<>("nutrient_bar", ProgressWidget.class, () -> {
            var progressBar = new ProgressWidget(ProgressWidget.JEIProgress, 0, 0, 9, 40,
                    new ProgressTexture(IGuiTexture.EMPTY, CBGuiTextures.NUTRIENT_BAR_MAX));
            progressBar.setFillDirection(ProgressTexture.FillDirection.DOWN_TO_UP);
            progressBar.setBackground(CBGuiTextures.NUTRIENT_BAR);
            return progressBar;
        }, (progressBar, machine) -> {
            progressBar.setProgressSupplier(
                    () -> machine.getNutrientAmount() * 1d / machine.getNutrientCapacity());
        });
    }
}
