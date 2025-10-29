package com.moguang.ctnhbio.machine.multiblock;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.recipe.ActionResult;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
import com.moguang.ctnhbio.data.recipe.CogniRecipeBuilder;
import com.moguang.ctnhbio.machine.multiblock.part.ParabioticBridgePartMachine;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

public class CogniAssemblerMachine extends WorkableLivingMultiblockMachine {
    public CogniAssemblerMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    public GTRecipe tryingRecipe;

    public static Comparator<IMultiPart> partSorter(MultiblockControllerMachine mc) {
        return Comparator.comparingInt(part ->
                part instanceof ParabioticBridgePartMachine ? 0 : 1  // 优先类实例排前面
        );
    }

    @Override
    protected CogniAssemblerRecipeLogic createRecipeLogic(Object... args) {
        return new CogniAssemblerRecipeLogic(this);
    }

    @Override
    public CogniAssemblerRecipeLogic getRecipeLogic() {
        return (CogniAssemblerRecipeLogic)recipeLogic;
    }

//    public static boolean isCogniAssemble(@Nullable GTRecipe recipe)
//    {
//        return recipe != null && recipe.recipeType == CBRecipeTypes.COGNI_ASSEMBLY_STEP;
//    }
//
//    public static boolean isFirstStep(@Nullable GTRecipe recipe)
//    {
//        return recipe != null && recipe.recipeType == CBRecipeTypes.COGNI_ASSEMBLY_STEP && recipe.data.contains(CogniRecipeBuilder.COGNI_ASSEMBLE_FIRST_STEP);
//    }
//
//    public static boolean isLastStep(@Nullable GTRecipe recipe)
//    {
//        return recipe != null && recipe.recipeType == CBRecipeTypes.COGNI_ASSEMBLY_STEP && recipe.data.contains(CogniRecipeBuilder.COGNI_ASSEMBLE_LAST_STEP);
//    }
//
//    public long getPBPartCount()
//    {
//        return getParts().stream()
//                .filter(m -> m instanceof ParabioticBridgePartMachine)
//                .count();
//    }

//    @Override
//    public boolean beforeWorking(@Nullable GTRecipe recipe) {
//        if(isFirstStep(recipe))
//        {
//            return getPBPartCount() == 1 && super.beforeWorking(recipe);
//        }
//        else if (isCogniAssemble(recipe) && !isLastStep(recipe))
//        {
//            return getPBPartCount() == 2 && super.beforeWorking(recipe);
//        }
//        return super.beforeWorking(recipe);
//    }

    public static class CogniAssemblerRecipeLogic extends BasicLivingMachine.BasicLivingRecipeLogic {
        public CogniAssemblerRecipeLogic(IRecipeLogicMachine machine) {
            super(machine);
        }

//        @Override
//        protected ActionResult matchRecipe(GTRecipe recipe) {
//            if(!isCogniAssemble(recipe) || isLastStep(recipe))
//            {
//                return super.matchRecipe(recipe);
//            }
//
//            var match = matchRecipeNoOutput(recipe);
//            if (!match.isSuccess()) return match;
//
//            return matchTickRecipeNoOutput(recipe);
//        }

        @Override
        public CogniAssemblerMachine getMachine() {
            return (CogniAssemblerMachine)super.getMachine();
        }

        public boolean checkAvailableBridge()
        {
            return  getMachine().getParts().stream().anyMatch(
                            m -> m instanceof ParabioticBridgePartMachine machine
                                    && !machine.getLastOutput().contains(this.getMachine().self().getPos()))
                    ;
        }

        @Override
        protected ActionResult handleRecipeIO(GTRecipe recipe, IO io) {
            if(io == IO.IN) {
                getMachine().tryingRecipe = recipe;
            }
            return super.handleRecipeIO(recipe, io);
        }

//        protected ActionResult matchRecipeNoOutput(GTRecipe recipe) {
//            if (!machine.hasCapabilityProxies()) return ActionResult.FAIL_NO_CAPABILITIES;
//            return RecipeHelper.handleRecipe(machine, recipe, IO.IN, recipe.inputs, Collections.emptyMap(), false,
//                    true);
//        }
//
//        protected ActionResult matchTickRecipeNoOutput(GTRecipe recipe) {
//            if (recipe.hasTick()) {
//                if (!machine.hasCapabilityProxies()) return ActionResult.FAIL_NO_CAPABILITIES;
//                return RecipeHelper.handleRecipe(machine, recipe, IO.IN, recipe.tickInputs, Collections.emptyMap(),
//                        false, true);
//            }
//            return ActionResult.SUCCESS;
//        }
    }
}
