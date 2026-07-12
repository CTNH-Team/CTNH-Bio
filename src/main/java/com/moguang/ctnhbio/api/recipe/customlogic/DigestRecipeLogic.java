package com.moguang.ctnhbio.api.recipe.customlogic;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipeDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.common.item.IntCircuitBehaviour;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.github.elenterius.biofactory.init.ModFluids;
import com.github.elenterius.biomancy.crafting.AnyFoodIngredient;
import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.ArrayList;
import java.util.List;

public class DigestRecipeLogic implements GTRecipeType.ICustomRecipeLogic {

    @Override
    public @Nullable GTRecipeDefinition createCustomRecipe(RecipeHandlerGroup holder) {
        var itemHandlers = holder.getInputHandlerMap().get(ItemRecipeCapability.CAP).stream()
                .filter(IItemHandlerModifiable.class::isInstance)
                .map(IItemHandlerModifiable.class::cast)
                .toArray(IItemHandlerModifiable[]::new);

        var inputItems = new CombinedInvWrapper(itemHandlers);
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
            totalNutrition += getFoodNutritionValue(stack) * stack.getCount() * 2;
        }

        if (circuit == 1) {
            int totalPaste = Math.round(totalNutrition / 3);
            int barCount = totalPaste / 9;
            int remainingPaste = totalPaste % 9;
            List<ItemStack> outputs = new ArrayList<>();
            if (barCount > 0) outputs.add(new ItemStack(ModItems.NUTRIENT_BAR.get(), barCount));
            if (remainingPaste > 0) outputs.add(new ItemStack(ModItems.NUTRIENT_PASTE.get(), remainingPaste));

            return CBRecipeBuilder.of(CTNHBio.id("nutrient_solid"), CBRecipeTypes.DIGEST_RECIPES)
                    .nutrient(1)
                    .inputItems(foods.toArray(ItemStack[]::new))
                    .outputItems(outputs.toArray(ItemStack[]::new))
                    .duration((int) totalNutrition)
                    .EUt(32)
                    .buildRawRecipe();
        } else if (circuit == 2) {
            return CBRecipeBuilder.of(CTNHBio.id("nutrient_fluid"), CBRecipeTypes.DIGEST_RECIPES)
                    .nutrient(1)
                    .inputItems(foods.toArray(ItemStack[]::new))
                    .outputFluids(new FluidStack(ModFluids.NUTRIENTS_FLUID.get(), Math.round(totalNutrition)))
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
            if (food != null) return food.getNutrition() + food.getSaturationModifier();
        }
        return 0.0f;
    }

    @CN("配方时间和产出\n由食物的饱食度和饱和度决定")
    @EN("Recipe duration and output\nare determined by the food’s Hunger Value and Saturation")
    public static Lang based_on_nutrition;

    @Override
    public void buildRepresentativeRecipes() {
        var bar = ModItems.NUTRIENT_BAR.get().getDefaultInstance();
        var paste = ModItems.NUTRIENT_PASTE.get().getDefaultInstance();
        var fluid = new FluidStack(ModFluids.NUTRIENTS_FLUID.get(), 1);

        var recipe1 = CBRecipeBuilder.of(CTNHBio.id("nutrient_solid"), CBRecipeTypes.DIGEST_RECIPES)
                .nutrient(1)
                .inputItems(new AnyFoodIngredient())
                .circuitMeta(1)
                .outputItems(bar, paste)
                .duration(100)
                .EUt(32)
                .addData("info", true)
                .buildRawRecipe();

        var recipe2 = CBRecipeBuilder.of(CTNHBio.id("nutrient_fluid"), CBRecipeTypes.DIGEST_RECIPES)
                .nutrient(1)
                .inputItems(new AnyFoodIngredient())
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
