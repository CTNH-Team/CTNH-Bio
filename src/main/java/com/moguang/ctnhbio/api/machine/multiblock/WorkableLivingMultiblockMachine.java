package com.moguang.ctnhbio.api.machine.multiblock;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.moguang.ctnhbio.api.ILivingMachine;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class WorkableLivingMultiblockMachine extends WorkableElectricMultiblockMachine implements ILivingMachine {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(WorkableLivingMultiblockMachine.class, WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);
    @Getter
    @Persisted
    private double nutrientAmount;
    @Getter
    @Persisted
    private double nutrientCapacity;

    @Persisted
    public ResourceLocation lastRecipeId;

    private LivingMetaMachineEntity machineEntity;
    public WorkableLivingMultiblockMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.nutrientAmount = 0;
        this.nutrientCapacity = 100;
    }
    @Override
    public LivingMetaMachineEntity getMachineEntity() {
        if(machineEntity == null) {
            machineEntity = ((LivingMetaMachineBlockEntity) holder).getHostedEntity();
        }
        return machineEntity;
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
                nutrientAmount = Math.min(nutrientAmount + nutrition + 0.5 * saturation, nutrientCapacity);

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
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        if(recipe != null) lastRecipeId = recipe.id;

        return super.beforeWorking(recipe);
    }

    @Override
    public void afterWorking() {
        tryDifferentiate();
        super.afterWorking();
    }

    public void tryDifferentiate(){

    }

}
