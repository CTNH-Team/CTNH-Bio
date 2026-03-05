package com.moguang.ctnhbio.api.capability.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.AbstractMapIngredient;
import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.item.CustomMapIngredient;
import com.gregtechceu.gtceu.api.recipe.ui.GTRecipeTypeUI;
import com.gregtechceu.gtceu.integration.xei.entry.item.ItemEntryList;
import com.gregtechceu.gtceu.integration.xei.entry.item.ItemStackList;
import com.gregtechceu.gtceu.integration.xei.handlers.item.CycleItemEntryHandler;
import com.gregtechceu.gtceu.integration.xei.widgets.GTRecipeWidget;
import com.gregtechceu.gtceu.utils.IngredientEquality;

import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.jei.IngredientIO;
import com.lowdragmc.lowdraglib.utils.ColorUtils;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.items.IItemHandlerModifiable;

import com.moguang.ctnhbio.api.recipe.content.SerializerModelIngredient;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenCustomHashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ModelRecipeCapability extends RecipeCapability<ModelIngredient> {

    public final static ModelRecipeCapability CAP = new ModelRecipeCapability();
    // private final static ItemRecipeCapability ItemCAP = ItemRecipeCapability.CAP;

    protected ModelRecipeCapability() {
        super("model", ColorUtils.color((int) (0.8 * 255), 88, 40, 196), true, -1919810,
                SerializerModelIngredient.INSTANCE);
    }

    @Override
    public ModelIngredient copyInner(ModelIngredient content) {
        return content.copy();
    }

    @Override
    public boolean isRecipeSearchFilter() {
        return true;
    }

    @Override
    public @Nullable List<AbstractMapIngredient> getDefaultMapIngredient(Object object) {
        if (object instanceof Ingredient ingredient) {
            return CustomMapIngredient.from(ingredient);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public int getMaxParallelByInput(IRecipeCapabilityHolder holder, GTRecipe recipe, int limit, boolean tick) {
        return super.getMaxParallelByInput(holder, recipe, limit, tick);
    }

    // @Override
    // public void addXEIInfo(WidgetGroup group, int xOffset, GTRecipe recipe, List<Content> contents, boolean perTick,
    // boolean isInput, MutableInt yOffset) {
    // ItemCAP.addXEIInfo(group, xOffset, recipe, contents, perTick, isInput, yOffset);
    // }

    @Override
    public @NotNull List<Object> createXEIContainerContents(List<Content> contents, GTRecipe recipe, IO io) {
        List<Object> entryLists = contents.stream()
                .map(Content::getContent)
                .map(this::of)
                .map(ModelRecipeCapability::mapItem)
                .collect(Collectors.toList());

        while (entryLists.size() < recipe.recipeType.getMaxOutputs(this)) entryLists.add(null);
        return entryLists;
    }

    public static ItemEntryList mapItem(Ingredient ingredient) {
        if (ingredient instanceof ModelIngredient modelIngredient) {
            ItemStackList stackList = new ItemStackList();
            stackList.add(modelIngredient.getModel());
            return stackList;
        }
        return null;
    }

    @Override
    public @Nullable Object createXEIContainer(List<?> contents) {
        return new CycleItemEntryHandler((List<ItemEntryList>) contents);
    }

    @Override
    public @Nullable("null when getWidgetClass() == null") Widget createWidget() {
        SlotWidget slot = new SlotWidget();
        slot.initTemplate();
        return slot;
    }

    @Override
    public @Nullable Class<? extends Widget> getWidgetClass() {
        return SlotWidget.class;
    }

    @Override
    public void applyWidgetInfo(@NotNull Widget widget, int index, boolean isXEI, IO io,
                                GTRecipeTypeUI.@Nullable("null when storage == null") RecipeHolder recipeHolder,
                                @NotNull GTRecipeType recipeType,
                                @Nullable("null when content == null") GTRecipe recipe, @Nullable Content content,
                                @Nullable Object storage, int recipeTier, int chanceTier) {
        if (widget instanceof SlotWidget slot) {
            if (storage instanceof IItemHandlerModifiable items) {
                if (index >= 0 && index < items.getSlots()) {
                    slot.setHandlerSlot(items, index);
                    slot.setIngredientIO(io == IO.IN ? IngredientIO.INPUT : IngredientIO.OUTPUT);
                    slot.setCanTakeItems(!isXEI);
                    slot.setCanPutItems(!isXEI && io.support(IO.IN));
                }
                // 1 over container size.
                // If in a recipe viewer and a research slot can be added, add it.
            }
            if (content != null) {
                float chance = (float) recipeType.getChanceFunction()
                        .getBoostedChance(content, recipeTier, chanceTier) / content.maxChance;
                slot.setXEIChance(chance);
                slot.setOnAddedTooltips((w, tooltips) -> {
                    GTRecipeWidget.setConsumedChance(content,
                            recipe.getChanceLogicForCapability(this, io, isTickSlot(index, io, recipe)),
                            tooltips, recipeTier, chanceTier, recipeType.getChanceFunction());

                });
            }
        }
    }

    @Override
    public Object2IntMap<ModelIngredient> makeChanceCache() {
        return new Object2IntOpenCustomHashMap<>(IngredientEquality.IngredientHashStrategy.INSTANCE);
    }

    @Override
    public boolean shouldBypassDistinct() {
        return false;
    }
}
