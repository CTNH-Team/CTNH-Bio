package com.moguang.ctnhbio.api.recipe.customlogic;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.transfer.fluid.IFluidHandlerModifiable;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.registries.ForgeRegistries;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import com.simibubi.create.foundation.fluid.CombinedTankWrapper;
import org.jetbrains.annotations.Nullable;

public class BasicLivingLogic implements GTRecipeType.ICustomRecipeLogic {

    @Override
    public @Nullable GTRecipe createCustomRecipe(IRecipeCapabilityHolder holder) {
        var fluidHandlers = holder.getCapabilitiesFlat(IO.IN, FluidRecipeCapability.CAP).stream()
                .filter(IFluidHandlerModifiable.class::isInstance)
                .map(IFluidHandlerModifiable.class::cast)
                .toArray(IFluidHandlerModifiable[]::new);

        var inputFluids = new CombinedTankWrapper(fluidHandlers);
        ListTag allEffects = new ListTag();
        var builder = CBRecipeBuilder.of(CTNHBio.id("potion"), CBRecipeTypes.BASIC_LIVING_RECIPES);

        for (int i = 0; i < inputFluids.getTanks(); i++) {
            var fluidStack = inputFluids.getFluidInTank(i).copy();
            boolean isPotion = false;
            var durationMultiplier = fluidStack.getAmount() / 250;
            if (!fluidStack.hasTag() || durationMultiplier <= 0) continue;

            ListTag effects = fluidStack.getOrCreateTag().getList("CustomPotionEffects", 9);
            for (var effect : effects) {
                isPotion = true;
                MobEffectInstance mobEffectInstance = MobEffectInstance.load((CompoundTag) effect);
                if (mobEffectInstance != null) {
                    CompoundTag tag = new CompoundTag();
                    mobEffectInstance.save(tag);
                    tag.putInt("Duration", mobEffectInstance.getDuration() * durationMultiplier);
                    allEffects.add(tag);
                }
            }

            var potionID = ResourceLocation.tryParse(fluidStack.getOrCreateTag().getString("Potion"));
            if (ForgeRegistries.POTIONS.containsKey(potionID)) {
                isPotion = true;
                var potion = ForgeRegistries.POTIONS.getValue(potionID);
                for (var mobEffectInstance : potion.getEffects()) {
                    CompoundTag tag = new CompoundTag();
                    mobEffectInstance.save(tag);
                    tag.putInt("Duration", mobEffectInstance.getDuration() * durationMultiplier);
                    allEffects.add(tag);
                }
            }

            if (isPotion) {
                fluidStack.setAmount(250 * durationMultiplier);
                builder.inputFluids(fluidStack);
            }
        }

        return allEffects.isEmpty() ? null :
                builder.addData("effects", allEffects).duration(10).EUt(32).buildRawRecipe();
    }

    @Override
    public void buildRepresentativeRecipes() {
        var recipe = CBRecipeBuilder.of(CTNHBio.id("potion"), CBRecipeTypes.BASIC_LIVING_RECIPES)
                .inputFluids(FluidIngredient.of(CustomTags.POTION_FLUIDS, 250))
                .duration(10)
                .EUt(32)
                .buildRawRecipe();

        CBRecipeTypes.BASIC_LIVING_RECIPES.addToMainCategory(recipe);
    }
}
