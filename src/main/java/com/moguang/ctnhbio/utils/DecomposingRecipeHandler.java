package com.moguang.ctnhbio.utils;

import com.github.elenterius.biomancy.crafting.ItemCountRange;
import com.github.elenterius.biomancy.crafting.recipe.DecomposingRecipe;
import com.google.gson.JsonObject;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.IntProviderIngredient;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import com.mojang.datafixers.util.Either;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.github.elenterius.biomancy.init.ModItems.*;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;

public class DecomposingRecipeHandler {
    private static final int FLUID_RATE = 25;

    private static final Set<Item> REMOVE_ITEMS = Set.of(
            MINERAL_FRAGMENT.get(),
            GEM_FRAGMENTS.get(),
            STONE_POWDER.get(),
            NUTRIENTS.get()
    );

    private static final Map<Item, Fluid> FLUID_CONVERSIONS = Map.of(
            REGENERATIVE_FLUID.get(), Regenerative_Fluid.getFluid(),
            WITHERING_OOZE.get(), Withering_Ooze.getFluid(),
            HORMONE_SECRETION.get(), Hormone_Secretion.getFluid(),
            TOXIN_EXTRACT.get(), Toxin_Extract.getFluid(),
            BILE.get(), Bile.getFluid(),
            VOLATILE_FLUID.get(), Volatile_Fluid.getFluid()
    );

    public static GTRecipe toGTrecipe(DecomposingRecipe recipe){
        var outputs = extractOutputs(recipe);
        if(outputs.isEmpty()) return null;

        var builder = CBRecipeBuilder.of(CTNHBio.id(recipe.getId().getPath()), CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(recipe.getCraftingCostNutrients(null))
                .duration(recipe.getCraftingTimeTicks(null));
        AtomicBoolean hasFluid = new AtomicBoolean(false);
        builder.inputItems(recipe.getIngredients().get(0));
        for(var output:outputs){
            output.min = Math.max(output.min, 1);
            output.ingredient.ifLeft(item -> {
                if(output.max > 0){
                    builder.outputItemRanged(IntProviderIngredient.of(item.getDefaultInstance(), UniformInt.of(output.min, output.max)));
                }
                else {
                    builder.outputItems(new ItemStack(item, output.count));
                }
            }).ifRight(fluid -> {
                hasFluid.set(true);
                if(output.max > 0){
                    builder.outputFluidsRanged(new FluidStack(fluid, 1), UniformInt.of(output.min * FLUID_RATE, output.max * FLUID_RATE));
                }
                else {
                    builder.outputFluids(new FluidStack(fluid, output.count * FLUID_RATE));
                }
            });
        }
        builder.EUt(hasFluid.get() ? VA[MV] : VA[LV]);
        JsonObject js = builder.build().serializeRecipe();

        try {
            java.nio.file.Path outputPath = java.nio.file.Paths.get("resources/data/ctnhbio/recipes/decomposing/" + recipe.getId().getPath() + ".json");
            java.nio.file.Files.createDirectories(outputPath.getParent());
            com.google.gson.Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
            String prettyJson = gson.toJson(js);
            java.nio.file.Files.writeString(outputPath, prettyJson);
        } catch (Exception e) {

        }
        return builder.buildRawRecipe();
    }

    private static List<OutputData> extractOutputs(DecomposingRecipe recipe) {
        var outputs = recipe.getOutputs();
        outputs.removeIf(i -> REMOVE_ITEMS.contains(i.getItem()));
        List<OutputData> list = new ArrayList<>();
        for(var output:outputs){
            OutputData data = new OutputData();
            if(FLUID_CONVERSIONS.containsKey(output.getItem())){
                var fluid = FLUID_CONVERSIONS.get(output.getItem());
                data.ingredient = Either.right(fluid);
            } else {
                data.ingredient = Either.left(output.getItem());
            }

            var range = output.getCountRange();
            if(range instanceof ItemCountRange.ConstantValue constantValue){
                data.count = constantValue.value();
            } else if(range instanceof ItemCountRange.UniformRange uniformRange){
                data.min = uniformRange.min();
                data.max = uniformRange.max();
            } else if(range instanceof ItemCountRange.BinomialRange binomialRange){
                data.max = binomialRange.n();
            }

            list.add(data);
        }
        return list;
    }

    private static class OutputData {
        Either<Item, Fluid> ingredient;
        int count = 0;
        int min = 0;
        int max = 0;
    }
}
