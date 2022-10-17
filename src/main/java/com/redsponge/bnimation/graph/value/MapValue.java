package com.redsponge.bnimation.graph.value;

import java.util.Map;

public class MapValue<T> implements Value<T> {

    private final Map<String, T> map;
    private final String valueName;

    public MapValue(Map<String, T> map, String valueName) {
        this.map = map;
        this.valueName = valueName;
    }

    @Override
    public T get() {
        return map.get(valueName);
    }

    @Override
    public String toString() {
        return "var[" + valueName + "]";
    }
}
