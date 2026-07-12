package com.moguang.ctnhbio.api.recipe.customlogic;

import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.fluid.FluidIngredient;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import com.moguang.ctnhbio.registry.CBTags;

import java.util.ArrayList;
import java.util.List;

public final class BasicLivingLogic {

    private BasicLivingLogic() {}

    public static GTRecipe createNutrientRecipe(BasicLivingMachine machine) {
        var builder = CBRecipeBuilder.of(CTNHBio.id("nutrient"), CBRecipeTypes.BASIC_LIVING_RECIPES);
        List<ItemStack> items = new ArrayList<>();
        float nutrient = 0;

        for (int slot = 0; slot < machine.importItems.getSlots(); slot++) {
            ItemStack stack = machine.importItems.getStackInSlot(slot);
            if (stack.is(ModItems.NUTRIENT_PASTE.get())) {
                items.add(stack.copy());
                nutrient += stack.getCount() * 3;
            } else if (stack.is(ModItems.NUTRIENT_BAR.get())) {
                items.add(stack.copy());
                nutrient += stack.getCount() * 27;
            }
        }
        if (!items.isEmpty()) {
            builder.inputItems(items.toArray(ItemStack[]::new));
        }

        for (var tank : machine.importFluids.getStorages()) {
            FluidStack fluid = tank.getFluid().copy();
            if (!fluid.isEmpty() && fluid.getFluid().is(CBTags.NUTRIENT_FLUIDS_TAG)) {
                builder.inputFluids(FluidIngredient.of(CBTags.NUTRIENT_FLUIDS_TAG, fluid.getAmount()));
                nutrient += fluid.getAmount();
            }
        }

        return nutrient <= 0 ? null : builder.nutrient(-nutrient).duration(1).buildRawRecipe().toRuntime();
    }

    public static PotionRecipe createPotionRecipe(BasicLivingMachine machine) {
        List<MobEffectInstance> effects = new ArrayList<>();
        var builder = CBRecipeBuilder.of(CTNHBio.id("potion"), CBRecipeTypes.BASIC_LIVING_RECIPES);

        for (var tank : machine.importFluids.getStorages()) {
            FluidStack fluid = tank.getFluid().copy();
            int durationMultiplier = fluid.getAmount() / 250;
            if (!fluid.hasTag() || durationMultiplier <= 0) continue;

            boolean isPotion = false;
            ListTag customEffects = fluid.getTag().getList("CustomPotionEffects", 9);
            for (var effect : customEffects) {
                MobEffectInstance instance = MobEffectInstance.load((CompoundTag) effect);
                if (instance != null) {
                    effects.add(new MobEffectInstance(instance.getEffect(),
                            instance.getDuration() * durationMultiplier, instance.getAmplifier(),
                            instance.isAmbient(), instance.isVisible(), instance.showIcon()));
                    isPotion = true;
                }
            }

            ResourceLocation potionId = ResourceLocation.tryParse(fluid.getTag().getString("Potion"));
            var potion = ForgeRegistries.POTIONS.getValue(potionId);
            if (potion != null) {
                for (MobEffectInstance instance : potion.getEffects()) {
                    effects.add(new MobEffectInstance(instance.getEffect(),
                            instance.getDuration() * durationMultiplier, instance.getAmplifier(),
                            instance.isAmbient(), instance.isVisible(), instance.showIcon()));
                    isPotion = true;
                }
            }

            if (isPotion) {
                fluid.setAmount(250 * durationMultiplier);
                builder.inputFluids(fluid);
            }
        }

        return effects.isEmpty() ? null :
                new PotionRecipe(builder.duration(10).buildRawRecipe().toRuntime(), effects);
    }

    public record PotionRecipe(GTRecipe recipe, List<MobEffectInstance> effects) {}
}
