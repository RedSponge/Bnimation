package com.redsponge.bnimation.graph;

import java.util.HashMap;
import java.util.Map;

public class Graph {

    public static final int NO_LIMIT = -1;

    private final Map<String, Node> nodes;
    private String currentNode;

    public Graph() {
        this.nodes = new HashMap<>();
        this.currentNode = null;
    }

    public Node addNode(String name) {
        assert !this.nodes.containsKey(name);

        if(this.currentNode == null) {
            this.currentNode = name;
        }

        Node node = new Node(name);
        this.nodes.put(name, node);
        return node;
    }

    public Node getNode(String name) {
        return this.nodes.get(name);
    }

    public Node getCurrentNode() {
        return nodes.get(currentNode);
    }

    public void update() {
        update(NO_LIMIT);
    }

    public void update(int recursionLimit) {
        if(recursionLimit == 0) return;

        Node current = getCurrentNode();
        current.test(this).ifPresent(newCurrent -> {
            this.currentNode = newCurrent;
            update(recursionLimit - 1);
        });
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                '}';
    }
}
