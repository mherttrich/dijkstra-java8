package de.micha.dijkstra;

import de.micha.dijkstra.domain.Edge;
import de.micha.dijkstra.domain.Node;
import de.micha.dijkstra.view.NearPointView;
import de.micha.dijkstra.view.ShortestPathView;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by micha on 08.08.16.
 * The main class, initialze the algorithm
 */
public class PublicTransportRouting {

    private static final Map<String, Node> nodes = new LinkedHashMap<>();
    private static final DijkstraAlgorithm dijkstraAlgorithm;

    static {
        ObjectMapper mapper = new ObjectMapper();
        URL systemResource = ClassLoader.getSystemResource("graph.json");
        try {
            @SuppressWarnings("unchecked")
            List<List<String>> edges = mapper.readValue(systemResource, List.class);
            edges.forEach(e -> {
                loadGraph(e.get(0), e.get(1), Integer.parseInt(e.get(2)));
            });

            dijkstraAlgorithm = new DijkstraAlgorithm(nodes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    private static void loadGraph(String n1, String n2, int distance) {
        Node node1 = nodes.computeIfAbsent(n1, Node::new);
        Node node2 = nodes.computeIfAbsent(n2, Node::new);

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
