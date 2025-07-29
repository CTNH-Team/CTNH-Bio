package com.moguang.ctnhbio.mixin.jei;

import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.integration.jei.recipe.GTRecipeJEICategory;
import mezz.jei.gui.recipes.RecipesGui;
import mezz.jei.api.recipe.RecipeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import static com.moguang.ctnhbio.registry.CBRecipeTypes.NUTRIENT;

@Mixin(value = RecipesGui.class, remap = false)
public abstract class RecipesGuiMixin {
//
//    /**
//     * Modify the recipeTypes list before it's processed by showTypes
//     */
//    @ModifyVariable(
//            method = "showTypes",
//            at = @At("HEAD"),
//            argsOnly = true
//    )
//    private List<RecipeType<?>> modifyRecipeTypes(List<RecipeType<?>> recipeTypes) {
//
//        List<RecipeType<?>> modifiedTypes = new ArrayList<>(recipeTypes);
//        if(recipeTypes.stream().anyMatch(
//                r-> Objects.equals(GTRecipeTypes.get(r.getUid().getPath()).group, NUTRIENT)
//        ))
//        {
//            modifiedTypes.add(
//                    GTRecipeJEICategory.
//            )
//        }
//        modifiedTypes.removeIf(type -> {
//            // Example condition - remove if UID contains "secret"
//            return type.getUid().toString().contains("secret");
//        });
//
//        return modifiedTypes;
//    }
}
