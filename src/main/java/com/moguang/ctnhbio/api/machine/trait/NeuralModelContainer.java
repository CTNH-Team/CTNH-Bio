package com.moguang.ctnhbio.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.ICapabilityTrait;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.IChancedIngredient;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;

import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NeuralModelContainer extends NotifiableRecipeHandlerTrait<ModelIngredient>
                                  implements ICapabilityTrait, IItemHandlerModifiable {

    @Persisted
    @Getter
    @Setter
    private boolean isLocked = false;
    @Persisted
    public final CustomItemStackHandler storage;

    public NeuralModelContainer(MetaMachine machine, int inventorySize) {
        super(machine);
        storage = new CustomItemStackHandler(inventorySize){

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        };;
        storage.setFilter(stack -> stack.isEmpty() || stack.getItem() instanceof DataModelItem);
        storage.setOnContentsChanged(this::notifyListeners);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }

    @Override
    public int getSlots() {
        return storage.getSlots();
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        return storage.getStackInSlot(slot);
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return !isLocked ? storage.insertItem(slot, stack, simulate) : stack;
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        return !isLocked ? storage.extractItem(slot, amount, simulate) : ItemStack.EMPTY;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return !isLocked && storage.getFilter().test(stack);
    }

    @Override
    public RecipeCapability<ModelIngredient> getCapability() {
        return ModelRecipeCapability.CAP;
    }

    @Override
    public boolean handleRecipe(IO io, GTRecipe recipe, List<ModelIngredient> left, boolean simulate) {
        if(io == IO.IN) {
            for (var iterator = left.iterator(); iterator.hasNext();) {
                ModelIngredient ingredient = iterator.next();
                for(int slot = 0; slot < getSlots(); slot ++) {
                    if (ingredient.test(getStackInSlot(slot))) {
                        iterator.remove();
                        if(!simulate && !ingredient.toStack().isEmpty()) {
                            storage.setStackInSlot(slot, ItemStack.EMPTY);
                        }
                        break;
                    }
                }
            }
        }
        else {
            for (var iterator = left.iterator(); iterator.hasNext();) {
                ModelIngredient ingredient = iterator.next();
                var result = simulate ? ingredient.getItem() : ingredient.toStack();
                if(!simulate && result.isEmpty()) continue;
                for(int slot = 0; slot < getSlots(); slot ++) {
                    if(getStackInSlot(slot).isEmpty()) {
                        if(ingredient.getTier().ordinal() == 0) {
                            iterator.remove();
                            if(!simulate) setStackInSlot(slot, result);
                        }
                    } else {
                        if(ingredient.testHigher(getStackInSlot(slot))) {
                            iterator.remove();
                            if(!simulate) setStackInSlot(slot, result);
                        }
                    }
                }
            }
        }

        return left.isEmpty();
    }

    @Override
    public @NotNull List<Object> getContents() {
        List<Object> stacks = new ArrayList<>();
        for(int slot = 0; slot < getSlots(); slot ++) {
            if(!getStackInSlot(slot).isEmpty()) {
                stacks.add(getStackInSlot(slot));
            }
        }
        return stacks;
    }

    @Override
    public double getTotalContentAmount() {
        return getContents().size();
    }

    @Override
    public IO getHandlerIO() {
        return IO.BOTH;
    }

    @Override
    public IO getCapabilityIO() {
        return IO.BOTH;
    }

    @Override
    public void setStackInSlot(int slot, @NotNull ItemStack stack) {
        storage.setStackInSlot(slot, stack);
    }
}
