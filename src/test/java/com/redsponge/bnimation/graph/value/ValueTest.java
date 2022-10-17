package com.redsponge.bnimation.graph.value;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValueTest {

    @Test
    void testConstantValue() {
        assertEquals(ConstantValue.intValue(10).get(), 10);
    }

    @Test
    void testMapValue() {
        Map<String, Integer> myMap = new HashMap<>();
        String key = "test";

        myMap.put(key, 10);
        MapValue<Integer> myValue = new MapValue<>(myMap, key);

        assertEquals(myValue.get(), 10);

        myMap.put(key, 15);
        assertEquals(myValue.get(), 15);

    }

}
