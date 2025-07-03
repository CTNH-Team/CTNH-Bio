package com.moguang.ctnhbio.api.machine;

import com.google.common.collect.Tables;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.*;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.UITemplate;
import com.gregtechceu.gtceu.api.gui.editor.EditableUI;
import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.common.data.GTDamageTypes;
import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.utils.BlockInfo;
import com.lowdragmc.lowdraglib.utils.Position;
import com.lowdragmc.lowdraglib.utils.TrackedDummyWorld;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.gui.CBGuiTextures;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.api.gui.CBRecipeTypeUI;
import com.moguang.ctnhbio.api.gui.LivingMachineUIWidget;
import com.moguang.ctnhbio.registry.CBEntities;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import lombok.Setter;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class BasicLivingMachine extends SimpleTieredMachine {

    private LivingMetaMachineEntity machineEntity;
    @Setter
    private String name = null;

    public BasicLivingMachine(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction, Object... args) {
        super(holder, tier, tankScalingFunction, args);
        //getMachineEntity();
    }

    @Override
    public void onLoad() {
        super.onLoad();

    }

    public LivingMetaMachineEntity getMachineEntity() {
        if(machineEntity == null) {
            machineEntity = ((LivingMetaMachineBlockEntity<?>) holder).getMachineEntity();
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
    public ModularUI createUI(Player entityPlayer) {
        return new ModularUI(176, 166, this, entityPlayer).widget(new LivingMachineUIWidget(this, 176, 166));

//        var storages = Tables.newCustomTable(new EnumMap<>(IO.class), LinkedHashMap<RecipeCapability<?>, Object>::new);
//        storages.put(IO.IN, ItemRecipeCapability.CAP, importItems.storage);
//        storages.put(IO.OUT, ItemRecipeCapability.CAP, exportItems.storage);
//        String title = name == null? getBlockState().getBlock().getDescriptionId():name;
//        //System.out.println(title);
//        var group = new CBRecipeTypeUI(getRecipeType()).createUITemplate(recipeLogic::getProgressPercent,
//                storages,
//                new CompoundTag(),
//                Collections.emptyList(),
//                false,
//                false);
//        Position pos = new Position((Math.max(group.getSize().width + 4 + 8, 176) - 4 - group.getSize().width) / 2 + 4,
//                20);
//        group.setSelfPosition(pos);
//        var template = createNutrientSlot();
//        SlotWidget nutrientSlot = template.createDefault();
//        template.setupUI(group, this);
//        nutrientSlot.setSelfPosition(new Position(group.getSize().width / 2 - 9, 60));
//        return new ModularUI(176, 166, this, entityPlayer)
//                .background(CBGuiTextures.BACKGROUND_BIO)
//                .widget(group)
//                .widget(nutrientSlot)
//                .widget(new LabelWidget(5, 5, title))
////                .widget(new PredicatedImageWidget(pos.x + group.getSize().width / 2 - 9,
////                        pos.y + group.getSize().height / 2 - 9, 18, 18,
////                        GuiTextures.INDICATOR_NO_STEAM.get(isHighPressure))
////                        .setPredicate(recipeLogic::isWaiting))
//                .widget(UITemplate.bindPlayerInventory(entityPlayer.getInventory(),
//                        CBGuiTextures.SLOT_BIO, 7, 84, true));
    }

//    public static BiFunction<ResourceLocation, GTRecipeType, EditableMachineUI> EDITABLE_UI_CREATOR_BIO = Util
//            .memoize((path, recipeType) -> new EditableMachineUI("bio", path, () -> {
//                WidgetGroup template = new CBRecipeTypeUI(recipeType).createEditableUITemplate(false, false).createDefault();
//                SlotWidget batterySlot = createBioBatterySlot().createDefault();
//                WidgetGroup group = new WidgetGroup(0, 0, template.getSize().width,
//                        Math.max(template.getSize().height, 78));
//                template.setSelfPosition(new Position(0, (group.getSize().height - template.getSize().height) / 2));
//                batterySlot.setSelfPosition(new Position(group.getSize().width / 2 - 9, group.getSize().height - 18));
//                group.addWidget(batterySlot);
//                group.addWidget(template);
//
//                // TODO fix this.
//                // if (ConfigHolder.INSTANCE.machines.ghostCircuit) {
//                // SlotWidget circuitSlot = createCircuitConfigurator().createDefault();
//                // circuitSlot.setSelfPosition(new Position(120, 62));
//                // group.addWidget(circuitSlot);
//                // }
//
//                return group;
//            }, (template, machine) -> {
//                if (machine instanceof SimpleTieredMachine tieredMachine) {
//                    var storages = Tables.newCustomTable(new EnumMap<>(IO.class),
//                            LinkedHashMap<RecipeCapability<?>, Object>::new);
//                    storages.put(IO.IN, ItemRecipeCapability.CAP, tieredMachine.importItems.storage);
//                    storages.put(IO.OUT, ItemRecipeCapability.CAP, tieredMachine.exportItems.storage);
//                    storages.put(IO.IN, FluidRecipeCapability.CAP, tieredMachine.importFluids);
//                    storages.put(IO.OUT, FluidRecipeCapability.CAP, tieredMachine.exportFluids);
//                    storages.put(IO.IN, CWURecipeCapability.CAP, tieredMachine.importComputation);
//                    storages.put(IO.OUT, CWURecipeCapability.CAP, tieredMachine.exportComputation);
//
//                    tieredMachine.getRecipeType().getRecipeUI().createEditableUITemplate(false, false).setupUI(template,
//                            new GTRecipeTypeUI.RecipeHolder(tieredMachine.recipeLogic::getProgressPercent,
//                                    storages,
//                                    new CompoundTag(),
//                                    Collections.emptyList(),
//                                    false, false));
//                    createBatterySlot().setupUI(template, tieredMachine);
//                    // createCircuitConfigurator().setupUI(template, tieredMachine);
//                }
//            }));


    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 100, 100);
        if (isRemote()) {
            group.addWidget(new ImageWidget((100 - 48) / 2, 60, 48, 16, GuiTextures.SCENE));
            TrackedDummyWorld world = new TrackedDummyWorld();
            world.addBlock(BlockPos.ZERO, BlockInfo.fromBlockState(self().getBlockState()));
            LivingMetaMachineEntity entity = ((LivingMetaMachineBlockEntity<?>)holder).getEntityType().create(world);
            entity.setPos(BlockPos.ZERO, ((LivingMetaMachineBlockEntity<?>)holder).getEntityOffset());
            world.addFreshEntity(entity);
            //world.addFreshEntity(getMachineEntity());
            SceneWidget sceneWidget = new SceneWidget(0, 0, 100, 100, world) {

                @Override
                @OnlyIn(Dist.CLIENT)
                public void drawInBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY,
                                             float partialTicks) {
                    // AUTO ROTATION
                    if (renderer != null) {
                        this.rotationPitch = (partialTicks + getGui().getTickCount()) * 2;
                        renderer.setCameraLookAt(this.center, 0.1f, Math.toRadians(this.rotationPitch),
                                Math.toRadians(this.rotationYaw));
                    }
                    super.drawInBackground(graphics, mouseX, mouseY, partialTicks);
                }
            };
            sceneWidget.useOrtho(true)
                    .setOrthoRange(0.5f)
                    .setScalable(false)
                    .setDraggable(false)
                    .setRenderFacing(false)
                    .setRenderSelect(false);
            sceneWidget.getRenderer().setFov(30);
            group.addWidget(sceneWidget);
            sceneWidget.setRenderedCore(List.of(BlockPos.ZERO), null);
        }
        return group;
    }

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
}
