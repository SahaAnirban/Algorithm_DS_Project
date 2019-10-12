package com.anisaha.adt.graphs.mst;

import com.anisaha.adt.disjoint_sets.DisjointSets;
import com.anisaha.adt.graphs.representation.Edge;
import com.anisaha.adt.graphs.representation.Graph;
import com.anisaha.adt.graphs.representation.Vertex;

import java.util.ArrayList;
import java.util.List;

public class KruskalMST {
    private Graph<Integer> graph;

    public KruskalMST(Graph<Integer> graph) {
        this.graph = graph;
    }

    public List<Edge<Integer>> getKruskalMST() {
        List<Edge<Integer>> allEdges = graph.getAllEdges();
        // non-decreasing sorting
        allEdges.sort((Edge<Integer> e1, Edge<Integer> e2) -> e1.getWeight() <= e2.getWeight() ? -1 : 1);

        DisjointSets dsSet = new DisjointSets();
        // create as many disjoint sets as total vertices
        for (Vertex<Integer> v : graph.getAllVertex())
            dsSet.makeSet(v.getId());

        List<Edge<Integer>> mstEdges = new ArrayList<>();
        for (Edge<Integer> edge : allEdges) {
            // get the sets of both ends of vertices belonging to an edge
            long set1 = dsSet.findSet(edge.getFromVertex().getId());
            long set2 = dsSet.findSet(edge.getToVertex().getId());

            // if both belong to same set than ignore,
            // since adding them will form loop in MST
            if (set1 == set2)
                continue;
            else {
                mstEdges.add(edge);
                dsSet.union(edge.getFromVertex().getId(), edge.getToVertex().getId());
            }
        }
        return mstEdges;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 6, 3);
        graph.addEdge(2, 4, 2);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 4, 3);
        graph.addEdge(4, 7, 2);
        graph.addEdge(3, 4, 5);
        graph.addEdge(3, 7, 8);

        KruskalMST obj = new KruskalMST(graph);
        List<Edge<Integer>> mstEdges = obj.getKruskalMST();
        mstEdges.forEach(edge -> System.out.println(edge.getFromVertex() + " " + edge.getToVertex()));
    }
}
