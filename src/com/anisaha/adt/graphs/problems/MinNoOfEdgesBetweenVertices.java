package com.anisaha.adt.graphs.problems;

import com.anisaha.adt.graphs.representation.Graph;
import com.anisaha.adt.graphs.representation.Vertex;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class MinNoOfEdgesBetweenVertices {
    private int minNoOfEdges = Integer.MAX_VALUE;
    private int edgeCount = 0;
    private Graph<Integer> graph;

    public MinNoOfEdgesBetweenVertices(Graph<Integer> graph) {
        this.graph = graph;
    }

    void minEdgesDFS(Vertex<Integer> from, Vertex<Integer> to) {
        Set<Vertex<Integer>> visited = new LinkedHashSet<>();
        minEdgesDFS(visited, from, to);
    }

    void minEdgesDFS(Set<Vertex<Integer>> visited, Vertex<Integer> source, Vertex<Integer> dest) {
        visited.add(source);
        if (source.equals(dest) && (minNoOfEdges > edgeCount)) {
            minNoOfEdges = edgeCount;
        } else {
            for (Vertex<Integer> v : source.getAdjacentVertexes()) {
                if (!visited.contains(v)) {
                    edgeCount++;
                    minEdgesDFS(visited, v, dest);
                }
            }
        }
        visited.remove(source);
        edgeCount--;
    }

    public int getMinNoOfEdges(int fromVertexId, int toVertexId) {
        this.minEdgesDFS(graph.getVertex(fromVertexId), graph.getVertex(toVertexId));
        return minNoOfEdges;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        MinNoOfEdgesBetweenVertices obj = new MinNoOfEdgesBetweenVertices(graph);
        System.out.println("The minimum number of edges between 0 and 3 vertex is: " + obj.getMinNoOfEdges(0, 3));
    }
}
