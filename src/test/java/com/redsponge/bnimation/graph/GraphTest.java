package com.redsponge.bnimation.graph;

import com.redsponge.bnimation.graph.conditions.BooleanCondition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void testGraphGetNode() {
        Graph graph = new Graph();
        Node node = graph.addNode("test");
        assertEquals(node, graph.getNode("test"));
    }


    @Test
    void testAddNodeTwice() {
        Graph graph = new Graph();
        Node node = graph.addNode("test");

        assertThrows(AssertionError.class, () -> {
            graph.addNode("test");
        });
    }

    @Test
    void testGraphUpdateShouldGo() {
        Graph graph = new Graph();
        Node a = graph.addNode("A");
        Node b = graph.addNode("B");

        a.addLink(new Link("B", BooleanCondition.TRUE));

        assertEquals(graph.getCurrentNode(), a);
        graph.update();
        assertEquals(graph.getCurrentNode(), b);
    }

    @Test
    void testGraphUpdateShouldFail() {
        Graph graph = new Graph();
        Node a = graph.addNode("A");
        Node b = graph.addNode("B");

        a.addLink(new Link("B", BooleanCondition.FALSE));

        assertEquals(graph.getCurrentNode(), a);
        graph.update();
        assertEquals(graph.getCurrentNode(), a);
    }

    @Test
    void testGraphUpdateShouldGoTwice() {
        Graph graph = new Graph();
        Node a = graph.addNode("A");
        Node b = graph.addNode("B");
        Node c = graph.addNode("C");

        a.addLink(new Link("B", BooleanCondition.TRUE));
        b.addLink(new Link("C", BooleanCondition.TRUE));

        graph.update();
        assertEquals(c, graph.getCurrentNode());
    }

    @Test
    void testGraphUpdateLimitRecursion() {
        Graph graph = new Graph();
        Node a = graph.addNode("A");
        Node b = graph.addNode("B");
        Node c = graph.addNode("C");

        a.addLink(new Link("B", BooleanCondition.TRUE));
        b.addLink(new Link("C", BooleanCondition.TRUE));

        graph.update(1);
        assertEquals(b, graph.getCurrentNode());
    }


}
