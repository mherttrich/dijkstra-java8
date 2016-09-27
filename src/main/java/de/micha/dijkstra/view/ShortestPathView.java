package de.micha.dijkstra.view;

import de.micha.dijkstra.domain.Node;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by micha on 07.08.16.
 * Viewclass to encapsulate a result of a shortest path search
 */
public class ShortestPathView {
    private final List<Node> path;
    private final int time;

    public ShortestPathView(List<Node> path, int time) {
        this.path = path;
        this.time = time;
    }


    @Override
    public String toString() {
        String path = this.path.stream().map(Node::getName)
                .collect(Collectors.joining(" -> "));
        return path + ": " + time;
    }
}
