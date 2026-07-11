package com.moguang.ctnhbio.api.capability.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.recipe.GTRecipeDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.ingredient.IChancedIngredient;
import com.gregtechceu.gtceu.api.recipe.ui.GTRecipeTypeUI;

import com.gregtechceu.gtceu.integration.xei.entry.item.ItemEntryList;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.jei.IngredientIO;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.items.IItemHandlerModifiable;

import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import com.gregtechceu.gtceu.integration.xei.entry.item.ItemStackList;
import com.gregtechceu.gtceu.integration.xei.handlers.item.CycleItemEntryHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.gregtechceu.gtceu.client.util.DrawUtil.drawChance;

public class ModelRecipeCapability extends RecipeCapability<ModelIngredient> {

    public static final ModelRecipeCapability CAP = new ModelRecipeCapability();

    protected ModelRecipeCapability() {
        super("model", 0xFFC458C4, true, ModelIngredient.CODEC);
    }

    @Override
    public ModelIngredient fromNetwork(FriendlyByteBuf friendlyByteBuf) {
        return ModelIngredient.fromNetwork(friendlyByteBuf);
    }

    @Override
    public void toNetwork(ModelIngredient ingredient, FriendlyByteBuf friendlyByteBuf) {
        ingredient.toNetwork(friendlyByteBuf);
    }

    @Override
    public ModelIngredient copyInner(ModelIngredient content, int multiplier) {
        return content.copy();
    }

    @Override
    public boolean isChanced(ModelIngredient content) {
        return content.isChanced();
    }

    @Override
    public IGuiTexture createXEIOverlay(ModelIngredient content, boolean perTick) {
        return new IGuiTexture() {

            @Override
            public void draw(GuiGraphics graphics, int mouseX, int mouseY, float x, float y, int width, int height) {
                if (!content.isChanced()) return;
                drawChance(graphics, x, y, width, height, content.getChance());
            }
        };
    }

    @Override
    public @NotNull List<ItemStackList> createXEIContainerContents(List<ModelIngredient> contents,
                                                                     GTRecipeDefinition recipe, IO io) {
        return contents.stream().map(ModelRecipeCapability::mapModel).toList();
    }

    private static ItemStackList mapModel(ModelIngredient ingredient) {
        ItemStackList stacks = new ItemStackList();
        stacks.add(ingredient.getItem());
        return stacks;
    }

    @Override
    public Object createXEIContainer(List<?> contents) {
        // noinspection unchecked
        return new CycleItemEntryHandler((List<ItemEntryList>) contents);
    }

    @Override
    public @NotNull Widget createWidget() {
        SlotWidget slot = new SlotWidget();
        slot.initTemplate();
        return slot;
    }

    @Override
    public @NotNull Class<? extends Widget> getWidgetClass() {
        return SlotWidget.class;
    }

    @Override
    public void applyWidgetInfo(@NotNull Widget widget, int index, boolean isXEI, IO io,
                                GTRecipeTypeUI.@Nullable("null when storage == null") RecipeHolder recipeHolder,
                                @NotNull GTRecipeType recipeType,
                                @Nullable("null when content == null") GTRecipeDefinition recipe,
                                @Nullable ModelIngredient content, @Nullable Object storage, int recipeTier,
                                int chanceTier) {
        if (!(widget instanceof SlotWidget slot)) return;
        if (storage instanceof IItemHandlerModifiable items && index >= 0 && index < items.getSlots()) {
            slot.setHandlerSlot(items, index);
            slot.setIngredientIO(io == IO.IN ? IngredientIO.INPUT : IngredientIO.OUTPUT);
            slot.setCanTakeItems(!isXEI);
            slot.setCanPutItems(!isXEI && io.support(IO.IN));
        }
        if (content != null && content.isChanced()) {
            slot.setXEIChance((float) content.getChance() / IChancedIngredient.MAX_CHANCE);
        }
    }

}
