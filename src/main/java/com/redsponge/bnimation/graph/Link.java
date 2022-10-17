package com.redsponge.bnimation.graph;

import com.redsponge.bnimation.graph.conditions.Condition;

public class Link {

    private final String destination;
    private final Condition condition;

    public Link(String destination, Condition condition) {
        this.destination = destination;
        this.condition = condition;
    }

    public boolean test() {
        return condition.test();
    }

    @Override
    public String toString() {
        return "--(" + condition + ")--> " + destination;
    }

    public String getDestination() {
        return destination;
    }

    public Condition getCondition() {
        return condition;
    }
}
