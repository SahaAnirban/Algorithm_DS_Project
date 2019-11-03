package com.anisaha.string.pattern_matching;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class KMPStringMatching {
    /**
     * Implementation of KMP String matcher algorithm
     *
     * @param text Input text where pattern needs to be found
     * @param pattern Input pattern
     * @return <b>0</b> based integer index of where the pattern is found, <b>-1</b> if not found
     */
    public static int kmpMatcher(String text, String pattern) {
        final int m = pattern.length();
        final int n = text.length();

        if (m == 0)
            return 0;

        final int[] pre = computePrefixFunction(pattern);

        int j = 0;
        int k = 0;
        while (j < n) {
            if (text.charAt(j) == pattern.charAt(k)) {
                if (k == m - 1)
                    return j - m + 1;
                j++;
                k++;
            } else if (k > 0) {
                k = pre[k - 1];
            } else {
                j++;
            }
        }
        return -1;
    }

    private static int[] computePrefixFunction(String pattern) {
        final int m = pattern.length();
        final int[] pre = new int[m];

        int j = 1;
        int k = 0;
        while (j < m) {
            if (pattern.charAt(j) == pattern.charAt(k)) {
                pre[j] = k + 1;
                j++;
                k++;
            } else if (k > 0) {
                k = pre[k - 1];
            } else {
                j++;
            }
        }
        return pre;
    }

    public static void main(String[] args) {
        final String text = "abacaabaccabacababb";
        final String pattern = "abacab";

        System.out.println("Pattern starts after index: " + kmpMatcher(text, pattern));
    }
}
