package com.moguang.ctnhbio.api.recipe.content;

import com.gregtechceu.gtceu.api.recipe.content.IContentSerializer;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import com.mojang.serialization.Codec;

public enum SerializerEntityIngredient implements IContentSerializer<EntityIngredient> {

    INSTANCE;

    @Override
    public EntityIngredient of(Object o) {
        if(o instanceof EntityIngredient ingredient){
            return ingredient.copy();
        }
        return defaultValue();
    }

    @Override
    public EntityIngredient defaultValue() {
        return EntityIngredient.EMPTY;
    }

    @Override
    public Class<EntityIngredient> contentClass() {
        return EntityIngredient.class;
    }

    @Override
    public Codec<EntityIngredient> codec() {
        return EntityIngredient.CODEC;
    }
}
