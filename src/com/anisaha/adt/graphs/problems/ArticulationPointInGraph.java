package com.anisaha.adt.graphs.problems;

import com.anisaha.adt.graphs.representation.Graph;
import com.anisaha.adt.graphs.representation.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArticulationPointInGraph<T> {
    private int time;
    private Set<Vertex<T>> visited;
    private Set<Vertex<T>> articulations;

    private Vertex<T> startV;
    private Map<Vertex<T>, Integer> visitedTime;
    private Map<Vertex<T>, Integer> lowPointTime;
    private Map<Vertex<T>, Vertex<T>> parent;
    private Graph<T> graph;

    public ArticulationPointInGraph(Graph<T> graph) {
        this.graph = graph;
        time = 0;

        startV = graph.getAllVertex().iterator().next();
        visitedTime = new HashMap<>();
        lowPointTime = new HashMap<>();
        parent = new HashMap<>();
        visited = new HashSet<>();
        articulations = new HashSet<>();
    }

    public Set<Vertex<T>> getArticulationPoints() {
        DFSUtil(visited, articulations, startV, visitedTime, lowPointTime, parent);
        return articulations;
    }

    private void DFSUtil(Set<Vertex<T>> visited, Set<Vertex<T>> articulations,
                         Vertex<T> startVertex, Map<Vertex<T>, Integer> depthFirstNum,
                         Map<Vertex<T>, Integer> lowPointTime, Map<Vertex<T>, Vertex<T>> parent) {
        visited.add(startVertex);
        visitedTime.put(startVertex, time);
        lowPointTime.put(startVertex, time);
        time++;

        int childCount = 0;
        boolean isArticulationPoint = false;
        for (Vertex<T> ver : startVertex.getAdjacentVertexes()) {
            if (ver.equals(parent.get(startVertex)))
                continue;

            if (!visited.contains(ver)) {
                parent.put(ver, startVertex);
                childCount++;

                DFSUtil(visited, articulations, ver, visitedTime, lowPointTime, parent);

                if (visitedTime.get(startVertex) <= lowPointTime.get(ver))
                    isArticulationPoint = true;
                else //does lowTime[vertex] = min(lowTime[vertex], lowTime[adj]);
                    lowPointTime.compute(startVertex, (currentV, time) -> Math.min(time, lowPointTime.get(ver)));
            } else {
                // does lowTime[vertex] = min(lowTime[vertex], visitedTime[adj])
                lowPointTime.compute(startVertex, (currentV, time) -> Math.min(time, lowPointTime.get(ver)));
            }
        }

        if ((parent.get(startVertex) == null && childCount >= 2)
                || parent.get(startVertex) != null
                && isArticulationPoint)
            articulations.add(startVertex);
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 5);
        graph.addEdge(6, 8);

        ArticulationPointInGraph<Integer> obj = new ArticulationPointInGraph<>(graph);
        obj.getArticulationPoints().forEach(point -> System.out.print(point + ", "));
    }
}
