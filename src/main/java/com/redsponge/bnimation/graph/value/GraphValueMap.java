package com.redsponge.bnimation.graph.value;

import com.redsponge.bnimation.graph.value.MapValue;

import java.util.HashMap;
import java.util.Map;

public class GraphValueMap {

    private final Map<String, Integer> integers;
    private final Map<String, Float> floats;
    private final Map<String, Boolean> booleans;

    public GraphValueMap() {
        this.integers = new HashMap<>();
        this.floats = new HashMap<>();
        this.booleans = new HashMap<>();
    }

    public void put(String name, int value) {
        integers.put(name, value);
    }

    public void put(String name, float value) {
        floats.put(name, value);
    }

    public void put(String name, boolean value) {
        booleans.put(name, value);
    }

    public int getInt(String name) {
        return integers.get(name);
    }

    public float getFloat(String name) {
        return floats.get(name);
    }

    public boolean getBoolean(String name) {
        return booleans.get(name);
    }

    public MapValue<Integer> getIntRef(String name) {
        return new MapValue<>(integers, name);
    }

    public MapValue<Float> getFloatRef(String name) {
        return new MapValue<>(floats, name);
    }

    public MapValue<Boolean> getBooleanRef(String name) {
        return new MapValue<>(booleans, name);
    }
}
