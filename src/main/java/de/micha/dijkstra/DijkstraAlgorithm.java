package de.micha.dijkstra;

/**
 * Created by micha on 06.08.16.
 * The main code of Dijkstra is in here
 */

import de.micha.dijkstra.domain.Node;
import de.micha.dijkstra.view.NearPointView;
import de.micha.dijkstra.view.ShortestPathView;

import java.util.*;
import java.util.stream.Collectors;


class DijkstraAlgorithm {


    private final List<Node> nodes;

    /*
    the nodes where we go ahead checking further possible pathes
    we want the one with the shortest distance to source node
    */
    private final PriorityQueue<Node> nodesToCheck;


    DijkstraAlgorithm(Map<String, Node> nodes) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<>(nodes.values());
        nodesToCheck = new PriorityQueue<>(Comparator.comparing(Node::getDistance));
    }

    void calculatePath(Node source) {

        nodes.forEach(n -> {
            n.setVistited(false);
            n.setPreviousNode(null);
            n.setDistance(Integer.MAX_VALUE);
        });

        source.setDistance(0);
        nodesToCheck.add(source);

        while (!nodesToCheck.isEmpty()) {
            Node node = nodesToCheck.poll();
            node.setVistited(true);
            updateShorterDistanceNeighbours(node);
        }
    }


    /*
    find all unvisited neighbours where (distance of node + distance to neighbours) id smaller than current distance
    to neighbour
    for this neighbours we set current node as previousNode and the new distance
     */
    private void updateShorterDistanceNeighbours(Node node) {
        node.getEdges().stream()
                .filter(e -> !e.getDestination().isVisited())
                .filter(e -> e.getDestination().getDistance() > node.getDistance() + e.getWeight())
                .forEach(e -> {
                    e.getDestination().setDistance(node.getDistance() + e.getWeight());
                    e.getDestination().setPreviousNode(node);
                    nodesToCheck.add(e.getDestination());
                });
    }

    List<NearPointView> getNeighboursNearby(int dist) {
        return nodes
                .stream()
                .filter(n -> n.getDistance() > 0 && n.getDistance() <= dist)
                .sorted(Comparator.comparing(Node::getDistance))
                .map(n -> new NearPointView(n.getName(), n.getDistance()))
                .collect(Collectors.toList());
    }

    /*
     * from destination node we know now  the way back to source by seeing the
     * previousNode and repeading that till we have the source node
     * To get the correct direction we only need to invers the result
     *
     */
    ShortestPathView getShortestPath(Node source, Node destination) {

        if (destination.getPreviousNode() == null) {
            throw new RuntimeException("Error: no Route from " + source + " to " + destination);
        }
        LinkedList<Node> path = new LinkedList<>();
        Node current = destination;

        path.add(current);
        while (current.getPreviousNode() != null) {
            current = current.getPreviousNode();
            path.add(current);
        }
        // revers, because we need from source to destination, not other way around
        Collections.reverse(path);
        return new ShortestPathView(path, destination.getDistance());
    }
}

