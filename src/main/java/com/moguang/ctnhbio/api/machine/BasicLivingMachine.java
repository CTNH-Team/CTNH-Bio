package com.moguang.ctnhbio.api.machine;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.*;
import com.gregtechceu.gtceu.api.gui.editor.EditableMachineUI;
import com.gregtechceu.gtceu.api.gui.editor.EditableUI;
import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyConfiguratorButton;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.CircuitFancyConfigurator;
import com.gregtechceu.gtceu.api.machine.feature.IMachineLife;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.ProgrammableCircuitSlotTrait;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.data.GTDamageTypes;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import com.lowdragmc.lowdraglib.gui.widget.ProgressWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.utils.Position;

import net.minecraft.Util;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;

import com.ctnhlang.*;
import com.google.common.collect.Tables;
import com.moguang.ctnhbio.api.ILivingMachine;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.api.gui.CBGuiTextures;
import com.moguang.ctnhbio.api.gui.CBRecipeTypeUI;
import com.moguang.ctnhbio.api.gui.LivingMachineUIWidget;
import com.moguang.ctnhbio.api.machine.trait.NotifiableNutrientHandler;
import com.moguang.ctnhbio.api.recipe.customlogic.BasicLivingLogic;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.theme.IThemeHelper;
import snownee.jade.api.ui.BoxStyle;
import snownee.jade.api.ui.IElementHelper;
import snownee.jade.impl.ui.HealthElement;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.*;
import java.util.function.BiFunction;

import static snownee.jade.addon.vanilla.StatusEffectsProvider.getEffectName;

public class BasicLivingMachine extends SimpleTieredMachine implements ILivingMachine, IMachineLife {

    @Persisted
    @Getter
    private final NotifiableNutrientHandler nutrientHandler;

    private LivingMetaMachineEntity machineEntity;
    private TickableSubscription entityBindingSubscription;
    @Setter
    private String name = null;

    public BasicLivingMachine(IMachineBlockEntity holder, int tier, Object... args) {
        super(holder, tier, (tiers) -> tiers * 32000, args);
        this.nutrientHandler = attachTrait(new NotifiableNutrientHandler(this, GTValues.V[tier] * 64));
        nutrientHandler.addChangedListener(getRecipeLogic()::updateTickSubscription);

        getMachineEntity();
    }

    @Override
    public LivingMetaMachineEntity getMachineEntity() {
        if (machineEntity == null || !machineEntity.isAlive() || machineEntity.isRemoved()) {
            refreshMachineEntityBinding();
        }
        return machineEntity;
    }

    protected void refreshMachineEntityBinding() {
        if (holder instanceof LivingMetaMachineBlockEntity blockEntity) {
            blockEntity.refreshHostedEntityBinding(true);
            machineEntity = blockEntity.getHostedEntity();
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        refreshMachineEntityBinding();
        entityBindingSubscription = subscribeServerTick(entityBindingSubscription, this::refreshMachineEntityBinding);
    }

    @Override
    public void onMachineRemoved() {
        if (holder instanceof LivingMetaMachineBlockEntity blockEntity) {
            blockEntity.removeHostedEntityImmediately();
        }
    }

    @Override
    public void onMachinePlaced(@Nullable LivingEntity player, ItemStack stack) {
        if (holder instanceof LivingMetaMachineBlockEntity blockEntity) {
            blockEntity.createHostedEntityImmediately();
            machineEntity = blockEntity.getHostedEntity();
        }
    }

    @Override
    protected BasicLivingRecipeLogic createRecipeLogic() {
        return new BasicLivingRecipeLogic(this);
    }

    @Override
    public BasicLivingRecipeLogic getRecipeLogic() {
        return (BasicLivingRecipeLogic) super.getRecipeLogic();
    }

    @Override
    public float getNutrientAmount() {
        return nutrientHandler.getAmount();
    }

    @Override
    public float getNutrientCapacity() {
        return nutrientHandler.getCapacity();
    }

    @Override
    public void extractNutrient(float amount) {
        nutrientHandler.extract(amount);
    }

    @Override
    public void addNutrient(float amount) {
        nutrientHandler.add(amount);
    }

    @Override
    public InteractionResult tryToOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

        // 判断是否是食物
        if (stack.isEdible() && stack.getFoodProperties(null) != null) {
            if (!getLevel().isClientSide) {
                var livingEntity = getMachineEntity();
                if (livingEntity == null) {
                    return InteractionResult.PASS;
                }
                // if (!player.getAbilities().instabuild && !stack.getFoodProperties(player).canAlwaysEat()) {
                // stack.shrink(1);
                // }
                int nutrition = stack.getFoodProperties(null).getNutrition();
                float saturation = stack.getFoodProperties(null).getSaturationModifier();
                livingEntity.eat(getLevel(), stack);
                nutrientHandler.add(nutrition + 0.5f * saturation);

                // getLevel().playSound(null, getPos().getX(), getPos().getY(), getPos().getZ(),
                // SoundEvents.GENERIC_EAT, SoundSource.PLAYERS,
                // 1.0f, 1.0f);
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
        float inputTier = explosionPower - 1;
        if (inputTier - tier >= 2) {
            if (machineEntity != null && machineEntity.isAlive()) {
                machineEntity.hurt(GTDamageTypes.ELECTRIC.source(getLevel()),
                        Math.max(machineEntity.getMaxHealth(), 10));
            }
        } else {
            if (getMachineEntity() != null) {
                this.energyContainer.changeEnergy(GTValues.V[tier + 1]);
                this.machineEntity.hurt(GTDamageTypes.ELECTRIC.source(this.getLevel()), tier);
            }
        }
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
                WidgetGroup template = recipeType.getRecipeUI().createEditableUITemplate(false, false).createDefault();
                ProgressWidget nutrientBar = createNutrientBar().createDefault();
                WidgetGroup group = new WidgetGroup(0, 0, template.getSize().width,
                        Math.max(template.getSize().height, 78));
                template.setSelfPosition(new Position(0, (group.getSize().height - template.getSize().height) / 2));
                nutrientBar.setSelfPosition(new Position(group.getSize().width / 2 - 20,
                        template.getPositionY() + (template.getSizeHeight() - nutrientBar.getSizeHeight()) / 2));
                // nutrientBar.setHoverTooltips(Component.translatable("ctnhbio.nutrient_bar.info"));
                group.addWidget(nutrientBar);
                group.addWidget(template);

                return group;
            }, (template, machine) -> {
                if (machine instanceof BasicLivingMachine livingMachine) {
                    var storages = Tables.newCustomTable(new EnumMap<>(IO.class),
                            LinkedHashMap<RecipeCapability<?>, Object>::new);
                    storages.put(IO.IN, ItemRecipeCapability.CAP, livingMachine.importItems.storage);
                    storages.put(IO.OUT, ItemRecipeCapability.CAP, livingMachine.exportItems.storage);
                    storages.put(IO.IN, FluidRecipeCapability.CAP, livingMachine.importFluids);
                    storages.put(IO.OUT, FluidRecipeCapability.CAP, livingMachine.exportFluids);

                    livingMachine.getRecipeType().getRecipeUI().createEditableUITemplate(false, false).setupUI(template,
                            new CBRecipeTypeUI.RecipeHolder(livingMachine.recipeLogic::getProgressPercent,
                                    storages,
                                    new CompoundTag(),
                                    Collections.emptyList(),
                                    false, false));
                    createNutrientBar().setupUI(template, livingMachine);
                    // createCircuitConfigurator().setupUI(template, livingMachine);
                }
            }));

