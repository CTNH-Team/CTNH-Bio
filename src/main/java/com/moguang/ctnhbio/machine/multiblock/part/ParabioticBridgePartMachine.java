package com.moguang.ctnhbio.machine.multiblock.part;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import com.moguang.ctnhbio.api.capability.recipe.CogniItemRecipeCapability;
import com.moguang.ctnhbio.machine.multiblock.CogniAssemblerMachine;
import com.moguang.ctnhbio.utils.MetaMachineUtils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class ParabioticBridgePartMachine extends TieredIOPartMachine {

    @Persisted
    private final ParabioticBridgeHandler inventory;

    @Persisted
    private ResourceLocation lastInputRecipeID;

    // @Getter
    // @Persisted
    // private ResourceLocation lastOutputRecipeID;
    @Persisted
    private final List<BlockPos> lastOutput = new ArrayList<>();

    public ParabioticBridgePartMachine(IMachineBlockEntity holder) {
        super(holder, GTValues.ZPM, IO.BOTH);
        this.inventory = new ParabioticBridgeHandler(this);
    }

    public void updateLastOutput(GTRecipe recipe) {
        getControllers().stream().filter(
                m -> m instanceof IRecipeLogicMachine recipeLogicMachine &&
                        recipeLogicMachine.getRecipeLogic().getLastRecipe() != null &&
                        recipeLogicMachine.getRecipeLogic().getLastRecipe().id.equals(recipe.id))
                .forEach(p -> {
                    var pos = p.self().getPos();
                    if (!lastOutput.contains(pos)) lastOutput.add(pos);
                });
    }

    public ItemStack insertItemInternal(int slot, @NotNull ItemStack stack, boolean simulate) {
        return inventory.insertItemInternal(slot, stack, simulate);
    }

    @Override
    public @NotNull List<RecipeHandlerList> getRecipeHandlers() {
        return MetaMachineUtils.getRecipeHandlers(this, inventory);
    }

    public class ParabioticBridgeHandler extends NotifiableItemStackHandler {

        public ParabioticBridgeHandler(MetaMachine machine) {
            super(machine, 1, IO.BOTH, IO.NONE);
        }

        @Override
        public RecipeCapability<Ingredient> getCapability() {
            return CogniItemRecipeCapability.CAP;
        }

        @Override
        public List<Ingredient> handleRecipeInner(IO io, GTRecipe recipe, List<Ingredient> left, boolean simulate) {
            if (io == IO.IN) {
                if (getControllers().stream().filter(
                        m -> m instanceof CogniAssemblerMachine machine &&
                                machine.tryingRecipe != null &&
                                machine.tryingRecipe.id.equals(recipe.id))
                        .anyMatch(p -> lastOutput.contains(p.self().getPos())))
                    return left;
                List<Ingredient> result = handleRecipe(io, recipe, left, simulate, io, storage);
                if (!(Objects.equals(result, left)))
                    lastInputRecipeID = recipe.id;
                return result;
            } else {
                if (recipe.id.equals(lastInputRecipeID)) return left;
                List<Ingredient> result = handleRecipe(io, recipe, left, simulate, io, storage);
                if (!(Objects.equals(result, left)))
                    updateLastOutput(recipe);
                return result;
            }
        }
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 34, 34);
        var container = new WidgetGroup(4, 4, 26, 26);

        container.addWidget(
                new SlotWidget(getInventory().storage, 0, 4, 4, false, false)
                        .setBackgroundTexture(GuiTextures.SLOT));

        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);

        return group;
    }
}
