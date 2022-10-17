package com.redsponge.bnimation.graph.value;

public class ConstantValue<T> implements Value<T> {

    private final T value;

    public ConstantValue(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }


    public static ConstantValue<Integer> intValue(int n) {
        return new ConstantValue<>(n);
    }

    public static ConstantValue<Boolean> booleanValue(boolean b) {
        return new ConstantValue<>(b);
    }

    public static ConstantValue<Float> floatValue(float f) {
        return new ConstantValue<>(f);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
