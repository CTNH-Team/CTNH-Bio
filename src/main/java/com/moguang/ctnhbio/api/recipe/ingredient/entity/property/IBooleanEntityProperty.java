package com.moguang.ctnhbio.api.recipe.ingredient.entity.property;

import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.Tag;

public interface IBooleanEntityProperty extends IAutoGetValueEntityProperty<Boolean> {
    @Override
    default Boolean valueFromTag(Tag tag){
        return ((NumericTag)tag).getAsByte() != 0;
    }

    @Override
    default Tag writeTag(Boolean value){
        return ByteTag.valueOf(value);
    }
}
