package com.moguang.ctnhbio.api.recipe.matcher;

import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiPredicate;

import static com.moguang.ctnhbio.api.recipe.matcher.PropertyOperators.*;

public abstract class PropertyOperator<T> implements BiPredicate<T, T> {
    public final String name;
    public final String symbol; //序列化字符

    @Override
    public abstract boolean test(T e, T r);

    private static final Map<String, PropertyOperator<?>> SYMBOLMAP = new HashMap<>();
    private PropertyOperator(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        SYMBOLMAP.put(symbol, this);
    }

    //factory

    public static <T> PropertyOperator<T> create(String name, String symbol, BiPredicate<T, T> matcher) {
        return new PropertyOperator<>( name, symbol) {
            @Override
            public boolean test(T e, T r) {
                return matcher.test(e, r);
            }
        };
    }

    //Serialization
    public Tag serialize(){
        return StringTag.valueOf(symbol);
    }
    public static PropertyOperator<?> deserialize(Tag tag){
        return SYMBOLMAP.getOrDefault(tag.getAsString(),EQUAL);
    }

    //JEI lang
    public String getVerbKey(){
        return String.format("jei.property.requirement.verb.%s", name.toLowerCase(Locale.ROOT));
    }
}
