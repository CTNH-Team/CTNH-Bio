package com.moguang.ctnhbio.mixin.create;

import com.moguang.ctnhbio.common.recipe.MobCrushingRecipe;
import com.moguang.ctnhbio.common.recipe.MobCrushingRecipeManager;
import com.moguang.ctnhbio.registry.CBItems;
import com.simibubi.create.content.kinetics.belt.behaviour.DirectBeltInputBehaviour;
import com.simibubi.create.content.kinetics.crusher.CrushingWheelControllerBlock;
import com.simibubi.create.content.kinetics.crusher.CrushingWheelControllerBlockEntity;
import com.simibubi.create.content.processing.recipe.ProcessingInventory;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.damageTypes.CreateDamageSources;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = CrushingWheelControllerBlockEntity.class)
public abstract class CrushingWheelControllerBlockEntityMixin extends SmartBlockEntity {

    private CrushingWheelControllerBlockEntity self = (CrushingWheelControllerBlockEntity)(Object)this;
    public CrushingWheelControllerBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
            )
    )
    public boolean hurt(Entity entity, DamageSource source, float originalDamage) {
        float damage = 1;
        if(entity.hurt(CreateDamageSources.crush(level),  damage) && !entity.isAlive())
        {
            //ItemStack result = CBItems.BIO_CAPACITOR.asStack();
            MobCrushingRecipeManager.findRecipe(entity)
                    .flatMap(MobCrushingRecipe::rollResult)
                    .ifPresent(stack -> {
                        self.inventory.setStackInSlot(0, stack);
                        outPut();
                    });

        }
        return false;
    }

    @Unique
    private void outPut()
    {
        Direction facing = getBlockState().getValue(CrushingWheelControllerBlock.FACING);
        var inventory = self.inventory;

        if (facing != Direction.UP) {
            BlockPos nextPos = worldPosition.below()
                    .relative(facing, facing.getAxis() == Direction.Axis.Y ? 0 : 1);

            DirectBeltInputBehaviour behaviour =
                    BlockEntityBehaviour.get(level, nextPos, DirectBeltInputBehaviour.TYPE);
            if (behaviour != null) {
                boolean changed = false;
                if (!behaviour.canInsertFromSide(facing))
                    return;
                for (int slot = 0; slot < inventory.getSlots(); slot++) {
                    ItemStack stack = inventory.getStackInSlot(slot);
                    if (stack.isEmpty())
                        continue;
                    ItemStack remainder = behaviour.handleInsertion(stack, facing, false);
                    if (remainder.equals(stack, false))
                        continue;
                    inventory.setStackInSlot(slot, remainder);
                    changed = true;
                }
                if (changed) {
                    setChanged();
                    sendData();
                }
            }
        }
    }
}
