package com.moguang.ctnhbio.api.recipe.ingredient.entity.property;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.Tag;

public interface IDoubleEntityProperty extends IAutoGetValueEntityProperty<Double> {
    @Override
    default Double valueFromTag(Tag tag){
        return ((NumericTag)tag).getAsDouble();
    }

    @Override
    default Tag writeTag(Double value){
        return DoubleTag.valueOf(value);
    }
}
