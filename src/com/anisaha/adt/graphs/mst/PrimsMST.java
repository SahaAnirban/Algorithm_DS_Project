package com.anisaha.adt.graphs.mst;

import java.util.*;

public class PrimsMST {

    private class Graph {
        int verticesCount;
        LinkedList<Edge>[] adjVertices;

        Graph(int verticesCount) {
            this.verticesCount = verticesCount;
            adjVertices = new LinkedList[verticesCount];
            for (int i = 0; i < verticesCount; i++)
                adjVertices[i] = new LinkedList<>();
        }

        void addEdge(int srcVertex, int destVertex, int weight) {
            Edge end1 = new Edge(destVertex, weight);
            Edge end2 = new Edge(srcVertex, weight);
            adjVertices[srcVertex].addLast(end1);
            adjVertices[destVertex].add(end2);
        }

        void builderPrimsMST(Graph graph) {
            boolean[] visited = new boolean[verticesCount];
            ResultSet[] result = new ResultSet[verticesCount];
            int[] parentVertex = new int[verticesCount];

            for (int i = 0; i < verticesCount; i++)
                result[i] = new ResultSet();

            for (int i = 0; i < verticesCount; i++) {
                visited[i] = false;
                result[i].key = Integer.MAX_VALUE;
                result[i].vertex = i;
                parentVertex[i] = -1;
            }

            visited[0] = true;
            result[0].key = 0;

            PriorityQueue<ResultSet> pq = new PriorityQueue<>(verticesCount, Comparator.comparingInt((ResultSet r) -> r.key));
            // pq in java is avoided since pq.remove() is O(N)
            // TreeSet<ResultSet> pq = new TreeSet<>(Comparator.comparingInt((ResultSet r) -> r.key));
            pq.addAll(Arrays.asList(result).subList(0, verticesCount));

            while (!pq.isEmpty()) {
                ResultSet start = pq.poll();
                //ResultSet start = pq.pollFirst();
                int extractedV = start.vertex;
                visited[extractedV] = true;

                List<Edge> edgeList = adjVertices[extractedV];
                for (Edge edge : edgeList) {
                    int destination = edge.destVertex;
                    if (!visited[destination]) {
                        if (result[destination].key > edge.weight) {
                            pq.remove(result[destination]);
                            result[destination].key = edge.weight;
                            pq.add(result[destination]);
                            parentVertex[destination] = start.vertex;
                        }
                    }
                }
            }

            for (int i = 1; i < verticesCount; i++)
                System.out.println(parentVertex[i] + " - " + i);
        }
    }

    private class Edge {
        int destVertex;
        int weight;

        Edge(int destV, int edgeWt) {
            destVertex = destV;
            weight = edgeWt;
        }
    }

    private static class ResultSet {
        int vertex;
        int key;    // minimum weight of any edge connecting Vertex v to a vertex in the tree
    }

    public static void main(String[] args) {
        PrimsMST obj = new PrimsMST();
        PrimsMST.Graph graph = obj.new Graph(9);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);

        graph.builderPrimsMST(graph);
    }
}