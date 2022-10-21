package com.redsponge.bnimation.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A class representing a node in a graph. The node has a name and links to other nodes (by name)
 */
public class Node {

    private final String name;
    private final Map<String, Link> links;

    Node(String name) {
        this.name = name;
        this.links = new HashMap<>();
    }

    /**
     * Adds a link to the node.
     * If the node already has a link to that destination, it will not be overriden.
     * @param link The link to add
     * @return Whether the link was added successfuly or not
     */
    public boolean addLink(Link link) {
        if(this.links.containsKey(link.getDestination())) return false;

        this.links.put(link.getDestination(), link);
        return true;
    }

    /**
     * Removes a link by its destination
     * @param link The link to remove
     */
    public Optional<Link> removeLink(Link link) {
        return removeLink(link.getDestination());
    }


    /**
     * Removes a link by its destination
     * @param destination The destination of the link to remove
     * @return An optional of eother the link that was removed if it existed, or empty
     */
    public Optional<Link> removeLink(String destination) {
        return Optional.ofNullable(this.links.remove(destination));
    }

    /**
     * Looks up a link by its destination
     * @param destination The destination of the link to lookup
     * @return An optional containing the link if it exists
     */
    public Optional<Link> getLink(String destination) {
        return Optional.ofNullable(this.links.get(destination));
    }

    public Optional<String> test() {
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
