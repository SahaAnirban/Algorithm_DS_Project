package com.anisaha.adt.graphs.representation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Adjacency List implementation of Directed Graph
public class AdjacencyListGraph {
    private List<Vertex> vertices;

    public AdjacencyListGraph() {
        vertices = new ArrayList<>();
    }

    public boolean removeEdge(int from, int to) {
        Vertex fromV = null;
        for (Vertex v : vertices) {
            if (from == v.data) {
                fromV = v;
                break;
            }
        }

        if (fromV == null)
            return false;
        return fromV.removeAdjacentVertex(to);
    }

    public boolean addEdge(int from, int to) {
        return addEdge(from, to, 0);
    }

    public boolean addEdge(int from, int to, int edgeWeight) {
        Vertex fromV = null, toV = null;
        for (Vertex v : vertices) {
            if (from == v.data)
                fromV = v;
            else if (to == v.data)
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
        return fromV.addAdjacentVertex(toV, edgeWeight);
    }

    public Optional<Vertex> getVertexByData(int Vdata) {
        return vertices.stream().filter(v -> v.data == Vdata).findFirst();
    }

    public int getVerticesCount() {
        return vertices.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices) {
            sb.append("Vertex: ");
            sb.append(v.data);
            sb.append(", ");
            sb.append("Adjacent vertices: ");
            for (Edge e : v.incident_edges) {
                sb.append(e.endV.data);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
        System.out.println(graph);
    }

    public class Vertex {
        public int data;
        List<Edge> incident_edges;

        public Vertex(int data) {
            incident_edges = new ArrayList<>();
            this.data = data;
        }

        public boolean addAdjacentVertex(Vertex to) {
            return addAdjacentVertex(to, 0);
        }

        public boolean addAdjacentVertex(Vertex to, int edgeWeight) {
            for (Edge e : incident_edges) {
                Vertex endV = e.endV;
                if (endV.data == to.data)
                    return false; // data already exist
            }
            return incident_edges.add(new Edge(to, edgeWeight)); // returns true
        }

        public boolean removeAdjacentVertex(int to) {
            // use indexes here so it is possible to
            // remove easily without implementing
            // equals method that ArrayList.remove(Object o) uses
            for (int i = 0; i < incident_edges.size(); i++) {
                if (incident_edges.get(i).endV.data == to) {
                    incident_edges.remove(i);
                    return true;
                }
            }
            return false;
        }

        public List<Vertex> getAdjacentVertices() {
            List<Vertex> result = new ArrayList<>();
            incident_edges.forEach(edge -> result.add(edge.endV));
            return result;
        }

        public List<Edge> getIncidentEdges() {
            return incident_edges;
        }
    }

    public class Edge {
        public int weight;
        public Vertex endV;

        public Edge(Vertex end, int weight) {
            this.endV = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge [weight=" + weight + ", adjacent Vertex=" + endV + "]";
        }
    }
}