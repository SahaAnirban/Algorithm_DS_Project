package com.anisaha.adt.graphs.shortestpath;

import com.anisaha.adt.graphs.representation.Graph;
import com.anisaha.adt.graphs.representation.Vertex;

import java.util.*;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class ShortestPathBFS {
    private final List<List<Vertex<Integer>>> allShortestPaths = new ArrayList<>();

    private List<List<Vertex<Integer>>> findShortestPathBFS(Vertex<Integer> source, Vertex<Integer> dest) {
        if (source == dest)
            return null;

        Map<Vertex<Integer>, Integer> distances = new HashMap<>();
        Queue<Vertex<Integer>> queue = new LinkedList<>();
        queue.add(source);
        distances.put(source, 0);

        while (!queue.isEmpty()) {
            Vertex<Integer> u = queue.poll();
            int distance = distances.get(u);
            List<Vertex<Integer>> adj_list = u.getAdjacentVertexes();
            for (Vertex<Integer> v : adj_list) {
                if (!distances.containsKey(v)) {
                    queue.offer(v);
                    distances.put(v, distance + 1);
                    //parent.add(v);
                    if (v == dest)
                        break;
                }
            }
        }
        return getShortestPathVertices(source, dest, distances);
    }

    private List<List<Vertex<Integer>>> getShortestPathVertices(Vertex<Integer> source, Vertex<Integer> dest,
                                                                Map<Vertex<Integer>, Integer> minDistances) {
        Set<Vertex<Integer>> visited = new HashSet<>();
        List<Vertex<Integer>> paths = new ArrayList<>();
        paths.add(source);
        int shortestDistance = minDistances.get(dest) + 1; // adding 1 to include source
        traverseAllPaths(source, dest, visited, paths, shortestDistance);

        return allShortestPaths;
    }

    private void traverseAllPaths(Vertex<Integer> start, Vertex<Integer> dest,
                                  Set<Vertex<Integer>> visited, List<Vertex<Integer>> paths,
                                  int shortestDist) {
        visited.add(start);
        if (start.equals(dest)) {
            if (paths.size() == shortestDist)
                allShortestPaths.add(new ArrayList<>(paths)); // create copy of list rather than reference paths list

            visited.remove(start);
            return;
        }

        for (Vertex<Integer> v : start.getAdjacentVertexes()) {
            if (!visited.contains(v)) {
                paths.add(v);
                traverseAllPaths(v, dest, visited, paths, shortestDist);
                paths.remove(v);
            }
        }
        visited.remove(start);
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(1, 3);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(3, 7);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(4, 7);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);

        ShortestPathBFS obj = new ShortestPathBFS();

        /*Vertex<Integer> vertex_0 = graph.getVertex(0);
        Vertex<Integer> vertex_7 = graph.getVertex(7); // 0, 3, 7
        System.out.println("The Shortest path from vertex 0 to vertex 7 ");
        List<List<Vertex<Integer>>> result1 = obj.findShortestPathBFS(vertex_0, vertex_7);*/

        System.out.println("The Shortest path from vertex 2 to vertex 6 ");
        Vertex<Integer> vertex_2 = graph.getVertex(2);
        Vertex<Integer> vertex_6 = graph.getVertex(6); // 2 shortest paths present
        List<List<Vertex<Integer>>> result2 = obj.findShortestPathBFS(vertex_2, vertex_6);

        result2.forEach(System.out::println);
    }
}
