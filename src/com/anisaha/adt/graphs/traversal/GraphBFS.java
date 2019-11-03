package com.anisaha.adt.graphs.traversal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class GraphBFS {
    private int vertexCount;
    private LinkedList<Integer>[] adj_list;

    public GraphBFS(int vertexCount) {
        this.vertexCount = vertexCount;
        adj_list = new LinkedList[vertexCount];

        for (int i = 0; i < vertexCount; i++)
            adj_list[i] = new LinkedList<>();
    }

    public void addEdge(int u, int v) {
        adj_list[u].add(v);
    }

    public void iterativeBFS(int startVertex) {
        boolean[] visited = new boolean[vertexCount]; // Set can be used
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        while (queue.size() != 0) {
            // de-queue a vertex and print
            startVertex = queue.poll();
            System.out.print(startVertex + " ");

            //get all adjacent vertices of de-queued vertex start
            Iterator<Integer> itr = adj_list[startVertex].listIterator();
            while (itr.hasNext()) {
                int v = itr.next();
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphBFS graph = new GraphBFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println("BFS traversal of the graph");
        graph.iterativeBFS(0);
    }
}
