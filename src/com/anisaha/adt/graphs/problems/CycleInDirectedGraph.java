package com.anisaha.adt.graphs.problems;

import com.anisaha.adt.graphs.representation.Graph;
import com.anisaha.adt.graphs.representation.Vertex;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class CycleInDirectedGraph {
    public boolean containsCycle(Graph<Integer> graph) {
        Stack<Vertex<Integer>> recurStk = new Stack<>();
        Set<Vertex<Integer>> visited = new LinkedHashSet<>();

        // loop over each vertex if graph is disconnected
        // currently testing on connected graph in main
        return containsCycleUtil(graph.getVertex(0), recurStk, visited);
    }

    private boolean containsCycleUtil(Vertex<Integer> vertex, Stack<Vertex<Integer>> stack, Set<Vertex<Integer>> visited) {
        if (stack.contains(vertex))
            return true;

        if (visited.contains(vertex))
            return false;

        visited.add(vertex);
        stack.push(vertex);

        for (Vertex<Integer> children : vertex.getAdjacentVertexes())
            if (containsCycleUtil(children, stack, visited))
                return true;

        stack.pop();
        return false;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        CycleInDirectedGraph obj = new CycleInDirectedGraph();
        System.out.println("The graph contains cycle?: " + obj.containsCycle(graph));
    }
}
