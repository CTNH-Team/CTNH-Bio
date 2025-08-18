package com.moguang.ctnhbio.api.recipe.ingredient.entity.property;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

public interface IStringEntityProperty extends IAutoGetValueEntityProperty<String>{
    @Override
    default String valueFromTag(Tag tag){
        return tag.getAsString();
    }

    @Override
    default Tag writeTag(String value){
        return StringTag.valueOf(value);
    }
}
