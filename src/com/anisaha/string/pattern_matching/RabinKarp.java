package com.anisaha.string.pattern_matching;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class RabinKarp {
    private static final int d = 256; // no. of range of characters in input string
    private static final int q = 101; // prime number for modulus operation

    public List<Integer> searchPattern(char[] text, char[] pattern) {
        List<Integer> match = new ArrayList<>();
        int m = pattern.length;
        int n = text.length;
        int p = 0, t = 0;
        int h = 1; // (d^(m-1)) % q

        int i, j;
        for (i = 0; i < m - 1; i++)
            h = (h * d) % q;

        // calculate hashcode of pattern and text
        for (i = 0; i < m; i++) {
            p = (d * p + pattern[i]) % q;
            t = (d * t + text[i]) % q;
        }

        // slide the pattern window over text
        for (i = 0; i <= n - m; i++) {
            // if hash match, than compare character by character
            if (p == t) {
                for (j = 0; j < m; j++)
                    if (text[i + j] != pattern[j])
                        break;
                // pattern[0 .. m-1] == text[i, i+1, ... ,i+m-1]
                if (j == m)
                    match.add(i);
            }

            // calculate hash value for next m characters in text
            if (i < n - m) {
                t = (d * (t - text[i] * h) + text[i + m]) % q;
                // cases where t is negative, conversion to positive
                if (t < 0)
                    t = t + q;
            }
        }
        return match;
    }

    public static void main(String[] args) {
        RabinKarp rka = new RabinKarp();
        String textInput = "AnirbanSaha";
        System.out.println("Pattern match found at index: " + rka.searchPattern(textInput.toCharArray(), "irban".toCharArray()));
        System.out.println("Pattern match found at index: " + rka.searchPattern(textInput.toCharArray(), "Saha".toCharArray()));
        System.out.println("Pattern match found at index: " + rka.searchPattern(textInput.toCharArray(), "an".toCharArray()));
        System.out.println("Pattern match found at index: " + rka.searchPattern(textInput.toCharArray(), "An".toCharArray()));
        System.out.println("Pattern match found at index: " + rka.searchPattern(textInput.toCharArray(), "ban".toCharArray()));
        System.out.println("Pattern match found at index: " + rka.searchPattern(textInput.toCharArray(), "a".toCharArray()));
    }
}
