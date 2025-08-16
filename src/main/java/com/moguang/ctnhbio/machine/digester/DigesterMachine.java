package com.moguang.ctnhbio.machine.digester;

import com.github.elenterius.biofactory.init.ModFluids;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.IntCircuitIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gregtechceu.gtceu.data.recipe.misc.CircuitRecipes;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.registry.CBMachines;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

public class DigesterMachine extends BasicLivingMachine {
    public DigesterMachine(IMachineBlockEntity holder, int tier, Int2IntFunction tankScalingFunction, double capacity, Object... args) {
        super(holder, tier, tankScalingFunction, capacity, args);
    }

    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe) {
        if(machine instanceof DigesterMachine digester && recipe.recipeType == CBRecipeTypes.DIGEST_RECIPES) {
            return original -> {
                // 计算输入食物总营养值(乘以2)

                float totalNutrition = 0.0f;
//                var foods = original.getInputContents(ItemRecipeCapability.CAP).stream()
//                        .map(num -> (Ingredient) num.getContent())
//                        .map(Ingredient::getItems)
//                        .filter(itemStacks -> itemStacks.length > 0)
//                        .map(itemStacks -> itemStacks[0])
//                        .toList();

                IItemHandler inputItems = digester.getItemHandlerCap(null, false);


                // 2. 遍历输入槽，收集所有有效食物
                List<ItemStack> foods = new ArrayList<>();
                for (int i = 0; i < inputItems.getSlots(); i++) {
                    ItemStack stack = inputItems.getStackInSlot(i);
                    if (!stack.isEmpty() && stack.isEdible()) {
                        foods.add(stack);
                    }
                }

                for (ItemStack stack : foods) {
                    totalNutrition += getFoodNutritionValue(stack) * stack.getCount() * 2; // 营养值乘以2
                }


                if(original.data.getInt("circuit") == 0)
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

                    if (newOutputs.isEmpty()) {
                        return null;
                    }

                    return GTRecipeBuilder.ofRaw()
                            .inputItems(foods.toArray(new ItemStack[0]))
                            .outputItems(newOutputs.toArray(new ItemStack[0]))
                            .duration((int)totalNutrition) // 持续时间基于总营养值
                            .EUt(32)
                            .buildRawRecipe();
                }
                else {
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
