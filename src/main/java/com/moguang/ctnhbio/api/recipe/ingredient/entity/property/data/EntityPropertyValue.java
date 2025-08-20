package com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.*;
import com.moguang.ctnhbio.api.recipe.matcher.PropertyOperator;
import lombok.Builder;
import lombok.NonNull;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Predicate;

import static com.moguang.ctnhbio.api.recipe.matcher.PropertyOperators.*;
import static net.minecraft.nbt.Tag.*;

@Builder
public record EntityPropertyValue<T>
        (IBaseEntityProperty<T> property, T value, PropertyOperator<? super T> operator) implements Predicate<CompoundTag> {

    //jei
    public Component getDescription(){
        return Component.translatable(property.getLanguageKey(), //text
                Component.translatable(operator.getVerbKey()), //verb
                value); //value
    }

    @Override
    public boolean test(@NonNull CompoundTag entityNBT) {
        var tag = entityNBT.get(property.getSection());
        if(tag == null) return false;

        var e = property.valueFromTag(tag);
        return operator.test(e, value);
    }



    //serialization
    @NonNull
    public CompoundTag tag(){
        Tag v = property.writeTag(value);
        Tag o = operator.serialize();

        CompoundTag nbt = new CompoundTag();
        nbt.put("v", v);
        nbt.put("o", o);
        return nbt;
    }
    @SuppressWarnings("unchecked")
    @ParametersAreNonnullByDefault
    private static <T> EntityPropertyValue<T> fromCompound(IBaseEntityProperty<T> property, CompoundTag compound){
        var otag = compound.get("o");
        var vtag = compound.get("v");

        var operator = otag == null ? EQUAL : PropertyOperator.deserialize(otag);
        T value = property.valueFromTag(vtag);
        return new EntityPropertyValue<>(property, value, (PropertyOperator<? super T>) operator);
    }
    @Nullable
    @ParametersAreNonnullByDefault
    public static <T> EntityPropertyValue<T> fromNBT(@Nullable IBaseEntityProperty<T> property, CompoundTag nbt) {
        if(property == null) return null;

        var type = nbt.getTagType(property.getSection());
        return switch (type) {
            case TAG_END -> null;
            case TAG_COMPOUND -> {
                var deeper = nbt.getCompound(property.getSection());
                yield fromCompound(property, deeper);
            }
            default -> new EntityPropertyValue<>(property, property.valueFromNBT(nbt), EQUAL);
        };
    }

    public void putNBT(@NotNull CompoundTag nbt){
        nbt.put(property.getSection(),tag());
    }
    public void putSlimNBT(@NotNull CompoundTag nbt){
        nbt.put(property.getSection(),property.writeTag(value));
    }
}
