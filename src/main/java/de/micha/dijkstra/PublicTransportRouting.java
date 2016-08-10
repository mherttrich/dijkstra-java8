package de.micha.dijkstra;

import de.micha.dijkstra.domain.Edge;
import de.micha.dijkstra.domain.Node;
import de.micha.dijkstra.view.NearPointView;
import de.micha.dijkstra.view.ShortestPathView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by micha on 08.08.16.
 * The main class, initialze the algorithm
 */
public class PublicTransportRouting {

    /* I assume that its just one process, even if this is started twice in parallel
        it is still differnt threads. If parallel processing is wanted, rather turn this to class variables
     */
    private static Map<String, Node> nodes = new HashMap<>();
    private static DijkstraAlgorithm dijkstraAlgorithm;


    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Integer amountLines;
        try {
            amountLines = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            //logging
            throw new RuntimeException("error reading input" + e);
        }

        //read the n lines of test data and process it with processTestData consumer
        parseAndProcessInputData(readNLines(in, amountLines).stream(), processTestData);

        dijkstraAlgorithm = new DijkstraAlgorithm(nodes);

        /*
        assume we have 2 lines with queries (like in the example), then I finish reading from STDIN
        this is because we need the main method to return to be able to untit test
        other possibility would be to terminate STDIN by Ctrl D or "quit"
         */
        List<String> lines = readNLines(in, 2);
        try {
            parseAndProcessInputData(lines.stream(), processQueries);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /*
    this consumer consumes the splitted input lines and build nodes and edges
     */
    private static Consumer<String[]> processTestData = splitted -> {
        Node node1 = nodes.computeIfAbsent(splitted[0], Node::new);
        Node node2 = nodes.computeIfAbsent(splitted[1], Node::new);
        Edge edge = new Edge(node1, node2, Integer.parseInt(splitted[2]));
        node1.getEdges().add(edge);
    };

    /*
    this consumer consumes the splitted query lines and calculates route and nearby queries
     */
    private static Consumer<String[]> processQueries = splitted -> {

        if (splitted[0].equals("route")) {
            //build up shortest path from source node to all possible destination nodes
            dijkstraAlgorithm.calculatePath(nodes.get(splitted[1]));
            //get shortest path to B
            ShortestPathView shortestPath = dijkstraAlgorithm.getShortestPath(nodes.get(splitted[1]), nodes.get(splitted[2]));
            System.out.println(shortestPath);

        } else if (splitted[0].equals("nearby")) {
            //build up shortest path from source node to all possible destination nodes
            dijkstraAlgorithm.calculatePath(nodes.get(splitted[1]));
            //get all near by nodes
            String neighboursNearby = dijkstraAlgorithm.getNeighboursNearby(Integer.parseInt(splitted[2]))
                    .stream()
                    .map(NearPointView::toString)
                    .collect(Collectors.joining(", "));

            System.out.println(neighboursNearby);
        }
    };


    private static void parseAndProcessInputData(Stream<String> lines, Consumer<String[]> consumer) {
        // split on one or more spaces, arrow, colon and comma and filter out any faulty lines
        lines.map(l -> l.split("\\s*(\\s+|->|:|,)\\s*"))
                .filter(check)
                .forEach(consumer);
    }

    //Predicat for skipping lines, which could not get splitted in 3 args
    private static Predicate<String[]> check = s -> s.length == 3;

    // simply reading STDIN lines to List
    private static List<String> readNLines(BufferedReader in, int n) {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            try {
                lines.add(in.readLine());
            } catch (IOException e) {
                //logging
                throw new RuntimeException("error reading input" + e);
            }
        }
        return lines;
    }
}
