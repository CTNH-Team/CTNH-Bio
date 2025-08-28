package com.moguang.ctnhbio.data.recipe;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import lombok.Setter;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CogniRecipeBuilder extends GTRecipeBuilder {

    public static final String COGNI_ASSEMBLE_FIRST_STEP = "cogni_assemble_first";
    public static final String COGNI_ASSEMBLE_LAST_STEP = "cogni_assemble_last";
    public static final String COGNI_ASSEMBLE_INTERMEDIATE = "cogni_assemble_intermediate";


    private final List<SubRecipe> subRecipes = new ArrayList<>();
    private ItemStack intermediateItem;
    private ItemStack finalOutput;
    private int currentStep = 0;
    private final GTRecipeType mainRecipeType;
    private final GTRecipeType subRecipeType;
    private long eut;

    public CogniRecipeBuilder(ResourceLocation id, GTRecipeType mainRecipeType, GTRecipeType subRecipeType) {
        super(id, mainRecipeType);
        this.mainRecipeType = mainRecipeType;
        this.subRecipeType = subRecipeType;
    }

    public static CogniRecipeBuilder start(ResourceLocation id, GTRecipeType mainRecipeType, GTRecipeType subRecipeType) {
        return new CogniRecipeBuilder(id, mainRecipeType, subRecipeType);
    }

    @Override
    public CogniRecipeBuilder EUt(long eu) {
        this.eut = eu;
        return this;
    }

    @Override
    public @NotNull CogniRecipeBuilder duration(int duration) {
        this.duration = duration;
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
        currentStep++;
        return this;
    }

    @Override
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
                stepBuilder.inputItems(copyWithStep(intermediateItem, step));
            }
            else {
                stepBuilder.addData(COGNI_ASSEMBLE_FIRST_STEP, true);
            }

            // 添加配方特定输入
            stepBuilder.inputItems(currentRecipe.itemInputs.toArray(Ingredient[]::new))
                    .inputFluids(currentRecipe.fluidInputs.toArray(FluidStack[]::new));

            // 设置输出
            if (step == subRecipes.size() - 1) {
                // 最后一步输出最终产物
                stepBuilder.outputItems(finalOutput).addData(COGNI_ASSEMBLE_LAST_STEP, true);
            } else {
                // 中间步骤输出带标记的中间产物
                stepBuilder.outputItems(copyWithStep(intermediateItem, step + 1));
            }

            stepBuilder.save(consumer);
        }
    }

    private void createMainRecipe(Consumer<FinishedRecipe> consumer) {
        // 收集所有输入
        List<Ingredient> allItemInputs = new ArrayList<>();
        List<FluidStack> allFluidInputs = new ArrayList<>();

        for (var subRecipe : subRecipes) {
            allItemInputs.addAll(subRecipe.itemInputs);
            allFluidInputs.addAll(subRecipe.fluidInputs);
        }

        // 创建主配方
        GTRecipeBuilder mainBuilder = GTRecipeBuilder.of(id, mainRecipeType)
                .inputItems(allItemInputs.toArray(Ingredient[]::new))
                .inputFluids(allFluidInputs.toArray(FluidStack[]::new))
                .outputItems(finalOutput) // 使用指定的最终产物
                .EUt(eut)
                .duration(this.duration * subRecipes.size()); // 总时间为各步骤时间之和

        mainBuilder.save(consumer);
    }

    private ItemStack copyWithStep(ItemStack stack, int step) {
        ItemStack copy = stack.copy();
        CompoundTag tag = copy.getOrCreateTag();
        tag.putInt("cogin_assemble_step", step);
        tag.putBoolean(COGNI_ASSEMBLE_INTERMEDIATE, true);
        return copy;
    }


    public static class SubRecipe {
        private final CogniRecipeBuilder parent;
        private final List<Ingredient> itemInputs = new ArrayList<>();
        private final List<FluidStack> fluidInputs = new ArrayList<>();

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

        public SubRecipe inputFluids(FluidStack... fluids) {
            fluidInputs.addAll(List.of(fluids));
            return this;
        }

        public CogniRecipeBuilder endStep() {
            return parent;
        }
    }
}