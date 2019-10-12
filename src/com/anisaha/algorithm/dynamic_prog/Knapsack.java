package com.anisaha.algorithm.dynamic_prog;

public class Knapsack {
    // Overlapping Subproblems
    private static int recursiveKnapsack(int W, int[] wt, int[] val, int n) {
        if (n == 0 || W == 0)
            return 0;

        // If weight of the nth item is more than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
        if (wt[n - 1] > W)
            return recursiveKnapsack(W, wt, val, n - 1);
        else
            return Math.max(val[n - 1] + recursiveKnapsack(W - wt[n - 1], wt, val, n - 1), // nth item included
                    recursiveKnapsack(W, wt, val, n - 1)); // nth term not included

    }

    private static int knapsackDP(int W, int[] wt, int[] val, int n) {
        int[][] tableStore = new int[n + 1][W + 1];

        // Bottom up table
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0)
                    tableStore[i][j] = 0;
                else if (wt[i - 1] <= j)
                    tableStore[i][j] = Math.max(val[i - 1] + tableStore[i - 1][j - wt[i - 1]], tableStore[i - 1][j]);
                else
                    tableStore[i][j] = tableStore[i - 1][j];
            }
        }
        return tableStore[n][W];
    }

    public static void main(String[] args) {
        int val[] = new int[] { 60, 100, 120 };
        int wt[] = new int[] { 10, 20, 30 };
        int W = 50;
        int n = val.length;
        System.out.println(recursiveKnapsack(W, wt, val, n));
        System.out.println(knapsackDP(W, wt, val, n));
    }
}