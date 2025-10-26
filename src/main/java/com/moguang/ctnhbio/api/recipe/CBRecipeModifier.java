package com.moguang.ctnhbio.api.recipe;

import com.github.elenterius.biofactory.init.ModFluids;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CBRecipeModifier {
    public static @NotNull ModifierFunction batchMode(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        if (recipe.duration < ConfigHolder.INSTANCE.machines.batchDuration) {
            int parallel = ConfigHolder.INSTANCE.machines.batchDuration / recipe.duration;
            parallel = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, parallel);

            if (parallel == 0) return ModifierFunction.NULL;
            if (parallel == 1) return ModifierFunction.IDENTITY;

            int duration = recipe.recipeType == CBRecipeTypes.BASIC_LIVING_RECIPES ? 1 : parallel;

            return ModifierFunction.builder()
                    .inputModifier(ContentModifier.multiplier(parallel))
                    .outputModifier(ContentModifier.multiplier(parallel))
                    .durationMultiplier(duration)
                    .batchParallels(parallel)
                    .build();
        }
        return ModifierFunction.IDENTITY;
    }

    public static ModifierFunction digesterRecipeModifier(MetaMachine machine, GTRecipe recipe) {
        if(recipe.recipeType == CBRecipeTypes.DIGEST_RECIPES &&
                recipe.data.getInt("circuit") != 0
        ) {
            return original -> {
                // 计算输入食物总营养值(乘以2)

                float totalNutrition = 0.0f;

                IItemHandler inputItems = machine.getItemHandlerCap(null, false);


                // 2. 遍历输入槽，收集所有有效食物
                List<ItemStack> foods = new ArrayList<>();
                for (int i = 0; i < inputItems.getSlots(); i++) {
                    ItemStack stack = inputItems.getStackInSlot(i);
                    if (!stack.isEmpty() && stack.isEdible()) {
                        foods.add(stack);
                    }
                }
                if (foods.isEmpty()) {
                    return null;
                }

                for (ItemStack stack : foods) {
                    totalNutrition += getFoodNutritionValue(stack) * stack.getCount() * 2; // 营养值乘以2
                }


                if(original.data.getInt("circuit") == 1)
                {
                    int totalPaste = Math.round(totalNutrition / 3);
                    int barCount = totalPaste / 9;
                    int remainingPaste = totalPaste % 9;
                    //if(re)
                    // 创建输出列表
                    List<ItemStack> newOutputs = new ArrayList<>();
                    if (barCount > 0) {
                        newOutputs.add(new ItemStack(ModItems.NUTRIENT_BAR.get(), barCount));
                    }
                    if (remainingPaste > 0) {
                        newOutputs.add(new ItemStack(ModItems.NUTRIENT_PASTE.get(), remainingPaste));
                    }



                    return GTRecipeBuilder.ofRaw()
                            .inputItems(foods.toArray(new ItemStack[0]))
                            .outputItems(newOutputs.toArray(new ItemStack[0]))
                            .duration((int)totalNutrition) // 持续时间基于总营养值
                            .EUt(32)
                            .buildRawRecipe();
                }
                else{
                    int fluidAmount = Math.round(totalNutrition); // 1营养值 = 1mB流体

                    return GTRecipeBuilder.ofRaw()
                            .inputItems(foods.stream().map(SizedIngredient::create).toArray(SizedIngredient[]::new))
                            .outputFluids(new FluidStack(ModFluids.NUTRIENTS_FLUID.get(), fluidAmount))
                            .duration((int) totalNutrition)
                            .EUt(32)
                            .buildRawRecipe();
                }



            };
        }
        return ModifierFunction.IDENTITY;
    }

    /**
     * 获取食物的营养值 (饱食度 + 饱和度)
     */
    private static float getFoodNutritionValue(ItemStack stack) {
        Item item = stack.getItem();
        if (item.isEdible()) {
            FoodProperties food = item.getFoodProperties();
            if (food != null) {
                return food.getNutrition() + food.getSaturationModifier();
            }
        }
        return 0.0f;
    }

}
