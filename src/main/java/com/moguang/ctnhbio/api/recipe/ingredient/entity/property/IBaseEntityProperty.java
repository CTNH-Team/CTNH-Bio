package com.moguang.ctnhbio.api.recipe.ingredient.entity.property;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityPropertyValue;
import com.moguang.ctnhbio.api.recipe.matcher.PropertyOperator;
import com.moguang.ctnhbio.api.recipe.matcher.PropertyOperators;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import java.util.Locale;

public interface IBaseEntityProperty<T>{
    String getSection();
    T valueFromTag(Tag tag);
    Tag writeTag(T value);

    //JEI lang
    default String getLanguageKey(){
        return String.format("jei.entity.property.requirement.%s", getSection().toLowerCase(Locale.ROOT));
    }

    //Utils
    default T valueFromNBT(CompoundTag nbt){
        return valueFromTag(nbt.get(getSection()));
    }
    default EntityPropertyValue<T> create(T value, PropertyOperator<? super T> operator){
        return new EntityPropertyValue<>(this, value, operator);
    }
    default EntityPropertyValue<T> create(T value){
        return new EntityPropertyValue<>(this, value, PropertyOperators.EQUAL);
    }
}
