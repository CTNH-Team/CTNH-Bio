package com.moguang.ctnhbio.api.recipe.content;

import com.gregtechceu.gtceu.api.recipe.content.IContentSerializer;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import com.mojang.serialization.Codec;

public enum SerializerModelIngredient implements IContentSerializer<ModelIngredient> {

    INSTANCE;

    @Override
    public ModelIngredient of(Object o) {
        if (o instanceof ModelIngredient mi)
            return mi;
        else if (o instanceof Ingredient ig) {
            return ModelIngredient.of(ig.getItems()[0]);
        } else if (o instanceof ItemStack s) {
            return ModelIngredient.of(s);
        }
        return defaultValue();
    }

    @Override
    public ModelIngredient defaultValue() {
        return ModelIngredient.DEFAULT;
    }

    @Override
    public Class<ModelIngredient> contentClass() {
        return ModelIngredient.class;
    }

    @Override
    public Codec<ModelIngredient> codec() {
        return ModelIngredient.CODEC;
    }
}
