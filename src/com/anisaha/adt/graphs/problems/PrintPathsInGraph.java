package com.anisaha.adt.graphs.problems;

import com.anisaha.adt.graphs.representation.Graph;
import com.anisaha.adt.graphs.representation.Vertex;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class PrintPathsInGraph {
    public void printPaths(Vertex<Integer> from, Vertex<Integer> to) {
        Set<Vertex<Integer>> visited = new LinkedHashSet<>();
        printPaths(visited, to, from);
    }

    void printPaths(Set<Vertex<Integer>> visited, Vertex<Integer> destination, Vertex<Integer> current) {
        if (visited.contains(current))
            return;

        if (destination.equals(current)) {
            for (Vertex<Integer> v : visited)
                System.out.print(v.getId() + " ");

            System.out.println(destination.getId());
            return;
        }

        visited.add(current);
        for (Vertex<Integer> child : current.getAdjacentVertexes())
            printPaths(visited, destination, child);

        visited.remove(current);
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.addEdge(4, 7);
        graph.addEdge(1, 8);
        graph.addEdge(8, 9);
        graph.addEdge(9, 1);

        Vertex<Integer> from = graph.getVertex(1);
        Vertex<Integer> to = graph.getVertex(7);
        PrintPathsInGraph obj = new PrintPathsInGraph();
        System.out.println("All edge path from vertex 1 to vertex 7 are as follows:");
        obj.printPaths(from, to);
    }
}