    @CN("营养:")
    @EN("Nutrients:")
    static Lang nutrient;

    protected static EditableUI<ProgressWidget, BasicLivingMachine> createNutrientBar() {
        return new EditableUI<>("nutrient_bar", ProgressWidget.class, () -> {
            var progressBar = new ProgressWidget(ProgressWidget.JEIProgress, 0, 0, 9, 40,
                    new ProgressTexture(IGuiTexture.EMPTY, CBGuiTextures.NUTRIENT_BAR_MAX));
            progressBar.setFillDirection(ProgressTexture.FillDirection.DOWN_TO_UP);
            progressBar.setBackground(CBGuiTextures.NUTRIENT_BAR);
            return progressBar;
        }, (progressBar, machine) -> {

            progressBar.setProgressSupplier(
                    () -> machine.getNutrientAmount() / machine.getNutrientCapacity());
            progressBar.setHoverTooltips(
                    nutrient.translate());
            progressBar.setDynamicHoverTips(progress -> {
                float current = (float) (progress * machine.getNutrientCapacity());
                float max = machine.getNutrientCapacity();
                return String.format("%.0f / %.0f u", current, max);

            });

        });
    }

    @Override
    public void attachConfigurators(ConfiguratorPanel left, ConfiguratorPanel right) {
        getTraitOptional(ProgrammableCircuitSlotTrait.class).ifPresent(
                trait -> left.attachConfigurators(
                        new CircuitFancyConfigurator(trait.getStorage())
                                .setSlotBackground(CBGuiTextures.SLOT)));
        left.attachConfigurators(new IFancyConfiguratorButton.Toggle(
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
                    left.attachConfigurators(configurator);
            }
        }
        attachAllowSameConfigurators(right);
    }

    public static void appendEffect(LivingEntity entity, MobEffectInstance mobEffect) {
        MobEffectInstance existEffect = entity.getEffect(mobEffect.getEffect());
        if (existEffect != null) {
            MobEffectInstance newEffect = new MobEffectInstance(existEffect.getEffect(),
                    existEffect.getDuration() + mobEffect.getDuration(), existEffect.getAmplifier(),
                    existEffect.isAmbient(), existEffect.isVisible(), existEffect.showIcon());
            entity.addEffect(newEffect);
        } else {
            entity.addEffect(mobEffect);
        }
    }

    public static class BasicLivingRecipeLogic extends RecipeLogic {

        private boolean update = false;

        public BasicLivingRecipeLogic(IRecipeLogicMachine machine) {
            super(machine);
        }

