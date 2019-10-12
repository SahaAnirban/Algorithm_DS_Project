package com.anisaha.adt.graphs.shortestpath;

import com.anisaha.utilities.ArrayUtilities;
import com.anisaha.utilities.NegativeWeightCycleException;

import java.util.Arrays;
import java.util.stream.Stream;

public class FloydWarshallAllPairShortestPath {
    private int[][] graph;
    private static Integer INF = Integer.MAX_VALUE;

    public FloydWarshallAllPairShortestPath(int[][] graph) {
        this.graph = graph;
    }

    private int[][] floydWarshallShortestPath() {
        int n = graph.length;
        int[][] distance = new int[n][n];
        int[][] path = new int[n][n];

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                distance[i][j] = graph[i][j];
                if (graph[i][j] != INF && i != j)
                    path[i][j] = i;
                else
                    path[i][j] = -1;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][k] == INF || distance[k][j] == INF)
                        continue;

                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }

        // negative cycle detection
        // if the value in diagonal of distance matrix is negative
        // there is negative weight cycle in the graph
        for (int i = 0; i < distance.length; i++) {
            if (distance[i][i] < 0)
                throw new NegativeWeightCycleException("The input graph contains negative weight cycle");
        }
        return distance;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 3, 6, 15},
                {INF, 0, -2, INF},
                {INF, INF, 0, 2},
                {1, INF, INF, 0}
        };

        FloydWarshallAllPairShortestPath obj = new FloydWarshallAllPairShortestPath(graph);
        ArrayUtilities.print2DArray(obj.floydWarshallShortestPath());
    }
}
