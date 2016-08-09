package de.micha.dijkstra.domain;

import java.util.List;

/**
 * Created by micha on 06.08.16.
 * Class to store graph information used by dijkstra
 */

public class Graph {
    private final List<Node> nodes;
    private final List<Edge> edges;

    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }



}

