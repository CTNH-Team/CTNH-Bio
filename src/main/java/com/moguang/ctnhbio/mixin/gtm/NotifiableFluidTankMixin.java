package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.ICapabilityTrait;
import com.gregtechceu.gtceu.api.machine.trait.MachineTrait;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.transfer.fluid.IFluidHandlerModifiable;
import com.gregtechceu.gtceu.common.fluid.potion.PotionFluid;
import com.moguang.ctnhbio.api.ILivingMachine;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;

@Mixin(NotifiableFluidTank.class)
public abstract class NotifiableFluidTankMixin extends NotifiableRecipeHandlerTrait<FluidIngredient> implements ICapabilityTrait, IFluidHandlerModifiable {
    public NotifiableFluidTankMixin(MetaMachine machine) {
        super(machine);
    }

    @Inject(method = "handleRecipeInner",
            at = @At(value = "INVOKE", target = "Lcom/gregtechceu/gtceu/api/transfer/fluid/CustomFluidTank;drain(ILnet/minecraftforge/fluids/capability/IFluidHandler$FluidAction;)Lnet/minecraftforge/fluids/FluidStack;", shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false)
    public void handlerRecipeInner(IO io, GTRecipe recipe, List<FluidIngredient> left, boolean simulate, CallbackInfoReturnable<List<FluidIngredient>> cir, Runnable[] listeners, boolean changed, IFluidHandler.FluidAction action, FluidStack[] visited, Iterator it, FluidIngredient ingredient, FluidStack[] fluids, int amount, int tank, FluidStack current, int count) {
        if (getMachine() instanceof ILivingMachine livingMachine) {
            if (recipe.data.getBoolean("potion")) {
                if (current.getFluid() instanceof PotionFluid potionFluid) {
                    ListTag effects = current.getOrCreateTag().getList("CustomPotionEffects", 9);
                    for (var effect : effects) {
                        MobEffectInstance mobEffectInstance = MobEffectInstance.load((CompoundTag) effect);
                        livingMachine.getMachineEntity().addEffect(mobEffectInstance);
                    }
                    var potion = current.getOrCreateTag().getString("Potion");
                    Potion potion1 = BuiltInRegistries.POTION.get(new ResourceLocation(potion));
                    var mobEffects = potion1.getEffects();
                    for (var mobEffect: mobEffects) {
                        livingMachine.getMachineEntity().addEffect(new MobEffectInstance(mobEffect));
                    }
                }
            }
        }
    }
}
