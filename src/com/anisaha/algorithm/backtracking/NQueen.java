package com.anisaha.algorithm.backtracking;

import java.util.Arrays;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class NQueen {
    private static int solutions = 1;

    static boolean isSafe(int board[][], int row, int col, int N) {
        int i, j;

        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    public static void solveNQueen(int N) {
        int[][] board = new int[N][N];

        if (!solveNQueenUtil(board, 0, N))
            System.out.println("No Solution possible");
    }

    // for each column try all the rows for placing the queen
    private static boolean solveNQueenUtil(int[][] board, int column, int N) {
        if (column == N) {
            System.out.println("Solution: " + solutions++);
            System.out.println(Arrays.deepToString(board)
                                .replace("], ", "]\n")
                                .replace("[[", "[")
                                .replace("]]", "]"));
            return true;
        }

        boolean result = false;
        for (int row = 0; row < N; row++) { // rows increment
            if (isSafe(board, row, column, N)) {
                board[row][column] = 1;
                result = solveNQueenUtil(board, column + 1, N) || result; //column increment
                board[row][column] = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        solveNQueen(4);
    }
}
