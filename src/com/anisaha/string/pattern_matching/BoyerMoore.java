package com.anisaha.string.pattern_matching;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class BoyerMoore {
    /**
     * Boyer Moore pattern matching implementation,
     * line 25-30 is populating last function as part of pre-processing
     *
     * @param text    the input string
     * @param pattern the pattern string to match in input string
     * @return <b>0</b> based integer start index of pattern in input text, <b>-1</b> if not found
     */
    private static int boyerMooreMatcher(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0)
            return 0;

        Map<Character, Integer> last = new HashMap<>();
        for (int i = 0; i < n; i++)
            last.put(text.charAt(i), -1); // initialize map with default -1

        for (int k = 0; k < m; k++)
            last.put(pattern.charAt(k), k);

        int i = m - 1;
        int k = m - 1;
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(k)) {
                if (k == 0) return i;
                i--;  // examining the previous character of the text/pattern
                k--;
            } else {
                i += m - Math.min(k, 1 + last.get(text.charAt(i)));
                k = m - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final String text = "abacaabacabab";
        final String pattern = "abacab";

        System.out.println("Pattern starts at index(0 based): " + boyerMooreMatcher(text, pattern));
    }
}
