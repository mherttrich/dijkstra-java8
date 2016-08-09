package de.micha.dijkstra.view;

/**
 * Created by micha on 07.08.16.
 * Viewclass to encapsulate a result of a near neighbour search
 */
public class NearPointView {
    private String nodeName;
    private int distance;

    public NearPointView(String nodeName, int distance) {
        this.nodeName = nodeName;
        this.distance = distance;
    }


    @Override
    public String toString() {
        return nodeName + ": " + distance;
    }
}
