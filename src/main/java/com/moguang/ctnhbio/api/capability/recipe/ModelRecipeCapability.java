package com.moguang.ctnhbio.api.capability.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.AbstractMapIngredient;
import com.gregtechceu.gtceu.api.recipe.ui.GTRecipeTypeUI;
import com.gregtechceu.gtceu.utils.IngredientEquality;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.utils.ColorUtils;
import com.moguang.ctnhbio.api.recipe.content.SerializerModelIngredient;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenCustomHashMap;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModelRecipeCapability extends RecipeCapability<ModelIngredient> {
    public final static ModelRecipeCapability CAP = new ModelRecipeCapability();
    private final static ItemRecipeCapability ItemCAP = ItemRecipeCapability.CAP;

    protected ModelRecipeCapability() {
        super("model", ColorUtils.color((int)(0.8*255),88,40,196), true, 0, SerializerModelIngredient.INSTANCE);
    }

    @Override
    public ModelIngredient copyInner(ModelIngredient content) {
        return content.copy();
    }

    @Override
    public boolean isRecipeSearchFilter() {
        return ItemCAP.isRecipeSearchFilter();
    }

    @Override
    public @Nullable List<AbstractMapIngredient> getDefaultMapIngredient(Object object) {
        return ItemCAP.getDefaultMapIngredient(object);
    }

    @Override
    public void addXEIInfo(WidgetGroup group, int xOffset, GTRecipe recipe, List<Content> contents, boolean perTick, boolean isInput, MutableInt yOffset) {
        ItemCAP.addXEIInfo(group, xOffset, recipe, contents, perTick, isInput, yOffset);
    }

    @Override
    public @NotNull List<Object> createXEIContainerContents(List<Content> contents, GTRecipe recipe, IO io) {
        return ItemCAP.createXEIContainerContents(contents, recipe, io);
    }

    @Override
    public @Nullable Object createXEIContainer(List<?> contents) {
        return ItemCAP.createXEIContainer(contents);
    }

    @Override
    public @Nullable("null when getWidgetClass() == null") Widget createWidget() {
        return ItemCAP.createWidget();
    }

    @Override
    public @Nullable Class<? extends Widget> getWidgetClass() {
        return ItemCAP.getWidgetClass();
    }

    @Override
    public void applyWidgetInfo(@NotNull Widget widget, int index, boolean isXEI, IO io, GTRecipeTypeUI.@Nullable("null when storage == null") RecipeHolder recipeHolder, @NotNull GTRecipeType recipeType, @Nullable("null when content == null") GTRecipe recipe, @Nullable Content content, @Nullable Object storage, int recipeTier, int chanceTier) {
        ItemCAP.applyWidgetInfo(widget, index, isXEI, io, recipeHolder, recipeType, recipe, content, storage, recipeTier, chanceTier);
    }

    @Override
    public Object2IntMap<ModelIngredient> makeChanceCache() {
        return new Object2IntOpenCustomHashMap<>(IngredientEquality.IngredientHashStrategy.INSTANCE);
    }

    @Override
    public boolean shouldBypassDistinct() {
        return ItemCAP.shouldBypassDistinct();
    }
}
