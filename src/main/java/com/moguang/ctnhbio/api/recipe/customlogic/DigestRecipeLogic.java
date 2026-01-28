package com.moguang.ctnhbio.api.recipe.customlogic;

import com.github.elenterius.biofactory.init.ModFluids;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.common.item.IntCircuitBehaviour;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Prefix;

import java.util.ArrayList;
import java.util.List;

@Prefix("recipe_logic")
public class DigestRecipeLogic implements GTRecipeType.ICustomRecipeLogic {

    @Override
    public @Nullable GTRecipe createCustomRecipe(IRecipeCapabilityHolder holder) {
        var itemInputs = holder.getCapabilitiesFlat(IO.IN, ItemRecipeCapability.CAP).stream()
                .filter(IItemHandlerModifiable.class::isInstance)
                .map(IItemHandlerModifiable.class::cast)
                .toArray(IItemHandlerModifiable[]::new);

        var inputItems = new CombinedInvWrapper(itemInputs);
        List<ItemStack> foods = new ArrayList<>();
        int circuit = 0;

        for (int i = 0; i < inputItems.getSlots(); i++) {
            ItemStack stack = inputItems.getStackInSlot(i);
            if (!stack.isEmpty() && stack.isEdible()) {
                foods.add(stack);
            }
            circuit = IntCircuitBehaviour.getCircuitConfiguration(stack);
        }
        if (foods.isEmpty() || circuit == 0) {
            return null;
        }

        float totalNutrition = 0.0f;
        for (ItemStack stack : foods) {
            totalNutrition += getFoodNutritionValue(stack) * stack.getCount() * 2; // 营养值乘以2
        }

        if(circuit == 1){
            int totalPaste = Math.round(totalNutrition / 3);
            int barCount = totalPaste / 9;
            int remainingPaste = totalPaste % 9;
            // 创建输出列表
            List<ItemStack> newOutputs = new ArrayList<>();
            if (barCount > 0) {
                newOutputs.add(new ItemStack(ModItems.NUTRIENT_BAR.get(), barCount));
            }
            if (remainingPaste > 0) {
                newOutputs.add(new ItemStack(ModItems.NUTRIENT_PASTE.get(), remainingPaste));
            }

            return CBRecipeBuilder.of(CTNHBio.id("nutrient_solid"), CBRecipeTypes.DIGEST_RECIPES)
                    .nutrient(1)
                    .inputItems(foods.stream().map(SizedIngredient::create).toArray(SizedIngredient[]::new))
                    .outputItems(newOutputs.toArray(new ItemStack[0]))
                    .duration((int)totalNutrition) // 持续时间基于总营养值
                    .EUt(32)
                    .buildRawRecipe();
        }
        else if(circuit == 2){
            int fluidAmount = Math.round(totalNutrition); // 1营养值 = 1mB流体
            return CBRecipeBuilder.of(CTNHBio.id("nutrient_fluid"), CBRecipeTypes.DIGEST_RECIPES)
                    .nutrient(1)
                    .inputItems(foods.stream().map(SizedIngredient::create).toArray(SizedIngredient[]::new))
                    .outputFluids(new FluidStack(ModFluids.NUTRIENTS_FLUID.get(), fluidAmount))
                    .duration((int) totalNutrition)
                    .EUt(32)
                    .buildRawRecipe();
        }

        return null;
    }

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

    @CN("任意食物")
    @EN("Any food")
    static Lang any_food;

    @CN("配方时间和产出\n由食物的饱食度和饱和度决定")
    @EN("Recipe duration and output\nare determined by the food’s nutrition and saturation")
    public static Lang based_on_nutrition;

    @Override
    public void buildRepresentativeRecipes() {
        var food = Items.BREAD.getDefaultInstance();
        food.setHoverName(any_food.translate());
        var bar = ModItems.NUTRIENT_BAR.get().getDefaultInstance();
        var paste = ModItems.NUTRIENT_PASTE.get().getDefaultInstance();
        var fluid = new FluidStack(ModFluids.NUTRIENTS_FLUID.get(), 1);

        var recipe1 = CBRecipeBuilder.of(CTNHBio.id("nutrient_solid"), CBRecipeTypes.DIGEST_RECIPES)
                .nutrient(1)
                .inputItems(food)
                .circuitMeta(1)
                .outputItems(bar, paste)
                .duration(100)
                .EUt(32)
                .addData("info", true)
                .buildRawRecipe();

        var recipe2 = CBRecipeBuilder.of(CTNHBio.id("nutrient_fluid"), CBRecipeTypes.DIGEST_RECIPES)
                .nutrient(1)
                .inputItems(food)
                .circuitMeta(2)
                .outputFluids(fluid)
                .duration(100)
                .EUt(32)
                .addData("info", true)
                .buildRawRecipe();

        CBRecipeTypes.DIGEST_RECIPES.addToMainCategory(recipe1);
        CBRecipeTypes.DIGEST_RECIPES.addToMainCategory(recipe2);
    }
}
