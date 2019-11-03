package com.anisaha.sorting;

import com.anisaha.utilities.ArrayUtilities;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class ShellSort {
    // Robert Sedgewick implementation
    static void shell_sort(int[] arr) {
        int len = arr.length;

        // 3x+1 increment sequence: 1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < len / 3)
            h = 3 * h + 1;

        while (h >= 1) {
            // h-insertion_sort the array using insertion insertion_sort
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && arr[j] < arr[j - h]; j -= h) {
                    ArrayUtilities.swap(arr, j, j - h);
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int arr[] = {12, 34, 54, 2, 3, 1, 2, 5, 10, 11, 56, 37, 9, 12, 14};
        shell_sort(arr);

        ArrayUtilities.printArray(arr);
    }
}
