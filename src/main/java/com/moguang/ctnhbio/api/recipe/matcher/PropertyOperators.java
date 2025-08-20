package com.moguang.ctnhbio.api.recipe.matcher;



public class PropertyOperators {
    public static final PropertyOperator<Object> EQUAL = PropertyOperator.create("equal","=",Object::equals);
    public static final PropertyOperator<Number> GREATER = PropertyOperator.create("greater",">", (a,b)->a.doubleValue()>b.doubleValue());
    public static final PropertyOperator<Number> LESSER = PropertyOperator.create("lesser","<", (a,b)->a.doubleValue()<b.doubleValue());
    public static final PropertyOperator<String> CONTAIN = PropertyOperator.create("contain","~", String::contains);

    public static void init(){}
}
