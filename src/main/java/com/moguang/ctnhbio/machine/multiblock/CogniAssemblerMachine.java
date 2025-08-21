package com.moguang.ctnhbio.machine.multiblock;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.ActionResult;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
import com.moguang.ctnhbio.data.recipe.CogniRecipeBuilder;
import com.moguang.ctnhbio.machine.multiblock.part.ParabioticBridgePartMachine;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CogniAssemblerMachine extends WorkableLivingMultiblockMachine {
    public CogniAssemblerMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    protected CogniAssemblerRecipeLogic createRecipeLogic(Object... args) {
        return new CogniAssemblerRecipeLogic(this);
    }

    @Override
    public CogniAssemblerRecipeLogic getRecipeLogic() {
        return (CogniAssemblerRecipeLogic)recipeLogic;
    }

    public static boolean isCogniAssemble(@Nullable GTRecipe recipe)
    {
        return recipe != null && recipe.recipeType == CBRecipeTypes.COGNI_ASSEMBLE_STEP;
    }

    public static boolean isFirstStep(@Nullable GTRecipe recipe)
    {
        return recipe != null && recipe.recipeType == CBRecipeTypes.COGNI_ASSEMBLE_STEP && recipe.data.contains(CogniRecipeBuilder.COGNI_ASSEMBLE_FIRST_STEP);
    }

    public static boolean isLastStep(@Nullable GTRecipe recipe)
    {
        return recipe != null && recipe.recipeType == CBRecipeTypes.COGNI_ASSEMBLE_STEP && recipe.data.contains(CogniRecipeBuilder.COGNI_ASSEMBLE_LAST_STEP);
    }

    public long getPBPartCount()
    {
        return getParts().stream()
                .filter(m -> m instanceof ParabioticBridgePartMachine)
                .count();
    }

    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        if(isFirstStep(recipe))
        {
            return getPBPartCount() == 1 && super.beforeWorking(recipe);
        }
        else if (isCogniAssemble(recipe) && !isLastStep(recipe))
        {
            return getPBPartCount() == 2 && super.beforeWorking(recipe);
        }
        return super.beforeWorking(recipe);
    }

    public static class CogniAssemblerRecipeLogic extends BasicLivingMachine.BasicLivingRecipeLogic {
        public CogniAssemblerRecipeLogic(IRecipeLogicMachine machine) {
            super(machine);
        }

        @Override
        protected ActionResult matchRecipe(GTRecipe recipe) {
            return super.matchRecipe(recipe);
//            if(!isCogniAssemble(recipe) || isLastStep(recipe))
//            {
//                return super.matchRecipe(recipe);
//            }
//
//            var match = matchRecipeNoOutput(recipe);
//            if (!match.isSuccess()) return match;
//
//            return matchTickRecipeNoOutput(recipe);
        }

        @Override
        public CogniAssemblerMachine getMachine() {
            return (CogniAssemblerMachine)super.getMachine();
        }

        public ParabioticBridgePartMachine getOutputPart(GTRecipe recipe)
        {
            return  getMachine().getParts().stream().filter(
                            m -> m instanceof ParabioticBridgePartMachine machine
                                    && machine.getLastRecipeID() != recipe.id)
                    .map(m -> (ParabioticBridgePartMachine)m)
                    .findFirst()
                    .orElse(null);
        }

//        @Override
//        protected ActionResult handleRecipeIO(GTRecipe recipe, IO io) {
//            if(io == IO.OUT && isCogniAssemble(recipe)) {
//                if (lastRecipe == null) {
//                    return ActionResult.SUCCESS;
//                }
//            }
//            return super.handleRecipeIO(recipe, io);
//        }

        protected ActionResult matchRecipeNoOutput(GTRecipe recipe) {
            if (!machine.hasCapabilityProxies()) return ActionResult.FAIL_NO_CAPABILITIES;
            return RecipeHelper.handleRecipe(machine, recipe, IO.IN, recipe.inputs, Collections.emptyMap(), false,
                    true);
        }

        protected ActionResult matchTickRecipeNoOutput(GTRecipe recipe) {
            if (recipe.hasTick()) {
                if (!machine.hasCapabilityProxies()) return ActionResult.FAIL_NO_CAPABILITIES;
                return RecipeHelper.handleRecipe(machine, recipe, IO.IN, recipe.tickInputs, Collections.emptyMap(),
                        false, true);
            }
            return ActionResult.SUCCESS;
        }
    }
}
