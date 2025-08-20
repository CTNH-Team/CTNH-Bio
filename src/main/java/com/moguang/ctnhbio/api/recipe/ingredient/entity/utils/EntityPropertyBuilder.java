package com.moguang.ctnhbio.api.recipe.ingredient.entity.utils;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.IBaseEntityProperty;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityPropertyValue;
import com.moguang.ctnhbio.api.recipe.matcher.PropertyOperator;
import lombok.NonNull;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.List;

public class EntityPropertyBuilder {
    private List<EntityPropertyValue<?>> values;
    public EntityPropertyBuilder(EntityPropertyValue<?> ... values){
        this.values = new ArrayList<>(List.of(values));
    }
    public EntityPropertyBuilder add(EntityPropertyValue<?> value){
        values.add(value);
        return this;
    }
    public <T> EntityPropertyBuilder add(IBaseEntityProperty<T> property, T value, PropertyOperator<? super T> operator){
        values.add(property.create(value, operator));
        return this;
    }
    public <T> EntityPropertyBuilder add(IBaseEntityProperty<T> property, T value){
        values.add(property.create(value));
        return this;
    }
    void release(){
        values.clear();
        values = null;
    }
    @NonNull
    public CompoundTag buildInput(){
        CompoundTag nbt = new CompoundTag();
        values.forEach(v->v.putNBT(nbt));

        release();
        return nbt;
    }
    @NonNull
    public CompoundTag buildOutput() {
        CompoundTag nbt = new CompoundTag();
        values.forEach(v -> v.putSlimNBT(nbt));

        release();
        return nbt;
    }
}
