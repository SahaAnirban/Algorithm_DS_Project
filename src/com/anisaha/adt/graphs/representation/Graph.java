package com.anisaha.adt.graphs.representation;

import java.util.*;

/**
 * Graph class representation, depends on Edge and Vertex classes
 * 
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class Graph<T> {
    private List<Edge<T>> allEdges;
    private Map<Long, Vertex<T>> allVertices;
    private boolean isDirected = false;

    public Graph(boolean isDirected) {
        allEdges = new ArrayList<>();
        allVertices = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addEdge(long vertexFromId, long vertexToId) {
        addEdge(vertexFromId, vertexToId, 0);
    }

    public void addEdge(long vertexFromId, long vertexToId, int weight) {
        Vertex<T> vertexFrom = null;
        if (allVertices.containsKey(vertexFromId))
            vertexFrom = allVertices.get(vertexFromId);
        else {
            vertexFrom = new Vertex<>(vertexFromId);
            allVertices.put(vertexFromId, vertexFrom);
        }

        Vertex<T> vertexTo = null;
        if (allVertices.containsKey(vertexToId))
            vertexTo = allVertices.get(vertexToId);
        else {
            vertexTo = new Vertex<>(vertexToId);
            allVertices.put(vertexToId, vertexTo);
        }

        Edge<T> edge = new Edge<>(vertexFrom, vertexTo, isDirected, weight);
        allEdges.add(edge);
        vertexFrom.addAdjacentVertex(edge, vertexTo);

        // edges are bi-directional in un-directed graph
        if (!isDirected)
            vertexTo.addAdjacentVertex(edge, vertexFrom);
    }

    public List<Edge<T>> getAllEdges() {
        return allEdges;
    }

    // works on for directed graphs
    // since in un-directed graphs edges gets added twice
    public void addVertex(Vertex<T> vertex) {
        if (allVertices.containsKey(vertex.getId()))
            return;

        allVertices.put(vertex.getId(), vertex);
        allEdges.addAll(vertex.getEdges());
    }

    public Vertex<T> createAndAddVertex(long vertexId) {
        if (allVertices.containsKey(vertexId))
            return allVertices.get(vertexId);

        Vertex<T> v = new Vertex<>(vertexId);
        allVertices.put(vertexId, v);
        return v;
    }

    public Vertex<T> getVertex(long vertexId) {
        return allVertices.get(vertexId);
    }

    public Collection<Vertex<T>> getAllVertex() {
        return allVertices.values();
    }

    public void setVertexData(long vertexId, T data) {
        if (allVertices.containsKey(vertexId)) {
            Vertex<T> vertex = allVertices.get(vertexId);
            vertex.setData(data);
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Edge<T> edge : getAllEdges()) {
            buffer.append(edge.getFromVertex() + " " + edge.getToVertex() + " " + edge.getWeight());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}