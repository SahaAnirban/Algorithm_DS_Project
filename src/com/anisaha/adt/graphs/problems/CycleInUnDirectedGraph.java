package com.anisaha.adt.graphs.problems;

import com.anisaha.adt.disjoint_sets.DisjointSets;
import com.anisaha.adt.graphs.representation.Edge;
import com.anisaha.adt.graphs.representation.Graph;
import com.anisaha.adt.graphs.representation.Vertex;

import java.util.HashSet;
import java.util.Set;

/**
 * Runtime and space complexity for both the techniques is O(v)
 * where v is total number of vertices in the graph.
 */
public class CycleInUnDirectedGraph<T> {
    private Graph<T> graph;

    public CycleInUnDirectedGraph(Graph<T> graph) {
        this.graph = graph;
    }

    // Using DFS approach - using recursion stack
    boolean hasCycle() {
        Set<Vertex<T>> visited = new HashSet<>();

        for (Vertex<T> vertex : graph.getAllVertex()) {
            if (visited.contains(vertex))
                continue;

            if (isCyclicUtil(vertex, visited, null))
                return true;
        }
        return false;
    }

    private boolean isCyclicUtil(Vertex<T> v, Set<Vertex<T>> visited, Vertex<T> parent) {
        visited.add(v);

        for (Vertex<T> adjV : v.getAdjacentVertexes()) {
            if (adjV.equals(parent)) //ignore in un-directed graph
                continue;

            if (visited.contains(adjV))
                return true;

            if (isCyclicUtil(adjV, visited, v))
                return true;
        }
        return false;
    }

    boolean detectCycleUsingDisjointSets() {
        DisjointSets disjointSet = new DisjointSets();

        for (Vertex<T> v : graph.getAllVertex())
            disjointSet.makeSet(v.getId());

        for (Edge<T> edge : graph.getAllEdges()) {
            long parent_a = disjointSet.findSet(edge.getFromVertex().getId());
            long parent_b = disjointSet.findSet(edge.getToVertex().getId());
            if (parent_a == parent_b)
                return true;

            disjointSet.union(edge.getFromVertex().getId(), edge.getToVertex().getId());
        }
        return false;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1); // comment this line to break cycle
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        CycleInUnDirectedGraph<Integer> obj = new CycleInUnDirectedGraph<>(graph);
        System.out.println("Graph contains cycle (Using DFS)? " + obj.hasCycle());
        System.out.println("Graph contains cycle (Using Disjoint Sets)? " + obj.detectCycleUsingDisjointSets());
    }

}
