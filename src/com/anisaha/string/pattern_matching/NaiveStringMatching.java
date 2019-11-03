package com.anisaha.string.pattern_matching;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class NaiveStringMatching {
    public void matchPattern(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && pattern.charAt(j) == text.charAt(i + j))
                j++;

            if (j == m) // if P[0 .. m-1] == T[i .. i + m - 1]
                System.out.println("Pattern found after " + i + " shifts");
        }
    }

    public static void main(String[] args) {
        String text = "AABAACAADAABAAABAA";
        String pattern = "AABA";

        NaiveStringMatching obj = new NaiveStringMatching();
        obj.matchPattern(text, pattern);
    }
}
