package com.redsponge.bnimation.graph;

import com.redsponge.bnimation.graph.conditions.BooleanCondition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeTest {

    @Test
    void testAddLink() {
        Node node = new Node("testNode");
        Link link = new Link("test", BooleanCondition.TRUE);
        node.addLink(link);
        assertEquals(link, node.getLink("test"));
    }

    @Test
    void testTestFunction() {
        Graph graph = new Graph();
        Node node = new Node("testNode");

        Link link = new Link("test", BooleanCondition.TRUE);
        node.addLink(link);
        assertEquals(node.test(graph).get(), "test");
    }

}
