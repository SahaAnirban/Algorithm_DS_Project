package com.anisaha.adt.graphs.problems;

import com.anisaha.adt.graphs.representation.Vertex;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class GraphColoringDecision {

    Map<Vertex<Integer>, Integer> vertexColorMap;
    private int[] colors;

    public boolean backTrackingColoring(int[][] graphAdjMatrix, int totalColors) {
        int vertexCount = graphAdjMatrix.length;

        // done for correct working of isSafe
        colors = new int[vertexCount];
        Arrays.fill(colors, 0);

        if (!backTrackingUtil(graphAdjMatrix, totalColors, colors, 0)) {
            System.out.println("Solution doesn't exist for " + colors + ", colors");
            return false;
        }
        return true;
    }

    private boolean backTrackingUtil(int[][] graphAdjMatrix, int totalColors, int[] colors, int currentVertex) {
        int vertexCount = graphAdjMatrix.length;
        // All vertices are assigned colors
        if (currentVertex == vertexCount)
            return true;

        for (int color = 1; color <= totalColors; color++) {
            if (isSafe(currentVertex, graphAdjMatrix, colors, color)) {
                colors[currentVertex] = color;
                if (backTrackingUtil(graphAdjMatrix, totalColors, colors, currentVertex + 1))
                    return true;

                colors[currentVertex] = 0;
            }
        }
        return false;
    }

    private boolean isSafe(int currentVertex, int[][] graphAdjMatrix, int[] colors, int color) {
        int vertexCount = graphAdjMatrix.length;
        for (int i = 0; i < vertexCount; i++) {
            if (graphAdjMatrix[currentVertex][i] == 1 && color == colors[i])
                return false;
        }
        return true;
    }

    public int[] getColoringSolution() {
        return colors;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0},
        };

        GraphColoringDecision obj = new GraphColoringDecision();
        System.out.println("Coloring possible solution? " + obj.backTrackingColoring(graph, 3));
        System.out.println(Arrays.toString(obj.getColoringSolution()));
    }
}
