package com.anisaha.adt.graphs.maxflow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Ford–Fulkerson method with BFS is Edmonds-Karp Algorithm
 * 
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class EdmondsKarpMaxFlow {
    private int verticesCount = 6;

    private boolean hasPathWithBFS(int[][] graph, int s, int t, int[] path) {
        // auto initialized to false in java
        boolean[] visited = new boolean[verticesCount];

        // enqueue source vertex and mark it as visited
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        path[s] = -1;

        // performing BFS starting from source 's' vertex
        while (!queue.isEmpty()) {
            int uVertex = queue.poll();
            for (int vVertex = 0; vVertex < verticesCount; vVertex++) {
                if (!visited[vVertex] && graph[uVertex][vVertex] > 0) {
                    queue.add(vVertex);
                    path[vVertex] = uVertex;
                    visited[vVertex] = true;
                }
            }
        }

        // reached sink 't' from source 's'
        return visited[t];
    }

    int getMaximumFlow(int[][] graph, int s, int t) {
        int u, v;
        // create a copy of graph in residualGraph
        // where residualGraph[i][j] indicates residual capacity from i to j vertex
        int[][] residualGraph = Arrays.stream(graph).map(int[]::clone).toArray(int[][]::new);
        int[] path = new int[verticesCount]; // populated in BFS traversal
        int maxFlow = 0; // no flow initially

        while (hasPathWithBFS(residualGraph, s, t, path)) {
            int pathFlow = Integer.MAX_VALUE;
            // find maximum flow through selected path (BFS used)
            for (v = t; v != s; v = path[v]) {
                u = path[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }

            for (v = t; v != s; v = path[v]) {
                u = path[v];
                residualGraph[u][v] -= pathFlow; // reducing capacity for forward flow
                residualGraph[v][u] += pathFlow; // consider for back-flow
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    public static void main(String[] args) {
        int[][] graph_adjMatrix = new int[][] { 
                { 0, 16, 13, 0, 0, 0 }, 
                { 0, 0, 10, 12, 0, 0 }, 
                { 0, 4, 0, 0, 14, 0 },
                { 0, 0, 9, 0, 0, 20 }, 
                { 0, 0, 0, 7, 0, 4 }, 
                { 0, 0, 0, 0, 0, 0 } 
            };

        EdmondsKarpMaxFlow obj = new EdmondsKarpMaxFlow();
        System.out.println("The maximum flow of the graph is: " + obj.getMaximumFlow(graph_adjMatrix, 0, 5));
    }
}
