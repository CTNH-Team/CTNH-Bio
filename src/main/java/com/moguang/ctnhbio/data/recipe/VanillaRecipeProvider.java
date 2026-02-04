package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.datagen.recipes.builder.BioForgingRecipeBuilder;
import com.github.elenterius.biomancy.datagen.recipes.builder.ItemData;
import com.github.elenterius.biomancy.init.ModBioForgeTabs;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBMachines;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import java.util.List;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.LV;
import static com.gregtechceu.gtceu.common.data.GTItems.*;


public class VanillaRecipeProvider extends RecipeProvider {
    public VanillaRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        //buildBioForgingRecipes(consumer);

    }

    public static void buildBrewingRecipes()
    {
        ItemStack potionStack = new ItemStack(Items.POTION);
        MobEffectInstance effectInstance = new MobEffectInstance(
                MobEffects.SATURATION, // 效果类型
                100,                   // 持续时间(ticks)
                0,                     // 效果等级
                false,                 // 环境粒子
                true,                  // 显示图标
                true                   // 可见效果
        );
        //Potion potion = new Potion(effectInstance);
        ItemStack resultStack = PotionUtils.setCustomEffects(potionStack, List.of(effectInstance));
        ItemStack splashPotionStack = new ItemStack(Items.SPLASH_POTION);
        ItemStack splashResultStack = PotionUtils.setCustomEffects(splashPotionStack, List.of(effectInstance));

        BrewingRecipeRegistry.addRecipe(
                Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)),
                Ingredient.of(ModItems.NUTRIENT_BAR.get()),
                resultStack
        );

        BrewingRecipeRegistry.addRecipe(
                Ingredient.of(resultStack), // 基础饱和药水
                Ingredient.of(Items.GUNPOWDER), // 火药
                splashResultStack // 喷溅型结果
        );
    }

}
