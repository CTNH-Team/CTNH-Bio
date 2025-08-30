package com.moguang.ctnhbio.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.ICapabilityTrait;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class NeuralModelContainer extends NotifiableRecipeHandlerTrait<ModelIngredient>
        implements ICapabilityTrait, IItemHandlerModifiable {

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NeuralModelContainer.class,
            NotifiableRecipeHandlerTrait.MANAGED_FIELD_HOLDER);

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Persisted
    @DescSynced
    @Getter @Setter
    private boolean isLocked = false;
    @Persisted
    @DescSynced
    public final CustomItemStackHandler storage = new CustomItemStackHandler(1){
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    };

    public NeuralModelContainer(MetaMachine machine) {
        super(machine);
        storage.setFilter(stack->stack.isEmpty() || stack.getItem() instanceof DataModelItem);
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
        return storage.getStackInSlot(0);
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return !isLocked? storage.insertItem(slot, stack, simulate) : stack;
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        return !isLocked? storage.extractItem(slot, amount, simulate) : ItemStack.EMPTY;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return !isLocked && storage.getFilter().test(stack);
    }

    @Override
    public int getSize() {
        return storage.getSlots();
    }

    public ItemStack getItemStack() {
        return this.getStackInSlot(0);
    }


    @Override
    public RecipeCapability<ModelIngredient> getCapability() {
        return ModelRecipeCapability.CAP;
    }

    @Override
    public List<ModelIngredient> handleRecipeInner(IO io, GTRecipe recipe, List<ModelIngredient> left, boolean simulate) {
        if(io != IO.IN && io != IO.OUT) return left.isEmpty() ? null : left;

        Predicate<ModelIngredient> testAndAction =
                io == IO.IN ? ingredient -> ingredient.test(getItemStack()) :   // input: only try to test the model
                simulate? ingredient -> true                          // output: simulate do nothing and always passed
                        : ingredient -> {                                // output: no simulate: set data and model
                            var output = ingredient.getItems()[0];
                            DataModelItem.setStoredModel(getItemStack(), DataModelItem.getStoredModel(output).get());
                            DataModelItem.setData(getItemStack(), DataModelItem.getData(output));
                            return true;
                        };

        boolean changed = false;

        for(var it = left.iterator() ; it.hasNext() ; ){
            var ingredient = it.next();
            if(testAndAction.test(ingredient)) {
                it.remove();
                changed = true;
                break;
            }
        }

        if(!simulate) isLocked = (io==IO.IN && changed);

        return left.isEmpty() ? null : left;
    }

    @Override
    public @NotNull List<Object> getContents() {
        return new ArrayList<>(getItemStack().isEmpty() ? List.of() : List.of(getItemStack()));
    }

    @Override
    public double getTotalContentAmount() {
        return getItemStack().isEmpty() ? 0 : 1;
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
        if(!isLocked)
            storage.setStackInSlot(0, stack);
    }
}