        @Override
        public BasicLivingMachine getMachine() {
            return (BasicLivingMachine) super.getMachine();
        }

        @Override
        public void serverTick() {
            if (!isSuspend() && update) {
                update = false;
                BasicLivingMachine livingMachine = (BasicLivingMachine) machine;
                executeAuxiliaryRecipe(BasicLivingLogic.createNutrientRecipe(livingMachine));
                var potionRecipe = BasicLivingLogic.createPotionRecipe(livingMachine);
                if (potionRecipe != null && executeAuxiliaryRecipe(potionRecipe.recipe())) {
                    LivingMetaMachineEntity entity = livingMachine.getMachineEntity();
                    if (entity != null) {
                        potionRecipe.effects().forEach(effect -> appendEffect(entity, effect));
                    }
                }
            }

            super.serverTick();
        }

        @Override
        public void updateTickSubscription() {
            update = true;
            super.updateTickSubscription();
        }

        private boolean executeAuxiliaryRecipe(@Nullable GTRecipe recipe) {
            if (recipe == null || !RecipeHelper.matchRecipe(getLastGroup(), recipe).isSuccess()) return false;
            if (!RecipeHelper.handleRecipeIO(getLastGroup(), recipe, IO.IN).isSuccess()) return false;
            return RecipeHelper.handleRecipeIO(getLastGroup(), recipe, IO.OUT).isSuccess();
        }
    }

    @Override
    protected void writeMachineJadeData(CompoundTag data, BlockAccessor accessor) {
        super.writeMachineJadeData(data, accessor);
        LivingEntity living = getMachineEntity();
        if (living == null) {
            return;
        }
        var maxHealth = living.getMaxHealth();
        var health = living.getHealth();
        data.putFloat("MaxHealth", maxHealth);
        data.putFloat("Health", health);

        var nutrientAmount = getNutrientAmount();
        var nutrientCapacity = getNutrientCapacity();
        data.putDouble("NutrientAmount", nutrientAmount);
        data.putDouble("NutrientCapacity", nutrientCapacity);

        Collection<MobEffectInstance> effects = living.getActiveEffects();
        if (!effects.isEmpty()) {
            ListTag list = new ListTag();

            for (MobEffectInstance effect : effects) {
                CompoundTag compound = new CompoundTag();
                compound.putString("Name", Component.Serializer.toJson(getEffectName(effect)));
                if (effect.isInfiniteDuration()) {
                    compound.putBoolean("Infinite", true);
                } else {
                    compound.putInt("Duration", effect.getDuration());
                }

                compound.putBoolean("Bad", effect.getEffect().getCategory() == MobEffectCategory.HARMFUL);
                list.add(compound);
            }

            data.put("StatusEffects", list);
        }
    }

    @Override
    protected void appendMachineJadeTooltip(CompoundTag data, ITooltip tooltip, BlockAccessor accessor,
                                            IPluginConfig config) {
        if (data.contains("MaxHealth") && data.contains("Health")) {
            float maxHealth = data.getFloat("MaxHealth");
            float health = data.getFloat("Health");
            tooltip.add(new HealthElement(maxHealth, health));
        }
        if (data.contains("NutrientAmount") && data.contains("NutrientCapacity")) {
            double nutrientAmount = data.getDouble("NutrientAmount");
            double nutrientCapacity = data.getDouble("NutrientCapacity");
            var helper = tooltip.getElementHelper();
            var progress = nutrientCapacity == 0 ? 0 : (float) (nutrientAmount / nutrientCapacity);
            tooltip.add(
                    helper.progress(
                            progress,
                            Component.translatable("ctnhbio.jade.nutrient_stored",
                                    FormattingUtil.formatNumbers(nutrientAmount),
                                    FormattingUtil.formatNumbers(nutrientCapacity)),
                            helper.progressStyle().color(0xFF7d8c39, 0x566223).textColor(-1),
                            Util.make(BoxStyle.DEFAULT, style -> style.borderColor = 0xFF555555),
                            true));
        }
        if (data.contains("StatusEffects")) {
            IElementHelper helper = IElementHelper.get();
            ITooltip box = helper.tooltip();
            ListTag list = data.getList("StatusEffects", 10);
            Component[] lines = new Component[list.size()];

            for (int i = 0; i < lines.length; ++i) {
                CompoundTag compound = list.getCompound(i);
                MutableComponent name = Component.Serializer.fromJsonLenient(compound.getString("Name"));
                if (name != null) {
                    String duration;
                    if (compound.getBoolean("Infinite")) {
                        duration = I18n.get("effect.duration.infinite", new Object[0]);
                    } else {
                        duration = StringUtil.formatTickDuration(compound.getInt("Duration"));
                    }

                    MutableComponent s = Component.translatable("jade.potion", new Object[] { name, duration });
                    IThemeHelper t = IThemeHelper.get();
                    box.add(compound.getBoolean("Bad") ? t.danger(s) : t.success(s));
                }
            }

            tooltip.add(helper.box(box, BoxStyle.DEFAULT));
        }
        super.appendMachineJadeTooltip(data, tooltip, accessor, config);
    }
}
