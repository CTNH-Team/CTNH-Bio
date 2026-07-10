package com.moguang.ctnhbio.api.machine.multiblock;

import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyConfiguratorButton;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.FancySelectorConfigurator;
import com.gregtechceu.gtceu.api.machine.feature.IMachineLife;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;

import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;

import com.moguang.ctnhbio.api.ILivingMachine;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.api.gui.CBGuiTextures;
import com.moguang.ctnhbio.api.gui.LivingMachineUIWidget;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.api.machine.trait.NotifiableNutrientHandler;
import com.moguang.ctnhbio.api.pattern.GrowingBlockPattern;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import static com.moguang.ctnhbio.api.machine.BasicLivingMachine.appendEffect;

public class WorkableLivingMultiblockMachine extends WorkableElectricMultiblockMachine
                                             implements ILivingMachine, IMachineLife {

    @Persisted
    @Getter
    protected final NotifiableNutrientHandler nutrientHandler;

    protected static final float capacity = 1000000;
    protected static final float NUTRIENT_NEEDED_FOR_GROWTH = 1;

    protected GrowingBlockPattern growingBlockPattern;

    @Persisted
    protected ResourceLocation lastRecipeId;

    protected LivingMetaMachineEntity machineEntity;
    protected TickableSubscription entityBindingSubscription;

    public WorkableLivingMultiblockMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.nutrientHandler = new NotifiableNutrientHandler(this, capacity);
        nutrientHandler.addChangedListener(getRecipeLogic()::updateTickSubscription);
        nutrientHandler.add(1000);
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
        if (stack.isEdible()) {
            if (!getLevel().isClientSide) {
                // 消耗一个物品
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                int nutrition = stack.getFoodProperties(null).getNutrition();
                float saturation = stack.getFoodProperties(null).getSaturationModifier();
                nutrientHandler.add(nutrition + 0.5f * saturation);

                getLevel().playSound(null, getPos().getX(), getPos().getY(), getPos().getZ(),
                        SoundEvents.GENERIC_EAT, SoundSource.PLAYERS,
                        1.0f, 1.0f);
                if (!isFormed()) checkGrow();
            }

            return InteractionResult.sidedSuccess(getLevel().isClientSide);
        }

        // 默认行为（打开 GUI）
        return super.tryToOpenUI(player, hand, hit);
    }

    @Override
    public @NotNull ModularUI createUI(@NotNull Player entityPlayer) {
        return new ModularUI(198, 208, this, entityPlayer).widget(new LivingMachineUIWidget(this, 198, 208));
    }

    @Override
    public void attachConfigurators(ConfiguratorPanel configuratorPanel) {
        configuratorPanel.attachConfigurators(new FancySelectorConfigurator<>(VoidingMode.VALUES, getVoidingMode(),
                this::setVoidingMode)
                .setTooltip(m -> List.of(Component.translatable("gtceu.gui.multiblock.voiding_mode"),
                        Component.translatable(m.getSerializedName()))));

        configuratorPanel.attachConfigurators(new IFancyConfiguratorButton.Toggle(
                CBGuiTextures.BUTTON_POWER.getSubTexture(0, 0, 1, 0.5),
                CBGuiTextures.BUTTON_POWER.getSubTexture(0, 0.5, 1, 0.5),
                this::isWorkingEnabled, (clickData, pressed) -> setWorkingEnabled(pressed))
                .setTooltipsSupplier(pressed -> List.of(
                        Component.translatable(
                                pressed ? "behaviour.soft_hammer.enabled" : "behaviour.soft_hammer.disabled"))));
    }

    @Override
    public IGuiTexture getScreenTexture() {
        return CBGuiTextures.DISPLAY_BIO;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        refreshMachineEntityBinding();
        entityBindingSubscription = subscribeServerTick(entityBindingSubscription, this::refreshMachineEntityBinding);
        // subscribeServerTick(this::checkGrow);
        checkGrow();
        subscribeServerTick(this::tickGrow);
    }

    @Override
    public void onMachineRemoved() {
        if (holder instanceof LivingMetaMachineBlockEntity blockEntity) {
            blockEntity.removeHostedEntityImmediately();
        }
    }

    @Override
    public void onMachinePlaced(LivingEntity player, ItemStack stack) {
        if (holder instanceof LivingMetaMachineBlockEntity blockEntity) {
            blockEntity.createHostedEntityImmediately();
            machineEntity = blockEntity.getHostedEntity();
        }
    }

    public boolean shouldTick(int interval) {
        return (!isFormed() && getOffsetTimer() % interval == 0) || getOffsetTimer() % 10 * interval == 0;
    }

    public void checkGrow() {
        if (true || shouldTick(20)) {
            isFormed = false;
            checkPattern();
            if (!isFormed()) {
                if (growingBlockPattern == null)
                    growingBlockPattern = GrowingBlockPattern.getGrowingBlockPattern(getPattern());

                if (growingBlockPattern.growPlan.isCompleted())
                    growingBlockPattern.generateGrowPlan(this, new GrowingBlockPattern.GrowSetting());
            }

        }
    }

    public void tickGrow() {
        if (shouldTick(2) &&
                getNutrientAmount() >= NUTRIENT_NEEDED_FOR_GROWTH &&
                growingBlockPattern != null &&
                growingBlockPattern.growPlan.tick()) {
            nutrientHandler.extract(NUTRIENT_NEEDED_FOR_GROWTH);
            if (growingBlockPattern.growPlan.isCompleted()) checkPattern();
        }
        // updatePartPositions();
    }

    @Override
    public void afterWorking() {
        super.afterWorking();
        var recipe = getRecipeLogic().getLastRecipe();
        if (recipe != null && recipe.data.contains("effects")) {
            var tag = recipe.data.get("effects");
            if (tag instanceof ListTag listTag) {
                LivingMetaMachineEntity entity = getMachineEntity();
                if (entity == null) {
                    return;
                }
                listTag.stream()
                        .filter(CompoundTag.class::isInstance)
                        .map(CompoundTag.class::cast)
                        .map(MobEffectInstance::load)
                        .filter(Objects::nonNull)
                        .forEach(effect -> appendEffect(entity, effect));
            }

        }
    }

    @Override
    protected BasicLivingMachine.BasicLivingRecipeLogic createRecipeLogic(Object... args) {
        return new BasicLivingMachine.BasicLivingRecipeLogic(this);
    }

    @Override
    public BasicLivingMachine.BasicLivingRecipeLogic getRecipeLogic() {
        return (BasicLivingMachine.BasicLivingRecipeLogic) super.getRecipeLogic();
    }
}
