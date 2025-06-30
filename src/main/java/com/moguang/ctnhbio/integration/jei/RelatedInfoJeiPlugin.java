package com.moguang.ctnhbio.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IFocusFactory;
//import mezz.jei.
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

@JeiPlugin
public class RelatedInfoJeiPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return null;
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addItemStackInfo(
                new ItemStack(Items.DIAMOND_AXE),
                Component.literal("这是一把钻石剑")
        );
        registration.addItemStackInfo(
                new ItemStack(Items.DIAMOND_AXE)
        );

        IModPlugin.super.registerRecipes(registration);
    }
}
