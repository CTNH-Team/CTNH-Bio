package com.moguang.ctnhbio.api.recipe.ingredient.entity.property;

import net.minecraft.nbt.Tag;
import org.apache.commons.lang3.NotImplementedException;

//不要直接用,这个只是为了语法方便
public abstract interface IAutoGetValueEntityProperty<T> extends IBaseEntityProperty<T> {
    @Override default T valueFromTag(Tag tag){throw new NotImplementedException();}
    @Override default Tag writeTag(T value){throw new NotImplementedException();}
}
