package com.anisaha.adt.graphs;

import java.util.ArrayList;
import java.util.List;

// Adjacency List implementation of Directed Graph
public class AdjacencyListGraph<E extends Comparable<E>> {
    List<Vertex> vertices;

    public AdjacencyListGraph() {
        vertices = new ArrayList<>();
    }

    public boolean removeEdge(E from, E to) {
        Vertex fromV = null;
        for (Vertex v : vertices) {
            if (from.compareTo(v.data) == 0) {
                fromV = v;
                break;
            }
        }

        if (fromV == null)
            return false;
        return fromV.removeAdjacentVertex(to);
    }

    public boolean addEdge(E from, E to) {
        Vertex fromV = null, toV = null;
        for (Vertex v : vertices) {
            if (from.compareTo(v.data) == 0)
                fromV = v;
            else if (to.compareTo(v.data) == 0)
                toV = v;

            if (fromV != null && toV != null)
                break;
        }
        if (fromV == null) {
            fromV = new Vertex(from);
            vertices.add(fromV);
        }
        if (toV == null) {
            toV = new Vertex(to);
            vertices.add(toV);
        }
        return fromV.addAdjacentVertex(toV);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices) {
            sb.append("Vertex: ");
            sb.append(v.data);
            sb.append(", ");
            sb.append("Adjacent vertices: ");
            for (Vertex v2 : v.adjacent_vertices) {
                sb.append(v2.data);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
        System.out.println(graph);
    }

    private class Vertex {
        E data;
        List<Vertex> adjacent_vertices;

        public Vertex(E data) {
            adjacent_vertices = new ArrayList<>();
            this.data = data;
        }

        public boolean addAdjacentVertex(Vertex to) {
            for (Vertex v : adjacent_vertices)
                if (v.data.compareTo(to.data) == 0)
                    return false; // data already exist

            return adjacent_vertices.add(to); // return true
        }

        public boolean removeAdjacentVertex(E to) {
            // use indexes here so it is possible to
            // remove easily without implementing
            // equals method that ArrayList.remove(Object o) uses
            for (int i = 0; i < adjacent_vertices.size(); i++) {
                if (adjacent_vertices.get(i).data.compareTo(to) == 0) {
                    adjacent_vertices.remove(i);
                    return true;
                }
            }
            return false;
        }
    }
}