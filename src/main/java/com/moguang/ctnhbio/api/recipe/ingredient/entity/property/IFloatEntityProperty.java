package com.moguang.ctnhbio.api.recipe.ingredient.entity.property;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.Tag;

public interface IFloatEntityProperty extends IAutoGetValueEntityProperty<Float>{
    @Override
    default Float valueFromTag(Tag tag){
        return ((NumericTag)tag).getAsFloat();
    }

    @Override
    default Tag writeTag(Float value){
        return FloatTag.valueOf(value);
    }
}
