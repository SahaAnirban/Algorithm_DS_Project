package com.anisaha.string.pattern_matching;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class ZAlgorithm {
    private int[] calculateZ(char[] input) {
        int[] Z = new int[input.length];
        int left = 0;
        int right = 0;
        for (int k = 1; k < input.length; k++) {
            if (k > right) {
                left = right = k;
                while (right < input.length && input[right] == input[right - left])
                    right++;

                Z[k] = right - left;
                right--;
            } else {
                // operating inside the box
                int k1 = k - left;
                // optimization: if value doesn't extend till right boundary, copy values
                if (Z[k1] < right - k + 1)
                    Z[k] = Z[k1];
                else { // recheck for more matches
                    left = k;
                    while (right < input.length && input[right] == input[right - left])
                        right++;

                    Z[k] = right - left;
                    right--;
                }
            }
        }
        return Z;
    }

    public List<Integer> matchPattern(char[] text, char[] pattern) {
        // +1 for $ sign that separates the pattern from text
        char[] processString = new char[text.length + pattern.length + 1];

        int i = 0;
        for (char ch : pattern) {
            processString[i] = ch;
            i++;
        }

        processString[i] = '$';
        i++;

        for (char ch : text) {
            processString[i] = ch;
            i++;
        }

        List<Integer> result = new ArrayList<>();
        int[] Z = calculateZ(processString);

        for (i = 0; i < Z.length; i++) {
            if (Z[i] == pattern.length)
                result.add(i - pattern.length - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        String text = "aaabcxyzaaaabczaaczabbaaaaaabc";
        String pattern = "aaabc";
        ZAlgorithm zAlgo = new ZAlgorithm();
        List<Integer> result = zAlgo.matchPattern(text.toCharArray(), pattern.toCharArray());
        System.out.println(result);
    }
}