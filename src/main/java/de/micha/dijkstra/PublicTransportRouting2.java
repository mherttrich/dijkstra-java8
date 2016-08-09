package de.micha.dijkstra;

import de.micha.dijkstra.domain.Edge;
import de.micha.dijkstra.domain.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by micha on 08.08.16.
 * The main class with initialze the algorithm
 */
public class PublicTransportRouting2 {

    // I assume that its just one process
    private static Map<String, Node> nodes = new HashMap<>();
    private static List<Edge> edges = new ArrayList<>();
    private static DijkstraAlgorithm dijkstraAlgorithm;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Integer amountLines = Integer.parseInt(in.readLine());


        List<String> lines = new ArrayList<>();
        for (int i = 0; i <amountLines ; i++) {
            lines.add(in.readLine());
        }
        parseInputData(lines.stream());
        lines = new ArrayList<>();
        for (int i = 0; i <=1 ; i++) {
            lines.add(in.readLine());
        }
        parseInputData(lines.stream());

    }

    private static void parseInputData(Stream<String> lines){
        System.out.println("fsdf");
        lines.map(l -> l.split("\\s*(\\s+|->|:|,)\\s*"))

                .forEach(s -> System.out.println("#"+s[0]+"#"+s[1]+"#"+s[2]+"#"));
    }


}
