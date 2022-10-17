package com.redsponge.bnimation.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Node {

    private final String name;
    private final Map<String, Link> links;

    Node(String name) {
        this.name = name;
        this.links = new HashMap<>();
    }

    public void addLink(Link link) {
        this.links.put(link.getDestination(), link);
    }

    public void removeLink(Link link) {
        this.links.remove(link.getDestination());
    }

    public Link getLink(String destination) {
        return this.links.get(destination);
    }

    public Optional<String> test(Graph graph) {
        for (String key : links.keySet()) {
            if (links.get(key).test()) {
                return Optional.of(key);
            }
        }

        return Optional.empty();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Node(" + name + ") " + links.values();
    }
}
