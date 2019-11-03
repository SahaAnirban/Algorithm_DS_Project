package com.anisaha.adt.graphs.representation;

/**
 * Edge representation of Graph
 * 
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class Edge<T> {
    private boolean isDirected = false;
    private Vertex<T> vertex_1;
    private Vertex<T> vertex_2;
    private int weight;

    public Edge(Vertex<T> vertex_a, Vertex<T> vertex_b) {
        this.vertex_1 = vertex_a;
        this.vertex_2 = vertex_b;
    }

    Edge(Vertex<T> vertex_a, Vertex<T> vertex_b, boolean isDirected, int weight) {
        this.vertex_1 = vertex_a;
        this.vertex_2 = vertex_b;
        this.isDirected = isDirected;
        this.weight = weight;
    }

    Edge(Vertex<T> vertex_a, Vertex<T> vertex_b, boolean isDirected) {
        this.vertex_1 = vertex_a;
        this.vertex_2 = vertex_b;
        this.isDirected = isDirected;
    }

    public Vertex<T> getFromVertex() {
        return vertex_1;
    }

    public Vertex<T> getToVertex() {
        return vertex_2;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vertex_1 == null) ? 0 : vertex_1.hashCode());
        result = prime * result + ((vertex_2 == null) ? 0 : vertex_2.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Edge other = (Edge) obj;
        if (vertex_1 == null) {
            if (other.vertex_1 != null)
                return false;
        } else if (!vertex_1.equals(other.vertex_1))
            return false;

        if (vertex_2 == null) {
            if (other.vertex_2 != null)
                return false;
        } else if (!vertex_2.equals(other.vertex_2))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Edge [ isDirected : " + isDirected + ", vertex from: " + vertex_1 + ", vertex to: " + vertex_2 + " ]";
    }
}