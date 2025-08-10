package com.moguang.ctnhbio.api.machine;

import com.google.common.collect.Tables;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.*;
import com.gregtechceu.gtceu.api.gui.editor.EditableMachineUI;
import com.gregtechceu.gtceu.api.gui.editor.EditableUI;
import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyConfiguratorButton;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.CircuitFancyConfigurator;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.ui.GTRecipeTypeUI;
import com.gregtechceu.gtceu.common.data.GTDamageTypes;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import com.lowdragmc.lowdraglib.gui.widget.ProgressWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.lowdragmc.lowdraglib.utils.Position;
import com.moguang.ctnhbio.api.ILivingMachine;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.api.gui.CBGuiTextures;
import com.moguang.ctnhbio.api.gui.CBRecipeTypeUI;
import com.moguang.ctnhbio.api.gui.LivingMachineUIWidget;
import com.moguang.ctnhbio.api.machine.trait.NotifiableNutrientTrait;
import com.moguang.ctnhbio.api.machine.trait.SynchronizedNutrientStorage;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;

public class BasicLivingMachine extends SimpleTieredMachine implements ILivingMachine {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(BasicLivingMachine.class, SimpleTieredMachine.MANAGED_FIELD_HOLDER);
    @Persisted
    @Getter
    private final NotifiableNutrientTrait inputTrait;
    @Persisted
    @Getter
    private final NotifiableNutrientTrait outputTrait;
    @Persisted
    @Getter
    private final SynchronizedNutrientStorage storage;

    private LivingMetaMachineEntity machineEntity;
    @Setter
    private String name = null;

    public BasicLivingMachine(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction, double capacity, Object... args) {
        super(holder, tier, tankScalingFunction, args);
        this.storage = new SynchronizedNutrientStorage(capacity);
        this.inputTrait = new NotifiableNutrientTrait(this, storage, IO.IN);
        this.outputTrait = new NotifiableNutrientTrait(this, storage, IO.OUT);

        getMachineEntity();
    }

    @Override
    public LivingMetaMachineEntity getMachineEntity() {
        if(machineEntity == null) {
            machineEntity = ((LivingMetaMachineBlockEntity) holder).getHostedEntity();
        }
        return machineEntity;
    }

    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        return super.beforeWorking(recipe);
    }

    @Override
    protected BasicLivingRecipeLogic createRecipeLogic(Object... args) {
        return new BasicLivingRecipeLogic(this);
    }

    @Override
    public BasicLivingRecipeLogic getRecipeLogic() {
        return (BasicLivingRecipeLogic) super.getRecipeLogic();
    }
    @Override
    public double getNutrientAmount() {
        return storage.getAmount();
    }
    @Override
    public double getNutrientCapacity() {
        return storage.getCapacity();
    }

    @Override
    public void extractNutrient(double amount) {
        storage.extract(amount);
    }

    @Override
    public void addNutrient(double amount) {
        storage.add(amount);
    }

    @Override
    public InteractionResult tryToOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {

        ItemStack stack = player.getItemInHand(hand);

        // 判断是否是食物
        if (stack.isEdible()) {
            if (!getLevel().isClientSide) {
                // 消耗一个物品
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                int nutrition = stack.getFoodProperties(null).getNutrition();
                float saturation = stack.getFoodProperties(null).getSaturationModifier();
                storage.add(nutrition + 0.5 * saturation);

                getLevel().playSound(null, getPos().getX(), getPos().getY(), getPos().getZ(),
                        SoundEvents.GENERIC_EAT, SoundSource.PLAYERS,
                        1.0f, 1.0f);
            }

            return InteractionResult.sidedSuccess(getLevel().isClientSide);
        }

        // 默认行为（打开 GUI）
        return super.tryToOpenUI(player, hand, hit);
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
            }
        }
        else {
            this.energyContainer.changeEnergy(GTValues.V[tier + 1]);
            this.machineEntity.hurt(GTDamageTypes.ELECTRIC.source(this.getLevel()), tier);
        }
    }

//    @Override
//    public int getTier() {
//        return tier + 1;
//    }
//
//    @Override
//    public int getOverclockTier() {
//        return 1;
//    }

    @Override
    public int getMaxOverclockTier() {
        //this.energyContainer.getInputVoltage()
        return GTUtil.getTierByVoltage(4 * Math.max(energyContainer.getInputVoltage(), energyContainer.getOutputVoltage()));

    }
    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
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
                nutrientBar.setHoverTooltips(Component.translatable("ctnhbio.nutrient_bar.info"));
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

                    new CBRecipeTypeUI(recipeType).createEditableUITemplate(false, false).setupUI(template,
                            new CBRecipeTypeUI.RecipeHolder(livingMachine.recipeLogic::getProgressPercent,
                                    storages,
                                    new CompoundTag(),
                                    Collections.emptyList(),
                                    false, false));
                    createNutrientBar().setupUI(template, livingMachine);
                    // createCircuitConfigurator().setupUI(template, livingMachine);
                }
            }));
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

    @Override
    public void attachConfigurators(ConfiguratorPanel configuratorPanel) {
        configuratorPanel.attachConfigurators(new IFancyConfiguratorButton.Toggle(
                CBGuiTextures.BUTTON_POWER.getSubTexture(0, 0, 1, 0.5),
                CBGuiTextures.BUTTON_POWER.getSubTexture(0, 0.5, 1, 0.5),
                this::isWorkingEnabled, (clickData, pressed) -> setWorkingEnabled(pressed))
                .setTooltipsSupplier(pressed -> List.of(
                        Component.translatable(
                                pressed ? "behaviour.soft_hammer.enabled" : "behaviour.soft_hammer.disabled"))));
        for (var direction : Direction.values()) {
            if (getCoverContainer().hasCover(direction)) {
                var configurator = getCoverContainer().getCoverAtSide(direction).getConfigurator();
                if (configurator != null)
                    configuratorPanel.attachConfigurators(configurator);
            }
        }
        if (isCircuitSlotEnabled()) {
            configuratorPanel.attachConfigurators(new BioCircuitFancyConfigurator(circuitInventory.storage));
        }
    }
    public static class BasicLivingRecipeLogic extends RecipeLogic {

        public BasicLivingRecipeLogic(IRecipeLogicMachine machine) {
            super(machine);
        }

        @Override
        public @NotNull Iterator<GTRecipe> searchRecipe() {
            var recipes = CBRecipeTypes.BASIC_LIVING_RECIPES.searchRecipe(machine, r -> matchRecipe(r).isSuccess());
            if (!recipes.equals(Collections.emptyIterator())) {
                return recipes;
            }
            return super.searchRecipe();
        }
    }
}
