package com.redsponge.bnimation.graph;

import com.redsponge.bnimation.graph.value.GraphValueMap;

public class ParameterizedGraph {

    private final Graph graph;
    private final GraphValueMap values;

    public ParameterizedGraph(Graph graph, GraphValueMap values) {
        this.graph = graph;
        this.values = values;
    }

    public Graph getGraph() {
        return graph;
    }

    public GraphValueMap getValues() {
        return values;
    }
}
