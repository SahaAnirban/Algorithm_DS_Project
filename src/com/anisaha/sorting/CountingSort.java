package com.anisaha.sorting;

import com.anisaha.utilities.ArrayUtilities;

public class CountingSort {
    public static int[] counting_sort(int[] arr, int k) {
        int len = arr.length;
        int[] arrSorted = new int[len];

        int[] aux = new int[k + 1];
        for (int i = 0; i < len; ++i)
            aux[arr[i]]++;

        for (int j = 1; j <= k; ++j)
            aux[j] = aux[j] + aux[j - 1];

        for (int i = len - 1; i >= 0; i--) {
            arrSorted[aux[arr[i]] - 1] = arr[i];
            aux[arr[i]]--;
        }

        return arrSorted;
    }

    public static void main(String[] args) {
        //int arr[] = {1, 5, 2, 9, 5, 2, 3, 5, 1};
        int arr[] = {5, 2, 9, 5, 2, 3, 5};

        // getting the maximum value of element in array
        int k = 0;
        for (int i = 0; i < arr.length; i++)
            k = Math.max(k, arr[i]);

        ArrayUtilities.printArray(counting_sort(arr, k));
    }

}
