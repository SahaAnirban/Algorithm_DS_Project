package com.anisaha.adt.graphs.representation;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
    private long id;
    private T data;
    private List<Edge<T>> edges = new ArrayList<>();
    private List<Vertex<T>> adjacent_vertex = new ArrayList<>();

    public Vertex(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    void addAdjacentVertex(Edge<T> e, Vertex<T> v) {
        edges.add(e);
        adjacent_vertex.add(v);
    }

    public List<Vertex<T>> getAdjacentVertexes() {
        return adjacent_vertex;
    }

    List<Edge<T>> getEdges() {
        return edges;
    }

    public int getDegree() {
        return edges.size();
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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

        Vertex other = (Vertex) obj;
        return id == other.id;
    }
}

