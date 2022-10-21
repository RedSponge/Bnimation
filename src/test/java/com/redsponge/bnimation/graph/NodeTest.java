package com.redsponge.bnimation.graph;

import com.redsponge.bnimation.graph.conditions.BooleanCondition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    @Test
    void testAddLink() {
        String dest = "test";
        Node node = new Node("testNode");

        Link link = new Link(dest, BooleanCondition.TRUE);
        assertTrue(node.addLink(link));
        assertEquals(link, node.getLink(dest).orElseThrow());
    }

    @Test
    void testAddExistingLink() {
        Node node = new Node("testNode");
        String dest = "test";

        Link link = new Link(dest, BooleanCondition.TRUE);
        Link link2 = new Link(dest, BooleanCondition.FALSE);

        assertTrue(node.addLink(link));
        assertFalse(node.addLink(link2));
        assertEquals(link, node.getLink(dest).orElseThrow());
    }

    @Test
    void testGetNonExistingLink() {
        Node node = new Node("testNode");
        assertTrue(node.getLink("iDontExist").isEmpty());
    }


    @Test
    void testTestFunctionSucceeds() {
        Node node = new Node("testNode");
        String dest = "test";

        Link link = new Link(dest, BooleanCondition.TRUE);
        node.addLink(link);
        assertEquals(dest, node.test().orElseThrow());
    }

    @Test
    void testTestFunctionFails() {
        Node node = new Node("testNode");
        String dest = "test";

        Link link = new Link(dest, BooleanCondition.FALSE);
        node.addLink(link);

        assertTrue(node.test().isEmpty());
    }

}
