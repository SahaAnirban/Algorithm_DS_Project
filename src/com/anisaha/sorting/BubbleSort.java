package com.anisaha.sorting;

import com.anisaha.utilities.ArrayUtilities;

public class BubbleSort {

    public static void bubble_sort(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len - 1; ++i) {
            // (n-k-1) is for ignoring comparisons of elements
            // which have already been compared in earlier iterations
            for (int j = 0; j < len - i - 1; ++j) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtilities.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] input = {5, 1, 4, 2, 8};
        bubble_sort(input);
        ArrayUtilities.printArray(input);
    }
}
