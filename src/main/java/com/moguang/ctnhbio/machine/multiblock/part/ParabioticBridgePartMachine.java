package com.moguang.ctnhbio.machine.multiblock.part;

import com.ctnhlang.CN;
import com.ctnhlang.EN;
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
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import com.gregtechceu.gtceu.api.recipe.ingredient.item.ItemIngredient;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import com.moguang.ctnhbio.api.capability.recipe.CogniItemRecipeCapability;
import com.moguang.ctnhbio.machine.multiblock.CogniAssemblerMachine;
import com.moguang.ctnhbio.utils.MetaMachineUtils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

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

    public class ParabioticBridgeHandler extends NotifiableItemStackHandler {

        public ParabioticBridgeHandler(MetaMachine machine) {
            super(machine, 1, IO.BOTH, IO.NONE);
        }

        @Override
        public RecipeCapability<ItemIngredient> getCapability() {
            return CogniItemRecipeCapability.CAP;
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

    @CN("联体桥建立连接失败")
    @EN("Parabiotic Bridge fails to build connection")
    static Lang fail_to_connect;

    @Override
    public Component modifyRecipe(GTRecipe recipe) {
        if(getControllers().size() < 2)
            return fail_to_connect.translate();
        return null;
    }

    @Override
    public void addMultiText(List<Component> textList) {
        if(getControllers().size() < 2) {
            textList.add(fail_to_connect.translate());
        }
    }
}
