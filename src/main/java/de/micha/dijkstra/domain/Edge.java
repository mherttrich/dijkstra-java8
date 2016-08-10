package de.micha.dijkstra.domain;

/**
 * Created by micha on 06.08.16.
 * Class to store edge information used by dijkstra
 */
public class Edge {
    private final Node source;
    private final Node destination;
    private final int weight;

    public Edge(Node source, Node destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Node getDestination() {
        return destination;
    }

    public Node getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge: (" + source + " -> " + destination + ")";
    }


}