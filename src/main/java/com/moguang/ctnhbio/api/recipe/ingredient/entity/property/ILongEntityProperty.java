package com.moguang.ctnhbio.api.recipe.ingredient.entity.property;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.Tag;

public interface ILongEntityProperty extends IAutoGetValueEntityProperty<Long>{
    @Override
    default Long valueFromTag(Tag tag){
        return ((NumericTag)tag).getAsLong();
    }

    @Override
    default Tag writeTag(Long value){
        return LongTag.valueOf(value);
    }
}
