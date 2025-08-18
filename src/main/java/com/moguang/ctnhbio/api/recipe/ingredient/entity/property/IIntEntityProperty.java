package com.moguang.ctnhbio.api.recipe.ingredient.entity.property;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.Tag;

public interface IIntEntityProperty extends IAutoGetValueEntityProperty<Integer> {
    @Override
    default Integer valueFromTag(Tag tag){
        return ((NumericTag)tag).getAsInt();
    }

    @Override
    default Tag writeTag(Integer value){
        return IntTag.valueOf(value);
    }
}
