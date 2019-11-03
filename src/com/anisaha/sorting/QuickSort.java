package com.anisaha.sorting;

import com.anisaha.utilities.ArrayUtilities;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class QuickSort {
    public static void quickSort(int[] arr, int start, int end) {
        //if(start >= end)
        //   return;

        if (start < end) {
            int pivotIndex = partition(arr, start, end);
            quickSort(arr, start, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        // Always choosing the end element,
        // can randomize it to avoid the worst case
        int pivotValue = arr[end];
        int pIndex = start;

        for (int i = start; i < end; i++) {
            if (arr[i] <= pivotValue) {
                ArrayUtilities.swap(arr, i, pIndex);
                pIndex++;
            }
        }
        ArrayUtilities.swap(arr, pIndex, end);
        return pIndex;
    }

    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7, 2, 6, 1};

        quickSort(arr, 0, arr.length - 1);
        ArrayUtilities.printArray(arr);
    }

}
