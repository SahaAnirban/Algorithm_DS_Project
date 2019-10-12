package com.anisaha.algorithm.dynamic_prog;

public class LongestCommonSeq {
    public static String getLCS(String s1, String s2) {
        if (s1 == null || s2 == null)
            return null;

        if (s1.length() == 0 || s2.length() == 0)
            return "";

        char[] s1char = s1.toCharArray();
        char[] s2char = s2.toCharArray();

        // table[i][j] = LCS of first i elements of arr1 and first j characters of arr2
        int[][] table = new int[s1char.length + 1][s2char.length + 1];

        // initialize base cases
        for (int i = 0; i < s1char.length + 1; i++)
            table[i][0] = 0;

        for (int j = 1; j < s2char.length + 1; j++)
            table[0][j] = 0;

        for (int i = 1; i < s1char.length + 1; i++) {
            for (int j = 1; j < s2char.length + 1; j++) {
                if (s1char[i - 1] == s2char[j - 1]) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }

        System.out.println("The longest sequence is of length " + table[s1char.length][s2char.length]);
        return getLCSString(s1, s2, table);
    }

    private static String getLCSString(String s1, String s2, int[][] table) {
        StringBuilder lcsString = new StringBuilder();
        int m = s1.length(), n = s2.length();

        while (m > 0 && n > 0) {
            if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
                lcsString.append(s1.charAt(m - 1));
                m--;
                n--;
            } else if (table[m - 1][n] > table[m][n - 1]) {
                m--;
            } else {
                n--;
            }
        }
        return lcsString.reverse().toString();
    }

    // Recursive solution to LCS, demonstrating overlapping sub-problem
    public static int getLCSRecursive(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        char[] s1c = s1.toCharArray();
        char[] s2c = s2.toCharArray();

        return getLCSRecursive(s1c, s2c, m, n);
    }

    private static int getLCSRecursive(char[] s1c, char[] s2c, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (s1c[m - 1] == s2c[n - 1])
            return 1 + getLCSRecursive(s1c, s2c, m - 1, n - 1);
        else
            return Math.max(getLCSRecursive(s1c, s2c, m - 1, n), getLCSRecursive(s1c, s2c, m, n - 1));
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        System.out.println("The longest common sequence string is: " + getLCS(s1, s2));
        System.out.println("The longest common sequence length using recursive approach is: " + getLCSRecursive(s1, s2));
    }
}