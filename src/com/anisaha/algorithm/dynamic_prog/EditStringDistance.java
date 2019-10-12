package com.anisaha.algorithm.dynamic_prog;

public class EditStringDistance {
    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static int recursiveEditDistance(String word1, String word2, int m, int n) {
        if (m == 0)
            return n;

        if (n == 0)
            return m;

        if (word1.charAt(m - 1) == word2.charAt(n - 1))
            return recursiveEditDistance(word1, word2, m - 1, n - 1);

        return 1 + min(recursiveEditDistance(word1, word2, m, n - 1), // insert
                recursiveEditDistance(word1, word2, m - 1, n),      // remove
                recursiveEditDistance(word1, word2, m - 1, n - 1)); // replace

    }

    public static int editDistanceDP(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // len1+1, len2+1, because finally return dp[len1][len2]
        int[][] tableStore = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // base case
                if (i == 0)
                    tableStore[i][j] = j;
                else if (j == 0)
                    tableStore[i][j] = i;
                else if (word1.charAt(i - 1) == word2.charAt(j - 1)) // If last characters are same, ignore last char 
                    tableStore[i][j] = tableStore[i - 1][j - 1];
                else  //If the last character is different, consider all possibilities
                    tableStore[i][j] = 1 + min(tableStore[i][j - 1], 
                                                tableStore[i - 1][j], 
                                                tableStore[i - 1][j - 1]);

            }
        }
        return tableStore[m][n];
    }

    public static void main(String[] args) {
        String str1 = "sunday";
        String str2 = "saturday"; // need to convert "un" to "atur"

        System.out.println(recursiveEditDistance(str1, str2, str1.length(), str2.length()));
        System.out.println(editDistanceDP(str1, str2));
    }

}