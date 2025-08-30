package com.moguang.ctnhbio.machine.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.BlockableSlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IMachineLife;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IWorkableMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.ICapabilityTrait;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.lowdragmc.lowdraglib.utils.Position;
import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
import com.moguang.ctnhbio.api.machine.trait.NeuralModelContainer;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import com.moguang.ctnhbio.utils.MetaMachineUtils;
import dev.shadowsoffire.hostilenetworks.data.DataModel;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import lombok.Getter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NeuralModelAccessorMachine extends MultiblockPartMachine implements IMachineLife {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NeuralModelAccessorMachine.class,
            MultiblockPartMachine.MANAGED_FIELD_HOLDER);

    @Override @NotNull
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @MustBeInvokedByOverriders
    @Override
    public void removedFromController(@NotNull IMultiController controller) {
        super.removedFromController(controller);
        if (controllers.isEmpty())
            setLocked(false);
    }

    @Override
    public boolean afterWorking(IWorkableMultiController controller) {
        setLocked(false);
        return super.afterWorking(controller);
    }

    @Persisted
    @Getter
    private final NeuralModelContainer modelHolder;


    public NeuralModelAccessorMachine(IMachineBlockEntity holder) {
        super(holder);
        modelHolder = new NeuralModelContainer(this);
    }

    //Life cycle
    @Override
    public void onMachineRemoved() {
        clearInventory(this.modelHolder.storage);
    }

    //Recipe Related
    public boolean isLocked() {
        return modelHolder.isLocked();
    }
    public void setLocked(boolean locked){
        modelHolder.setLocked(locked);
    }

    //UI
    @Override
    public Widget createUIWidget() {
        return new WidgetGroup(new Position(0, 0))
                .addWidget(new BlockableSlotWidget(modelHolder, 0, 50/2-18-6, 50/2-18)
                        .setIsBlocked(this::isLocked)
                        .setBackground(GuiTextures.SLOT, GuiTextures.RESEARCH_STATION_OVERLAY));
    }

    @Override
    public @NotNull List<RecipeHandlerList> getRecipeHandlers() {
        return MetaMachineUtils.getRecipeHandlers(this, modelHolder);
    }
}
