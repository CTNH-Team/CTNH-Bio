package com.moguang.ctnhbio.api.recipe.content;

import com.gregtechceu.gtceu.api.recipe.content.IContentSerializer;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import com.mojang.serialization.Codec;

public enum SerializerModelIngredient implements IContentSerializer<ModelIngredient> {

    INSTANCE;

    @Override
    public ModelIngredient of(Object o) {
        if(o instanceof ModelIngredient mi)
            return mi;
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
