package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

import com.github.elenterius.biomancy.crafting.recipe.DecomposingRecipe;
import com.moguang.ctnhbio.utils.DecomposingRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;

@Mixin(value = GTRecipeType.class, remap = false)
public class GTRecipeTypeMixin {

    @Inject(method = "toGTrecipe", at = @At("HEAD"), cancellable = true)
    void handleBiomancyRecipes(ResourceLocation id, Recipe<?> r,
                               CallbackInfoReturnable<GTRecipe> cir) throws IOException {
        if (r instanceof DecomposingRecipe recipe) {
            var result = DecomposingRecipeHandler.toGTrecipe(recipe);
            cir.setReturnValue(result);
        }
    }
}
