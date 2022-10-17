package com.redsponge.bnimation.graph;

import com.redsponge.bnimation.graph.value.GraphValueMap;

public class WholeGraph {

    public final Graph graph;
    public final GraphValueMap values;

    public WholeGraph(Graph graph, GraphValueMap values) {
        this.graph = graph;
        this.values = values;
    }
}
