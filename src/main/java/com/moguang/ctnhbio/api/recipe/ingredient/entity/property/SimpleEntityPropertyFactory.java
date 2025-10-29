package com.moguang.ctnhbio.api.recipe.ingredient.entity.property;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;

//暴力出奇迹喵
@UtilityClass
public class SimpleEntityPropertyFactory{
    @SuppressWarnings("unchecked")
    public static <T> IBaseEntityProperty<T> create(Class<T> clazz, String section) {
        if (Boolean.class.equals(clazz) || boolean.class.equals(clazz)) {
            return (IBaseEntityProperty<T>) new BooleanEntityProperty(section);
        } else if (Byte.class.equals(clazz) || byte.class.equals(clazz)) {
            return (IBaseEntityProperty<T>) new ByteEntityProperty(section);
        } else if (Short.class.equals(clazz) || short.class.equals(clazz)) {
            return (IBaseEntityProperty<T>) new ShortEntityProperty(section);
        } else if (Integer.class.equals(clazz) || int.class.equals(clazz)) {
            return (IBaseEntityProperty<T>) new IntEntityProperty(section);
        } else if (Long.class.equals(clazz) || long.class.equals(clazz)) {
            return (IBaseEntityProperty<T>) new LongEntityProperty(section);
        } else if (Float.class.equals(clazz) || float.class.equals(clazz)) {
            return (IBaseEntityProperty<T>) new FloatEntityProperty(section);
        } else if (Double.class.equals(clazz) || double.class.equals(clazz)) {
            return (IBaseEntityProperty<T>) new DoubleEntityProperty(section);
        } else if (String.class.equals(clazz)) {
            return (IBaseEntityProperty<T>) new StringEntityProperty(section);
        } else if (Component.class.equals(clazz)) {
            return (IBaseEntityProperty<T>) new ComponentEntityProperty(section);
        } else {
            throw new IllegalArgumentException("Unsupported class: " + clazz.getName());
        }
    }

    @Getter
    public static abstract class SimpleEntityProperty<T> implements IBaseEntityProperty<T> {
        public final String section;
        public SimpleEntityProperty(String section) {
            this.section = section;
            EntityProperties.put(this);
        }

    }

    public static class BooleanEntityProperty extends SimpleEntityProperty<Boolean> implements IBooleanEntityProperty {
        public BooleanEntityProperty(String section) {
            super(section);
        }
    }

    public static class ByteEntityProperty extends SimpleEntityProperty<Byte> implements IByteEntityProperty {
        public ByteEntityProperty(String section) {
            super(section);
        }
    }

    public static class ShortEntityProperty extends SimpleEntityProperty<Short> implements IShortEntityProperty {
        public ShortEntityProperty(String section) {
            super(section);
        }
    }

    public static class IntEntityProperty extends SimpleEntityProperty<Integer> implements IIntEntityProperty {
        public IntEntityProperty(String section) {
            super(section);
        }
    }

    public static class LongEntityProperty extends SimpleEntityProperty<Long> implements ILongEntityProperty {
        public LongEntityProperty(String section) {
            super(section);
        }
    }

    public static class FloatEntityProperty extends SimpleEntityProperty<Float> implements IFloatEntityProperty {
        public FloatEntityProperty(String section) {
            super(section);
        }
    }

    public static class DoubleEntityProperty extends SimpleEntityProperty<Double> implements IDoubleEntityProperty {
        public DoubleEntityProperty(String section) {
            super(section);
        }
    }

    public static class StringEntityProperty extends SimpleEntityProperty<String> implements IStringEntityProperty {
        public StringEntityProperty(String section) {
            super(section);
        }
    }

    public static class ComponentEntityProperty extends SimpleEntityProperty<Component> implements IComponentEntityProperty{
        public ComponentEntityProperty(String section) {
            super(section);
        }
    }

}
