package com.moguang.ctnhbio.integration.jei;

import com.github.elenterius.biomancy.init.ModBlocks;
import com.github.elenterius.biomancy.init.ModItems;
import com.github.elenterius.biomancy.styles.TextStyles;
import com.github.elenterius.biomancy.util.ComponentUtil;
import com.moguang.ctnhbio.machine.multiblock.MultiblocksA;
import com.moguang.ctnhbio.registry.CBItems;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JeiPlugin
public class RelatedInfoJeiPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return null;
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addItemStackInfo(
                List.of(
                        new ItemStack(ModBlocks.PRIMORDIAL_CRADLE.get()),
                        new ItemStack(ModItems.INJECTOR.get()),
                        new ItemStack(CBItems.ORGANIC_VIAL),
                        new ItemStack(MultiblocksA.GREAT_FLESH.getItem())
                ),
                ComponentUtil.literal("SWINTER").withStyle(TextStyles.PRIMORDIAL_RUNES_MUTED_PURPLE),
                Component.translatable("ctnhbio.great_flesh.info.0"),
                ComponentUtil.literal("MO_GUANG").withStyle(TextStyles.PRIMORDIAL_RUNES_MUTED_PURPLE),
                Component.translatable("ctnhbio.great_flesh.info.1"),
                ComponentUtil.literal("LUCKY_BLOCK").withStyle(TextStyles.PRIMORDIAL_RUNES_MUTED_PURPLE),
                Component.translatable("ctnhbio.great_flesh.info.2"),
                ComponentUtil.literal("VIX_HENTX").withStyle(TextStyles.PRIMORDIAL_RUNES_MUTED_PURPLE)
        );



        IModPlugin.super.registerRecipes(registration);
    }
}
