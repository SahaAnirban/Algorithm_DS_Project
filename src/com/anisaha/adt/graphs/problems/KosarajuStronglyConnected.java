package com.anisaha.adt.graphs.problems;

import com.anisaha.adt.graphs.representation.Edge;
import com.anisaha.adt.graphs.representation.Graph;
import com.anisaha.adt.graphs.representation.Vertex;

import java.util.*;

public class KosarajuStronglyConnected {
    public List<Set<Vertex<Integer>>> getStronglyConnectedComponents(Graph<Integer> graph) {
        //vertices by finish time in reverse order
        Deque<Vertex<Integer>> stack = new ArrayDeque<>();
        Set<Vertex<Integer>> visited = new HashSet<>();

        // populate stack with vertices finishing last at top
        for (Vertex<Integer> ver : graph.getAllVertex()) {
            if (visited.contains(ver))
                continue;

            DFSUtil(ver, visited, stack);
        }

        Graph<Integer> reverseGraph = reverseGraph(graph);

        visited.clear();
        // Do DFS on the reversed graph using vertex in stack (desc finished time order)
        List<Set<Vertex<Integer>>> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            Vertex<Integer> top = reverseGraph.getVertex(stack.poll().getId());
            if (visited.contains(top))
                continue;

            Set<Vertex<Integer>> sccSet = new HashSet<>();
            reverseGraphDFSUtil(top, visited, sccSet);
            result.add(sccSet);
        }
        return result;
    }

    private void reverseGraphDFSUtil(Vertex<Integer> ver, Set<Vertex<Integer>> visited, Set<Vertex<Integer>> set) {
        visited.add(ver);
        set.add(ver);
        for (Vertex<Integer> adjV : ver.getAdjacentVertexes()) {
            if (visited.contains(adjV))
                continue;

            reverseGraphDFSUtil(adjV, visited, set);
        }
    }

    private void DFSUtil(Vertex<Integer> ver, Set<Vertex<Integer>> visited, Deque<Vertex<Integer>> stack) {
        visited.add(ver);
        for (Vertex<Integer> adjV : ver.getAdjacentVertexes()) {
            if (visited.contains(adjV))
                continue;

            DFSUtil(adjV, visited, stack);
        }
        stack.offerFirst(ver);
    }

    private Graph<Integer> reverseGraph(Graph<Integer> graph) {
        Graph<Integer> revGraph = new Graph<>(true);
        for (Edge<Integer> edge : graph.getAllEdges()) {
            revGraph.addEdge(edge.getToVertex().getId(), edge.getFromVertex().getId(), edge.getWeight());
        }
        return revGraph;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 3);
        graph.addEdge(5, 6);

        KosarajuStronglyConnected obj = new KosarajuStronglyConnected();
        obj.getStronglyConnectedComponents(graph).forEach(System.out::println);
    }
}