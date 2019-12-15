package com.anisaha.string.pattern_matching;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class DFAPatternMatching {
    private static final int NO_OF_CHARS = 256;

    void searchPattern(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();

        int[][] TF = new int[m + 1][NO_OF_CHARS];
        this.computeTransitionValues(pattern, TF);

        int state = 0;
        for (int i = 0; i < n; i++) {
            state = TF[state][text.charAt(i)];
            if (state == m)
                System.out.println("Pattern found at index " + (i - m + 1));
        }
    }

    /**
     * Computes the values for transition function table of the DFA,
     * from each state all possible characters are evaluated
     *
     * @param pattern the input String pattern
     * @param TF      the transition function table - 2d array
     */
    void computeTransitionValues(String pattern, int[][] TF) {
        int m = pattern.length();
        for (int state = 0; state <= m; ++state)
            for (int x = 0; x < NO_OF_CHARS; ++x)
                TF[state][x] = this.getNextState(pattern, state, (char) x);
    }

    /**
     * @param pattern the input String pattern
     * @param state   the current state in the DFA
     * @param c       the current character being evaluated
     * @return The length of the longest prefix of the pattern such that the prefix is equal to suffix of pat[0 .. k-1]x
     */
    int getNextState(String pattern, int state, char c) {
        int m = pattern.length();
        if (state < m && c == pattern.charAt(state))
            return state + 1;

        for (int ns = state; ns > 0; ns--) {
            if (pattern.charAt(ns - 1) == c) {
                for (int i = 0; i < ns - 1; i++) {
                    if (pattern.charAt(i) != pattern.charAt(state - ns + 1 + i))
                        break;

                    if (i == ns - 1)
                        return ns;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String text = "AABAACAADAABAAABAA";
        String pattern = "AABA";
        DFAPatternMatching obj = new DFAPatternMatching();
        obj.searchPattern(pattern, text);
    }
}
