package com.anisaha.adt.graphs.problems;

import com.anisaha.adt.graphs.representation.Graph;
import com.anisaha.adt.graphs.representation.Vertex;

import java.util.*;

/**
 * Time complexity is O(E + V) & Space complexity is O(V)
 * 
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class TarjanStronglyConnected {
    // holds time when vertex was discovered in DFS tree
    private Map<Vertex<Integer>, Integer> visitedTime;

    // stores smallest index of any node known to be reachable from given vertex
    private Map<Vertex<Integer>, Integer> lowTime;

    // stack of visited vertex
    private Deque<Vertex<Integer>> stack;

    // For DFS use, if vertex is ever visited or not
    private Set<Vertex<Integer>> visited;
    private Set<Vertex<Integer>> stackMember;
    private List<Set<Vertex<Integer>>> result;

    // stores the global time counter when vertex is discovered in DFS tree
    private int time;
    private Graph<Integer> graph;

    public TarjanStronglyConnected(Graph<Integer> graph) {
        this.graph = graph;
        time = 0;
        visitedTime = new HashMap<>();
        lowTime = new HashMap<>();
        stackMember = new HashSet<>();
        stack = new LinkedList<>();
        visited = new HashSet<>();
        result = new ArrayList<>();
    }

    public List<Set<Vertex<Integer>>> stronglyConnectedComponents() {
        // start from any vertex in graph
        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            if (visited.contains(vertex))
                continue;

            sccUtil(vertex);
        }
        return result;
    }

    private void sccUtil(Vertex<Integer> vertex) {
        visited.add(vertex);
        visitedTime.put(vertex, time);
        lowTime.put(vertex, time);
        time++;
        stack.addFirst(vertex);
        stackMember.add(vertex);

        for (Vertex<Integer> child : vertex.getAdjacentVertexes()) {
            // if child is not visited, than visit it and check if it has link back to vertex's ancestor
            // In that case update low time to ancestor's visit time
            if (!visited.contains(child)) {
                sccUtil(child);
                //sets lowTime[vertex] = min(lowTime[vertex], lowTime[child]);
                lowTime.compute(vertex, (v, low) -> Math.min(low, lowTime.get(child)));
            }

            // if child is present in stack than check if it was visited before vertex's low time
            // if yes than update the vertex's low time to that value
            if (stackMember.contains(child)) {
                //sets lowTime[vertex] = min(lowTime[vertex], visitedTime[child]);
                lowTime.compute(vertex, (v, low) -> Math.min(low, visitedTime.get(child)));
            }
        }

        // if vertex low time is same as vertex visited time than this is the articulation point
        // and start vertex of SCC. Keep popping stack until you reach the current vertex, this will provide the SCC
        if (visitedTime.get(vertex) == lowTime.get(vertex)) {
            Set<Vertex<Integer>> scComponents = new HashSet<>();
            Vertex<Integer> v;

            do {
                v = stack.pollFirst();
                stackMember.remove(v);
                scComponents.add(v);
            } while (!vertex.equals(v));
            result.add(scComponents);
        }
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        graph.addEdge(7, 6);
        graph.addEdge(7, 8);
        graph.addEdge(8, 7);

        TarjanStronglyConnected obj = new TarjanStronglyConnected(graph);
        obj.stronglyConnectedComponents().forEach(System.out::println);
    }
}
