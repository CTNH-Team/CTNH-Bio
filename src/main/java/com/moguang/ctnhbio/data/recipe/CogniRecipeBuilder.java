package com.moguang.ctnhbio.data.recipe;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.IntProviderIngredient;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import net.minecraftforge.fluids.FluidStack;

import com.moguang.ctnhbio.api.capability.recipe.CogniItemRecipeCapability;
import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CogniRecipeBuilder {

    public static final String COGNI_AESSEMBLY_STEP = "cogin_assembly_step";

    private final List<SubRecipe> subRecipes = new ArrayList<>();
    private ItemStack intermediateItem;
    private ItemStack finalOutput;

    int minIntermediate = 0;
    int maxIntermediate = 0;

    private final GTRecipeType mainRecipeType;
    private final GTRecipeType subRecipeType;
    private long eut;
    private ResourceLocation id;
    private int duration;

    public CogniRecipeBuilder(ResourceLocation id, GTRecipeType mainRecipeType, GTRecipeType subRecipeType) {
        this.id = id;
        this.mainRecipeType = mainRecipeType;
        this.subRecipeType = subRecipeType;
    }

    public static CogniRecipeBuilder start(ResourceLocation id, GTRecipeType mainRecipeType,
                                           GTRecipeType subRecipeType) {
        return new CogniRecipeBuilder(id, mainRecipeType, subRecipeType);
    }

    public CogniRecipeBuilder EUt(long eu) {
        this.eut = eu;
        return this;
    }

    public @NotNull CogniRecipeBuilder duration(int duration) {
        this.duration = duration;
        return this;
    }

    public CogniRecipeBuilder setIntermediate(ItemStack item, int min, int max) {
        this.intermediateItem = item;
        minIntermediate = min;
        maxIntermediate = max;
        return this;
    }

    public CogniRecipeBuilder setIntermediate(ItemStack item) {
        this.intermediateItem = item;

        return this;
    }

    public CogniRecipeBuilder setIntermediate(Item item) {
        return setIntermediate(new ItemStack(item));
    }

    public CogniRecipeBuilder setFinalOutput(ItemStack output) {
        this.finalOutput = output;
        return this;
    }

    public CogniRecipeBuilder setFinalOutput(Item item) {
        return setFinalOutput(new ItemStack(item));
    }

    public CogniRecipeBuilder addStep(Consumer<SubRecipe> recipeConsumer) {
        SubRecipe subRecipe = new SubRecipe(this);
        recipeConsumer.accept(subRecipe);
        subRecipes.add(subRecipe);

        return this;
    }

    public CogniRecipeBuilder MIFStep(ModelTier requiredTier, EntityType<?> type,
                                      Supplier<? extends Item> input, int amount,
                                      FluidStack fluids) {
        SubRecipe subRecipe = new SubRecipe(this);
        subRecipe.inputItems(input, amount)
                .inputFluids(fluids)
                .inputModel(requiredTier, type);
        subRecipes.add(subRecipe);

        return this;
    }

    public CogniRecipeBuilder MIFStep(ModelTier requiredTier, EntityType<?> type,
                                      ItemStack stack,
                                      FluidStack fluids) {
        SubRecipe subRecipe = new SubRecipe(this);
        subRecipe.inputItems(stack)
                .inputFluids(fluids)
                .inputModel(requiredTier, type);
        subRecipes.add(subRecipe);

        return this;
    }

    public CogniRecipeBuilder IFStep(Supplier<? extends Item> input, int amount,
                                     FluidStack fluids) {
        SubRecipe subRecipe = new SubRecipe(this);
        subRecipe.inputItems(input, amount)
                .inputFluids(fluids);
        subRecipes.add(subRecipe);

        return this;
    }

    public CogniRecipeBuilder IFStep(ItemStack stack,
                                     FluidStack fluids) {
        SubRecipe subRecipe = new SubRecipe(this);
        subRecipe.inputItems(stack)
                .inputFluids(fluids);
        subRecipes.add(subRecipe);

        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer) {
        if (subRecipes.isEmpty()) {
            GTCEu.LOGGER.error("Pipeline recipe {} has no sub recipes!", id);
            return;
        }

        if (intermediateItem == null || intermediateItem.isEmpty()) {
            GTCEu.LOGGER.error("Pipeline recipe {} has no intermediate item set!", id);
            return;
        }

        if (finalOutput == null || finalOutput.isEmpty()) {
            GTCEu.LOGGER.error("Pipeline recipe {} has no final output set!", id);
            return;
        }
        UniformInt random = null;
        if (minIntermediate != 0) {
            random = UniformInt.of(minIntermediate, maxIntermediate);
        }
        // 创建主配方（用于JEI显示）
        createMainRecipe(consumer);

        // 构建所有子配方
        for (int step = 0; step < subRecipes.size(); step++) {
            SubRecipe currentRecipe = subRecipes.get(step);
            String stepName = (step == subRecipes.size() - 1) ? "_final_step" : "_step_" + (step + 1);

            GTRecipeBuilder stepBuilder = GTRecipeBuilder.of(
                    ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath() + stepName),
                    subRecipeType)
                    .EUt(eut)
                    .duration(this.duration);

            // 添加中间产物输入（除第一步外）
            if (step > 0) {
                Ingredient ingredient;
                if (random == null) {
                    ingredient = StrictNBTIngredient.of(copyWithStep(intermediateItem, step));
                } else {
                    ingredient = IntProviderIngredient.of(copyWithStep(intermediateItem, step), random);
                }
                stepBuilder.input(CogniItemRecipeCapability.CAP, ingredient);
            }

            // 添加配方特定输入
            stepBuilder.inputItems(currentRecipe.itemInputs.toArray(Ingredient[]::new))
                    .inputFluids(currentRecipe.fluidInputs.toArray(FluidIngredient[]::new))
            // .outputItems(currentRecipe.itemOutputs.toArray(Ingredient[]::new))
            // .outputFluids(currentRecipe.fluidOutputs.toArray(FluidIngredient[]::new))
            ;
            if (!currentRecipe.modelInputs.isEmpty())
                stepBuilder.input(ModelRecipeCapability.CAP, currentRecipe.modelInputs.get(0));

            // 设置输出
            if (step == subRecipes.size() - 1) {
                // 最后一步输出最终产物
                stepBuilder.outputItems(finalOutput);
            } else {
                // 中间步骤输出带标记的中间产物
                // stepBuilder.outputItems(copyWithStep(intermediateItem, step + 1));
                Ingredient ingredient;
                if (random == null) {
                    ingredient = StrictNBTIngredient.of(copyWithStep(intermediateItem, step + 1));
                } else {
                    ingredient = IntProviderIngredient.of(copyWithStep(intermediateItem, step + 1), random);
                }

                stepBuilder.output(CogniItemRecipeCapability.CAP, ingredient);
            }

            stepBuilder.save(consumer);
        }
    }

    private void createMainRecipe(Consumer<FinishedRecipe> consumer) {
        // 收集所有输入
        List<Ingredient> allItemInputs = new ArrayList<>();
        List<FluidIngredient> allFluidInputs = new ArrayList<>();
        List<Ingredient> allItemOutputs = new ArrayList<>();
        List<FluidIngredient> allFluidOutputs = new ArrayList<>();
        List<ModelIngredient> allModels = new ArrayList<>();

        for (var subRecipe : subRecipes) {
            allItemInputs.addAll(subRecipe.itemInputs);
            allFluidInputs.addAll(subRecipe.fluidInputs);
            allItemOutputs.addAll(subRecipe.itemOutputs);
            allFluidOutputs.addAll(subRecipe.fluidOutputs);
            allModels.addAll(subRecipe.modelInputs);
        }

        // 创建主配方
        GTRecipeBuilder mainBuilder = GTRecipeBuilder.of(id, mainRecipeType)
                .inputItems(allItemInputs.toArray(Ingredient[]::new))
                .inputFluids(allFluidInputs.toArray(FluidIngredient[]::new))
                .outputItems(finalOutput) // 先输出最终产物
                .outputItems(allItemOutputs.toArray(Ingredient[]::new))

                .outputFluids(allFluidOutputs.toArray(FluidIngredient[]::new))
                .EUt(eut)
                .duration(this.duration * subRecipes.size()); // 总时间为各步骤时间之和
        for (var model : allModels) {
            mainBuilder.input(ModelRecipeCapability.CAP, model);
        }

        mainBuilder.save(consumer);
    }

    private ItemStack copyWithStep(ItemStack stack, int step) {
        ItemStack copy = stack.copy();
        CompoundTag tag = copy.getOrCreateTag();
        tag.putInt(COGNI_AESSEMBLY_STEP, step);
        // tag.putBoolean(COGNI_ASSEMBLE_INTERMEDIATE, true);
        return copy;
    }

    public static class SubRecipe {

        private final CogniRecipeBuilder parent;
        private final List<Ingredient> itemInputs = new ArrayList<>();
        private final List<ModelIngredient> modelInputs = new ArrayList<>();
        private final List<FluidIngredient> fluidInputs = new ArrayList<>();
        private final List<Ingredient> itemOutputs = new ArrayList<>();
        private final List<FluidIngredient> fluidOutputs = new ArrayList<>();

        public SubRecipe(CogniRecipeBuilder parent) {
            this.parent = parent;
        }

        public SubRecipe inputItems(Supplier<? extends Item> input) {
            this.inputItems(new ItemStack(input.get(), 1));
            return this;
        }

        public SubRecipe inputItems(Supplier<? extends Item> input, int amount) {
            this.inputItems(new ItemStack(input.get(), amount));
            return this;
        }

        public SubRecipe inputItems(ItemStack... items) {
            for (ItemStack stack : items) {
                itemInputs.add(SizedIngredient.create(stack));
            }
            return this;
        }

        public SubRecipe inputItems(Ingredient... ingredients) {
            itemInputs.addAll(List.of(ingredients));
            return this;
        }

        public SubRecipe inputModel(ModelIngredient... models) {
            modelInputs.addAll(Arrays.asList(models));
            return this;
        }

        public SubRecipe inputModel(ModelTier requiredTier, EntityType<?> type) {
            inputModel(ModelIngredient.of(requiredTier, type));
            return this;
        }

        public SubRecipe inputFluids(FluidStack... fluids) {
            fluidInputs.add(FluidIngredient.of(List.of(fluids)));
            return this;
        }

        public SubRecipe outputItems(Supplier<? extends Item> output) {
            return this.outputItems(new ItemStack(output.get(), 1));
        }

        public SubRecipe outputItems(Supplier<? extends Item> output, int amount) {
            return this.outputItems(new ItemStack(output.get(), amount));
        }

        public SubRecipe outputItems(ItemStack... items) {
            for (ItemStack stack : items) {
                itemOutputs.add(SizedIngredient.create(stack));
            }
            return this;
        }

        public SubRecipe outputItems(Ingredient... ingredients) {
            itemOutputs.addAll(List.of(ingredients));
            return this;
        }

        public SubRecipe outputFluids(FluidStack... fluids) {
            for (FluidStack fluid : fluids) {
                fluidOutputs.add(FluidIngredient.of(fluid));
            }
            return this;
        }

        public CogniRecipeBuilder endStep() {
            return parent;
        }
    }
}
