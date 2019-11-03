package com.anisaha.adt.graphs.traversal;

import java.util.*;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class GraphDFS {
    private int vertexCount;
    private LinkedList<Integer>[] adj_list;

    public GraphDFS(int vertexCount) {
        this.vertexCount = vertexCount;
        adj_list = new LinkedList[vertexCount];

        for (int i = 0; i < vertexCount; i++)
            adj_list[i] = new LinkedList<>();
    }

    // Add directed edge from vertex u to vertex v
    public void addEdge(int u, int v) {
        adj_list[u].add(v);
    }

    public void recursiveDFS(int startVertex) {
        //all values defaulted to false in this array
        boolean[] visited = new boolean[vertexCount];
        recursiveDFSHelper(startVertex, visited);
    }

    private void recursiveDFSHelper(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        Iterator<Integer> itr = adj_list[vertex].listIterator();
        while (itr.hasNext()) {
            int v = itr.next();
            if (!visited[v])
                recursiveDFSHelper(v, visited);
        }
    }

    // print all vertexes reachable from startVertex
    public void iterativeDFS(int startVertex) {
        //marking all the vertex as not visited
        List<Boolean> visited = new ArrayList<>(); // Set can be used
        for (int i = 0; i < vertexCount; i++)
            visited.add(false);

        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.empty()) {
            startVertex = stack.peek();
            stack.pop();

            // stack may contain the same vertex,
            // hence we need to print vertex only if un-visited
            if (!visited.get(startVertex)) {
                System.out.print(startVertex + " ");
                visited.add(startVertex, true);
            }

            Iterator<Integer> itr = adj_list[startVertex].listIterator();
            while (itr.hasNext()) {
                int v = itr.next();
                if (!visited.get(v))
                    stack.push(v);
            }
        }
    }

    public static void main(String[] args) {
        GraphDFS graph = new GraphDFS(5);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);

        System.out.println("Recursive Depth First Traversal");
        graph.recursiveDFS(0);

        System.out.println("\nIterative Depth First Traversal");
        graph.iterativeDFS(0);

        /*
            The difference in output between iterative and recursive is correct.

            Explanation: Both are valid DFS algorithms. A DFS does not specify which node you see first.
            It is not important because the order between edges is not defined [remember: edges are a set usually].
            The difference is due to the way you handle each node's children.

            In the iterative approach: You first insert all the elements into the stack - and then handle the head
            of the stack [which is the last node inserted] - thus the first node you handle is the last child.

            In the recursive approach: You handle each node when you see it. Thus the first node you handle is
            the first child. To make the iterative DFS yield the same result as the recursive one - you need to
            add elements to the stack in reverse order [for each node, insert its last child first and its first child last]
         */
    }
}