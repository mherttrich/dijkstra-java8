package de.micha.dijkstra.domain;

/**
 * Created by micha on 06.08.16.
 * Class to store node information used by dijkstra
 */
public class Node {


    final private String name;
    private Node previousNode;
    // the updated distance (as time) to the source node (can be over several edges)
    private Integer distance;
    private boolean vistited;


    public Node(String name) {
        this.name = name;
        // for nodes we don't know a distance awe assume infinity
        this.distance = Integer.MAX_VALUE;
    }

    public String getName() {
        return name;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public boolean isVisited() {
        return vistited;
    }

    public void setVistited(boolean vistited) {
        this.vistited = vistited;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Node: "+ name;
    }
}
