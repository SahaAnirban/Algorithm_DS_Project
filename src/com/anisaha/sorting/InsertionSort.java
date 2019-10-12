package com.anisaha.sorting;

import com.anisaha.utilities.ArrayUtilities;

public class InsertionSort {

    public static void insertion_sort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; ++i) {
            /*storing current element whose left side is checked for its
             correct position*/
            int value = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > value) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] input = {11, 12, 1, 5, 6, 8};
        insertion_sort(input);
        ArrayUtilities.printArray(input);
    }
}
