package com.anisaha.adt.graphs.shortestpath;

import com.anisaha.adt.graphs.representation.Edge;
import com.anisaha.adt.graphs.representation.Graph;
import com.anisaha.adt.graphs.representation.Vertex;
import com.anisaha.utilities.NegativeWeightCycleException;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class BellmanFordShortestPath {
    Graph<Integer> graph;

    public BellmanFordShortestPath(Graph<Integer> graph) {
        this.graph = graph;
    }

    // using a value less than Integer.MAX_VALUE to avoid integer overflow while addition
    private static Integer INFINITY = 1000000;

    public Map<Vertex<Integer>, Integer> bellmanFordShortestPath(Vertex<Integer> startVertex) {
        Map<Vertex<Integer>, Integer> distance = new HashMap<>();
        Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap<>();

        // INITIALIZE-SINGLE-SOURCE procedure
        for (Vertex<Integer> v : graph.getAllVertex()) {
            distance.put(v, INFINITY);
            parent.put(v, null);
        }

        distance.put(startVertex, 0);
        int verticesCount = graph.getAllVertex().size();

        // relaxing each edges |V| - 1 times
        for (int i = 0; i < verticesCount - 1; i++) {
            for (Edge<Integer> edge : graph.getAllEdges()) {
                Vertex<Integer> u = edge.getFromVertex();
                Vertex<Integer> v = edge.getToVertex();

                if (distance.get(u) + edge.getWeight() < distance.get(v)) {
                    distance.put(v, distance.get(u) + edge.getWeight());
                    parent.put(v, u);
                }
            }
        }

        // negative cycle detection step
        // relaxing all edges again after |V| - 1 tries, if we still get lesser distance means
        // there is negative cycle in the graph
        for (Edge<Integer> edge : graph.getAllEdges()) {
            Vertex<Integer> u = edge.getFromVertex();
            Vertex<Integer> v = edge.getToVertex();

            if (distance.get(u) + edge.getWeight() < distance.get(v))
                throw new NegativeWeightCycleException("The graph contains negative weight cycle");
        }
        return distance;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(0, 3, 8);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 2, -3);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 3, 1);

        BellmanFordShortestPath obj = new BellmanFordShortestPath(graph);
        Vertex<Integer> startV = graph.getAllVertex().iterator().next();
        System.out.println("The shortest path distance map of the graph is: " + obj.bellmanFordShortestPath(startV));
    }
}
