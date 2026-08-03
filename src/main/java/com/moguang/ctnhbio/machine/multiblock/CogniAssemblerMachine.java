package com.moguang.ctnhbio.machine.multiblock;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipeDefinition;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.resources.ResourceLocation;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
import com.moguang.ctnhbio.api.machine.multiblock.WorkableLivingMultiblockMachine;
import com.moguang.ctnhbio.data.recipe.CogniRecipeBuilder;
import com.moguang.ctnhbio.machine.multiblock.part.ParabioticBridgePartMachine;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.Comparator;

public class CogniAssemblerMachine extends WorkableLivingMultiblockMachine {

    public CogniAssemblerMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    public static Comparator<IMultiPart> partSorter(MultiblockControllerMachine mc) {
        return Comparator.comparingInt(part -> part instanceof ParabioticBridgePartMachine ? 0 : 1);
    }

    @Override
    protected CogniAssemblerRecipeLogic createRecipeLogic(Object... args) {
        return new CogniAssemblerRecipeLogic(this);
    }

    public static class CogniAssemblerRecipeLogic extends RecipeLogic {

        @Persisted
        private ResourceLocation lastRecipeID;

        public CogniAssemblerRecipeLogic(IRecipeLogicMachine machine) {
            super(machine);
        }

        @Override
        public CogniAssemblerMachine getMachine() {
            return (CogniAssemblerMachine) super.getMachine();
        }

        @Override
        public void onRecipeFinish() {
            if (lastRecipe != null) lastRecipeID = lastRecipe.id;
            super.onRecipeFinish();
        }

        @CN("无法执行连续的意识装配步骤")
        @EN("Can not handle ")
        static Lang fail_to_handle;

        @Override
        public boolean checkMatchedRecipeAvailable(GTRecipeDefinition match, RecipeHandlerGroup group) {
            if (match.recipeType == CBRecipeTypes.COGNI_ASSEMBLY_STEP &&
                    CogniRecipeBuilder.isNextStep(lastRecipeID, match.id)) {
                failureReasonsMap.put(match.id, fail_to_handle.translate());
                return false;
            }
            return super.checkMatchedRecipeAvailable(match, group);
        }
    }
}
