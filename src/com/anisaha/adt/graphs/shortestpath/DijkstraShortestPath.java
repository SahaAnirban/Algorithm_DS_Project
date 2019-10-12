package com.anisaha.adt.graphs.shortestpath;

import java.util.*;

public class DijkstraShortestPath {
    private class Graph {
        private int verticesCount;
        LinkedList<Edge>[] adj_list;

        public Graph(int verticesCount) {
            this.verticesCount = verticesCount;
            adj_list = new LinkedList[verticesCount];
            for (int i = 0; i < verticesCount; i++)
                adj_list[i] = new LinkedList<>();
        }

        public void addEdge(int src, int dest, int weight) {
            Edge edge = new Edge(src, dest, weight);
            adj_list[src].addFirst(edge);

            edge = new Edge(dest, src, weight);
            adj_list[dest].addFirst(edge); // since un-directed
        }

        public void dijkstraShortestPath(int srcVertex) {
            boolean[] visited = new boolean[verticesCount];
            int[] distance = new int[verticesCount];
            Arrays.fill(distance, 0, verticesCount, Integer.MAX_VALUE);

            PriorityQueue<ResultSet> pq = new PriorityQueue<>(verticesCount, Comparator.comparingInt(r -> r.distance));
            distance[0] = 0;
            ResultSet result = new ResultSet();
            result.distance = 0;
            result.vertexId = 0;

            pq.offer(result);
            while (!pq.isEmpty()) {
                ResultSet extracted = pq.poll();
                int extractedValue = extracted.vertexId;
                if (!visited[extractedValue]) {
                    visited[extractedValue] = true;
                    LinkedList<Edge> adjacentV = adj_list[extractedValue];
                    for (Edge edge : adjacentV) {
                        int destVInx = edge.destVertex;
                        if (!visited[destVInx]) {
                            int newKey = distance[extractedValue] + edge.weight;
                            int currentKey = distance[destVInx];
                            if (currentKey > newKey) {
                                ResultSet p = new ResultSet();
                                p.distance = newKey;
                                p.vertexId = destVInx;

                                pq.offer(p);
                                distance[destVInx] = newKey;
                            }
                        }
                    }
                }
            }
            printResult(distance, srcVertex);
        }

        private void printResult(int[] distance, int srcVertex) {
            for (int i = 0; i < verticesCount; i++)
                System.out.println("Source vertex: " + srcVertex + " to vertex " + i + " at distance " + distance[i]);
        }
    }

    public static void main(String[] args) {
        DijkstraShortestPath obj = new DijkstraShortestPath();
        DijkstraShortestPath.Graph graph = obj.new Graph(6);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 2);
        graph.addEdge(0, 3, 9);
        graph.addEdge(0, 4, 3);
        graph.addEdge(4, 5, 2);
        graph.addEdge(5, 3, 2);
        graph.addEdge(2, 3, 3);
        /*graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);*/

        graph.dijkstraShortestPath(0);
    }

    private class Edge {
        int sourceVertex;
        int destVertex;
        int weight;

        public Edge(int srcV, int destV, int edgeWt) {
            sourceVertex = srcV;
            destVertex = destV;
            weight = edgeWt;
        }
    }

    private static class ResultSet {
        int vertexId;
        int distance;
    }
}
