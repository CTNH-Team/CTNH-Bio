package com.moguang.ctnhbio.mixin.jei;

import com.mojang.logging.LogUtils;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IFocusFactory;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.gui.recipes.RecipeGuiLogic;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.*;
import java.util.stream.Collectors;

@Mixin(value = RecipeGuiLogic.class, remap = false)
public abstract class RecipeGuiLogicMixin {
//    private static final Logger LOGGER = LogUtils.getLogger();
//
//    @Shadow
//    @Final
//    private IFocusFactory focusFactory;
//
//    @ModifyVariable(
//            method = "showFocus",
//            at = @At("HEAD"),
//            argsOnly = true
//    )
//    private IFocusGroup enhanceTaggedRecipes(IFocusGroup originalFocuses) {
//        List<IFocus<?>> newFocuses = new ArrayList<>(originalFocuses.getAllFocuses());
//
//        originalFocuses.getAllFocuses().forEach(focus -> {
//            if (focus.getRole() == RecipeIngredientRole.CATALYST) {
//                ItemStack stack = (ItemStack) focus.getTypedValue().getIngredient();
//            }
//        });
//
//        return focusFactory.createFocusGroup(newFocuses);
//    }

}