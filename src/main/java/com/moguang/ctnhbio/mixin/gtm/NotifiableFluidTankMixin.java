package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.ICapabilityTrait;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.transfer.fluid.IFluidHandlerModifiable;
import com.moguang.ctnhbio.api.ILivingMachine;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
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
        if (recipe.data.getBoolean("potion") && !simulate) {
            if (getMachine() instanceof ILivingMachine livingMachine) {
                if (current.hasTag()) {
                    ListTag effects = current.getOrCreateTag().getList("CustomPotionEffects", 9);
                    for (var effect : effects) {
                        MobEffectInstance mobEffectInstance = MobEffectInstance.load((CompoundTag) effect);
                        appendEffect(livingMachine.getMachineEntity(), mobEffectInstance);
                    }
                    var potion = current.getOrCreateTag().getString("Potion");
                    Potion potion1 = BuiltInRegistries.POTION.get(ResourceLocation.parse(potion));
                    var mobEffects = potion1.getEffects();
                    for (var mobEffect: mobEffects) {
                        appendEffect(livingMachine.getMachineEntity(), mobEffect);
                    }
                }
            }
        }
    }

    public void appendEffect(LivingEntity entity, MobEffectInstance mobEffect) {
        MobEffectInstance existEffect = entity.getEffect(mobEffect.getEffect());
        if (existEffect != null) {
            MobEffectInstance newEffect = new MobEffectInstance(existEffect.getEffect(), existEffect.getDuration() + mobEffect.getDuration() / 5, existEffect.getAmplifier(), existEffect.isAmbient(), existEffect.isVisible(), existEffect.showIcon());
            entity.addEffect(newEffect);
        }
        else {
            entity.addEffect(new MobEffectInstance(mobEffect.getEffect(), mobEffect.getDuration() / 5, mobEffect.getAmplifier(), mobEffect.isAmbient(), mobEffect.isVisible(), mobEffect.showIcon()));
        }
    }
}
