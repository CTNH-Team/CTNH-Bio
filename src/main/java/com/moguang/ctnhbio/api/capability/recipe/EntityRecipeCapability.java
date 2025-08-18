package com.moguang.ctnhbio.api.capability.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.moguang.ctnhbio.api.recipe.content.SerializerEntityIngredient;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.List;

public class EntityRecipeCapability extends RecipeCapability<EntityIngredient> {
    public static final EntityRecipeCapability CAP = new EntityRecipeCapability();
    protected EntityRecipeCapability() {
        super("entity", 0xf5424200, false, 5, SerializerEntityIngredient.INSTANCE);
    }

    @Override
    public EntityIngredient copyInner(EntityIngredient content) {
        return content.copy();
    }

    @Override
    public EntityIngredient copyWithModifier(EntityIngredient content, ContentModifier modifier) {
        final var ret = content.copy();
        ret.count = modifier.apply(ret.count);
        return ret;
    }

    @Override
    public void addXEIInfo(WidgetGroup group, int xOffset, GTRecipe recipe, List<Content> contents, boolean perTick, boolean isInput, MutableInt yOffset) {
        //TODO: UI Render
        group.addWidget(new LabelWidget(3-xOffset,yOffset.addAndGet(10),"e"));
    }
    public static String getTranslationKey(boolean isInput){
        return isInput? "ctnhbio.recipe.input_entity" : "ctnhbio.recipe.output_entity";
    }
}
