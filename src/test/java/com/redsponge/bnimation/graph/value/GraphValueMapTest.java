package com.redsponge.bnimation.graph.value;

import com.redsponge.bnimation.graph.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GraphValueMapTest {

    @Test
    void testInteger() {
        GraphValueMap map = new GraphValueMap();
        map.put("hi", 10);
        assertEquals(map.getInt("hi"), 10);
        assertEquals(map.getIntRef("hi").get(), 10);
    }

    @Test
    void testFloat() {
        GraphValueMap map = new GraphValueMap();
        map.put("ho", 1.f);
        assertEquals(map.getFloat("ho"), 1.f);
        assertEquals(map.getFloatRef("ho").get(), 1.f);
    }

    @Test
    void testBoolean() {
        GraphValueMap map = new GraphValueMap();
        map.put("he", false);
        assertFalse(map.getBoolean("he"));
        assertFalse(map.getBooleanRef("he").get());
    }

}
