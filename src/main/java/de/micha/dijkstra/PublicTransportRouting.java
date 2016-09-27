package de.micha.dijkstra;

import de.micha.dijkstra.domain.Edge;
import de.micha.dijkstra.domain.Node;
import de.micha.dijkstra.view.NearPointView;
import de.micha.dijkstra.view.ShortestPathView;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by micha on 08.08.16.
 * The main class, initialze the algorithm
 */
public class PublicTransportRouting {

    private static Map<String, Node> nodes = new LinkedHashMap<>();
    private static DijkstraAlgorithm dijkstraAlgorithm;

    static{
        //TODO load from JSON conf file
        loadGraph(new Node("A"), new Node("B"), 240);
        loadGraph(new Node("A"), new Node("C"), 70);
        loadGraph(new Node("A"), new Node("D"), 120);
        loadGraph(new Node("C"), new Node("B"), 60);
        loadGraph(new Node("D"), new Node("E"), 480);
        loadGraph(new Node("C"), new Node("E"), 240);
        loadGraph(new Node("B"), new Node("E"), 210);
        loadGraph(new Node("E"), new Node("A"), 300);
        dijkstraAlgorithm = new DijkstraAlgorithm(nodes);
    }

    private static void loadGraph(Node n1, Node n2, int distance) {
        Node node1 = nodes.computeIfAbsent(n1.getName(), Node::new);
        Node node2 = nodes.computeIfAbsent(n2.getName(), Node::new);

        Edge edge = new Edge(node1, node2, distance);
        node1.getEdges().add(edge);
    }


    public String route(String node, String node2) {
        //build up shortest path from source node to all possible destination nodes
        dijkstraAlgorithm.calculatePath(nodes.get(node));
        //get shortest path to B
        ShortestPathView shortestPath = dijkstraAlgorithm.getShortestPath(nodes.get(node), nodes.get(node2));
        return shortestPath.toString();

    }

    //rather Node n, int d as signature
    public String nearby(String node, int dist) {
        //build up shortest path from source node to all possible destination nodes
        dijkstraAlgorithm.calculatePath(nodes.get(node));
        //get all near by nodes
        return dijkstraAlgorithm.getNeighboursNearby(dist)
                .stream()
                .map(NearPointView::toString)
                .collect(Collectors.joining(", "));
    }
}
