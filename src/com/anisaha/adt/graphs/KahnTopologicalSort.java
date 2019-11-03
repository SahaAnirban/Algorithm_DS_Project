package com.anisaha.adt.graphs;

import com.anisaha.adt.graphs.representation.Graph;
import com.anisaha.adt.graphs.representation.Vertex;
import com.anisaha.utilities.GenericUserException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class KahnTopologicalSort<T extends Comparable<T>> {
    private Queue<Vertex<T>> result;

    KahnTopologicalSort(Graph<T> graph) {
        if (graph == null)
            throw new IllegalArgumentException("construction with null graph");

        if (graph.getAllVertex().size() == 0)
            return;

        result = new LinkedList<>();
        try {
            doKahnTopologicalSort(graph);
        } catch (GenericUserException e) {
            e.printStackTrace();
        }
    }

    private void doKahnTopologicalSort(Graph<T> graph) throws GenericUserException {
        Queue<Vertex<T>> queue = new LinkedList<>();
        int[] inDegree = new int[graph.getAllVertex().size()];

        for (Vertex<T> v : graph.getAllVertex()) {
            List<Vertex<T>> temp = v.getAdjacentVertexes();
            for (Vertex<T> adjV : temp) {
                long vertexId = adjV.getId();
                inDegree[(int) vertexId]++; // dangerous casting
            }
        }

        for (Vertex<T> v : graph.getAllVertex()) {
            if (inDegree[(int) v.getId()] == 0)
                queue.add(v);
        }

        int visitedCount = 0;
        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();
            result.add(current);

            for (Vertex<T> v : current.getAdjacentVertexes()) {
                if (--inDegree[(int) v.getId()] == 0)
                    queue.add(v);
            }
            visitedCount++;
        }

        if (visitedCount != graph.getAllVertex().size()) {
            throw new GenericUserException("There exist cycle in the input graph");
        }
    }

    public Collection<Vertex<T>> getResult() {
        return result;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        KahnTopologicalSort<Integer> obj = new KahnTopologicalSort<>(graph);
        System.out.println("The Topological order of graph nodes is " + obj.getResult());
    }

}
