package com.moguang.ctnhbio.api.recipe.ingredient.entity.property;

import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NumericTag;
import net.minecraft.nbt.Tag;

public interface IByteEntityProperty extends IAutoGetValueEntityProperty<Byte> {
    @Override
    default Byte valueFromTag(Tag tag){
        return ((NumericTag)tag).getAsByte();
    }

    @Override
    default Tag writeTag(Byte value){
        return ByteTag.valueOf(value);
    }
}
