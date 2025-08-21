package com.moguang.ctnhbio.api.recipe.ingredient.entity.property;

import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;

public interface IComponentEntityProperty extends IAutoGetValueEntityProperty<Component> {
    @Override
    default Component valueFromTag(Tag tag) {
        return Component.Serializer.fromJson(tag.getAsString());
    }

    @Override
    default Tag writeTag(Component value) {
        return StringTag.valueOf(Component.Serializer.toJson(value));
    }

    @Override
    default Component showValue(Component value) {
        return value;
    }
}
