package com.moguang.ctnhbio.api.recipe.ingredient.entity.property;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.ShortTag;
import net.minecraft.nbt.Tag;

public interface IShortEntityProperty extends IAutoGetValueEntityProperty<Short> {
    @Override
    default Short valueFromTag(Tag tag){
        return ((NumericTag)tag).getAsShort();
    }

    @Override
    default Tag writeTag(Short value){
        return ShortTag.valueOf(value);
    }
}
