package com.anisaha.sorting;

import com.anisaha.utilities.ArrayUtilities;

public class SelectionSort {

    public static void selection_sort(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len - 1; ++i) {
            int min_value_index = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[min_value_index])
                    min_value_index = j;
            }
            ArrayUtilities.swap(arr, i, min_value_index);
        }
    }


    public static void main(String[] args) {
        int[] input = {64, 25, 12, 22, 11};
        selection_sort(input);
        ArrayUtilities.printArray(input);
    }
}
