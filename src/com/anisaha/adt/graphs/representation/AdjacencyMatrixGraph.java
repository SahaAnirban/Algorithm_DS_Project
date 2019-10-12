package com.anisaha.adt.graphs.representation;


import java.util.ArrayList;
import java.util.List;

/*
 * UnDirected Graph Adjacency Matrix Representation
 */
public class AdjacencyMatrixGraph {
    private int vertexCount;
    private int adj_matrix[][];

    private static final int EDGE_EXIST = 1;
    private static final int EDGE_NONE = 0;

    public AdjacencyMatrixGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        adj_matrix = new int[vertexCount][vertexCount];
    }

    public void addEdge(int i, int j) {
        if (i < vertexCount && j < vertexCount) {
            adj_matrix[i][j] = EDGE_EXIST;
            adj_matrix[j][i] = EDGE_EXIST;
        }
    }

    public void removeEdge(int i, int j) {
        if (i < vertexCount && j < vertexCount) {
            adj_matrix[i][j] = EDGE_NONE;
            adj_matrix[j][i] = EDGE_NONE;
        }
    }

    public boolean hasEdge(int i, int j) {
        if (i < vertexCount && j < vertexCount)
            return adj_matrix[i][j] == EDGE_EXIST;
        else
            return false;
    }

    public void printGraph() {
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                System.out.print(adj_matrix[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < vertexCount; i++) {
            System.out.print("Vertex " + i + " is connected to: ");
            for (int j = 0; j < vertexCount; j++) {
                if (adj_matrix[i][j] == EDGE_EXIST)
                    System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public int getInDegree(int vertex) {
        if (vertex >= vertexCount)
            throw new IllegalArgumentException("Vertex index invalid");

        int degree = 0;
        for (int i = 0; i < vertexCount; i++) {
            if (adj_matrix[i][vertex] != EDGE_NONE) // the order of index matter in directed graph
                degree++;
        }
        return degree;
    }

    public List<Integer> getNeighbors(int vertex) {
        if (vertex >= vertexCount)
            throw new IllegalArgumentException("Vertex index invalid");

        List<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            if (adj_matrix[vertex][i] != EDGE_NONE) // originating at vertex
                neighbors.add(i);
        }
        return neighbors;
    }

    public static void main(String[] args) {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.printGraph();

        System.out.println("In-Degree of vertex 2: " + graph.getInDegree(2));
        System.out.println("Neighbors of vertex 2: " + graph.getNeighbors(2));
    }

}