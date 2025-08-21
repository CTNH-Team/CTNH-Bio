package com.moguang.ctnhbio.machine.multiblock.part;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.moguang.ctnhbio.machine.multiblock.CogniAssemblerMachine;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ParabioticBridgePartMachine extends TieredIOPartMachine {


    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(ParabioticBridgePartMachine.class,
            MultiblockPartMachine.MANAGED_FIELD_HOLDER);

    @Getter
    @Persisted
    private final ParabioticBridgeHandler inventory;

    @Getter
    @Persisted
    private ResourceLocation lastRecipeID;


    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    public ParabioticBridgePartMachine(IMachineBlockEntity holder) {
        super(holder, GTValues.ZPM, IO.BOTH);
        this.inventory = new ParabioticBridgeHandler(this);
    }

    public ItemStack insertItemInternal(int slot, @NotNull ItemStack stack, boolean simulate) {
        return inventory.insertItemInternal(slot, stack, simulate);
    }

    public class ParabioticBridgeHandler extends NotifiableItemStackHandler {
        public ParabioticBridgeHandler(MetaMachine machine) {
            super(machine, 1, IO.BOTH, IO.BOTH);
        }

        @Override
        public List<Ingredient> handleRecipeInner(IO io, GTRecipe recipe, List<Ingredient> left, boolean simulate) {
            List<Ingredient> result = handleRecipe(io, recipe, left, simulate, io, storage);
            if (io == IO.IN && !(Objects.equals(result, left))) {
                lastRecipeID = recipe.id;
            }
            return result;
        }


        public List<Ingredient> handleRecipeManually(IO io, GTRecipe recipe, List<Ingredient> left, boolean simulate, IO handler) {
            return handleRecipe(io, recipe, left, simulate, handler, storage);
        }
    }



    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 34, 34);
        var container = new WidgetGroup(4, 4, 26, 26);

        container.addWidget(
                new SlotWidget(getInventory().storage, 0, 4, 4, false, true)
                        .setBackgroundTexture(GuiTextures.SLOT));

        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);

        return group;
    }
}
