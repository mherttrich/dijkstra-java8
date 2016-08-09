package de.micha.dijkstra;

/**
 * Created by micha on 06.08.16.
 * The main code of Dijkstra is in here
 */

import de.micha.dijkstra.domain.Edge;
import de.micha.dijkstra.domain.Graph;
import de.micha.dijkstra.domain.Node;
import de.micha.dijkstra.view.NearPointView;
import de.micha.dijkstra.view.ShortestPathView;

import java.util.*;
import java.util.stream.Collectors;


class DijkstraAlgorithm {


    private final List<Node> nodes;
    private final List<Edge> edges;

    // the nodes where we go ahead checking further possible pathes
    private final Set<Node> nodesToCheck;

    DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<>(graph.getNodes());
        this.edges = new ArrayList<>(graph.getEdges());
        nodesToCheck = new HashSet<>();
    }

    void calculatePath(Node source) {

        nodes.forEach(n -> {
            n.setVistited(false);
            n.setPreviousNode(null);
            n.setDistance(Integer.MAX_VALUE);});

        source.setDistance(0);
        nodesToCheck.add(source);

        while (nodesToCheck.size() > 0) {
            Node node = getMinimumUnvisitedNode();
            node.setVistited(true);
            nodesToCheck.remove(node);
            findShortestDistance(node);
        }
    }

    /*
    from the unvistited Nodes get the one with the smallest distance to source node
    */
    private Node getMinimumUnvisitedNode() {
        return nodesToCheck.stream()
                .sorted(Comparator.comparing(Node::getDistance))
                .findFirst().orElseThrow(() -> new RuntimeException("Error, no route"));
    }

    /*
    find all unvisited neighbours where (distance of node + distance to neighbours) id smaller than current distance
    to neighbour
    for this neighbours we set current node as previousNode and the new distance
     */
    private void findShortestDistance(Node node) {
        //TODO getDistanceOfNeigbours called twice
        getNeighbors(node)
                .stream()
                .filter(neighbour -> neighbour.getDistance() > node.getDistance() + getDistanceOfNeigbours(node, neighbour))
                .forEach(neighbour -> {
                            neighbour.setDistance(node.getDistance() + getDistanceOfNeigbours(node, neighbour));
                            neighbour.setPreviousNode(node);
                            nodesToCheck.add(neighbour);
                        }
                );
    }

    /*
     we search the edge between source and destination
     and return it's weight/ distance
     */
    private int getDistanceOfNeigbours(Node source, Node destination) {
        return edges.stream()
                .filter(
                        e -> e.getSource().equals(source)
                                && e.getDestination().equals(destination))
                .map(Edge::getWeight)
                .findAny() //should be just one
                .orElseThrow(() -> new RuntimeException("Error: No route from "+ source + " to " + destination));
    }

    /* we want all nodes where the
         edge starts on current node and
         end of that edge is a not visited node
    */
    private List<Node> getNeighbors(Node node) {
        return edges.stream()
                .filter(edge ->
                        edge.getSource().equals(node))
                .map(Edge::getDestination)
                .filter(n -> !n.isVisited())
                .collect(Collectors.toList());
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
        //Optional Path


        if (destination.getPreviousNode() == null){
            throw new RuntimeException("Error: no Route from "+ source + " to " + destination);
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

