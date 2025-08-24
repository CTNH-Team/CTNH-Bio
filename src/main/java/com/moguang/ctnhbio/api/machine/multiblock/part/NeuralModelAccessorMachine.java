package com.moguang.ctnhbio.api.machine.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.BlockableSlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IMachineLife;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.lowdragmc.lowdraglib.utils.Position;
import dev.shadowsoffire.hostilenetworks.data.DataModel;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import lombok.Getter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NeuralModelAccessorMachine extends MultiblockPartMachine implements IMachineLife {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NeuralModelAccessorMachine.class,
            MultiblockPartMachine.MANAGED_FIELD_HOLDER);

    @Override @NotNull
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }


    @Persisted
    @Getter
    private final NeuralModelHandler itemHolder;


    public NeuralModelAccessorMachine(IMachineBlockEntity holder) {
        super(holder);
        itemHolder = new NeuralModelHandler(this);
    }

    //Life cycle
    @Override
    public void onMachineRemoved() {
        clearInventory(this.itemHolder.storage);
    }

    //Recipe Related
    public boolean isLocked() {
        return itemHolder.isLocked;
    }
    public void setLocked(boolean locked){
        itemHolder.isLocked = locked;
    }

    //UI
    @Override
    public Widget createUIWidget() {
        return new WidgetGroup(new Position(0, 0))
                .addWidget(new BlockableSlotWidget(itemHolder, 0, 50/2-18-6, 50/2-18)
                        .setIsBlocked(this::isLocked)
                        .setBackground(GuiTextures.SLOT, GuiTextures.RESEARCH_STATION_OVERLAY));
    }

    //Container
    private static class NeuralModelHandler extends NotifiableItemStackHandler {

        public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NeuralModelHandler.class,
                NotifiableItemStackHandler.MANAGED_FIELD_HOLDER);

        @Override
        public ManagedFieldHolder getFieldHolder() {
            return MANAGED_FIELD_HOLDER;
        }

        @Persisted
        @DescSynced
        private boolean isLocked = false;

        public NeuralModelHandler(MetaMachine machine) {
            super(machine, 1, IO.BOTH, IO.BOTH,size -> new CustomItemStackHandler(size) {

                @Override
                public int getSlotLimit(int slot) {
                    return 1;
                }
            });
        }

        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            return !isLocked? super.extractItem(slot, amount, simulate) : ItemStack.EMPTY;
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return stack.isEmpty() || stack.getItem() instanceof DataModelItem;
        }

        public ItemStack getItemStack() {
            return this.getStackInSlot(0);
        }
        public DataModel getDataModel() {
            return DataModelItem.getStoredModel(getItemStack()).get();
        }


        @Override
        public List<Ingredient> handleRecipeInner(IO io, GTRecipe recipe, List<Ingredient> left, boolean simulate) {
            if(io != IO.IN && io != IO.OUT) return left.isEmpty() ? null : left;

            boolean changed = false;

            for(var it = left.iterator() ; it.hasNext() ; ){
                //item check
                var require = it.next().getItems()[0];
                //item type check
                if(!(require.getItem() == getItemStack().getItem())) continue;

                if(io == IO.IN){
                    //entity type check
                    EntityType<?> requireType = DataModelItem.getStoredModel(require).get().type();
                    EntityType<?> storedType = DataModelItem.getStoredModel(getItemStack()).get().type();
                    if(requireType != storedType) continue;
                    //data check
                    int requireData = DataModelItem.getData(require);
                    int storedData = DataModelItem.getData(getItemStack());
                    if(requireData > storedData) continue;
                }
                else if(!simulate)/* && io == IO.OUT */ {
                        DataModelItem.setData(getItemStack(), DataModelItem.getData(require));
                }

                //all passed
                it.remove();
                changed =true;
                break;
            }

            if(changed && !simulate) isLocked = (io==IO.IN);

            return left.isEmpty() ? null : left;
        }
    }
}
