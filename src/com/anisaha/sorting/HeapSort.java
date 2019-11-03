package com.anisaha.sorting;

import com.anisaha.utilities.ArrayUtilities;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class HeapSort {
    public static void heap_sort(int[] arr) {
        int len = arr.length;

        //build heap out of array
        for (int i = len / 2 - 1; i >= 0; i--)
            buildHeap(arr, len, i);

        for (int j = len - 1; j >= 0; j--) {
            // move current root element to end
            ArrayUtilities.swap(arr, 0, j);

            // call buildHeap on the reduced heap
            buildHeap(arr, j, 0);
        }

    }

    /* buildHeap a tree with root Node i (index in array)
     *  n is the size of the array */
    private static void buildHeap(int[] arr, int n, int i) {
        int largest = i;   // root in max heap
        int l = 2 * i + 1; // left node
        int r = 2 * i + 2; // right node

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            ArrayUtilities.swap(arr, i, largest);
            buildHeap(arr, n, largest);
        }

    }

    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7};
        heap_sort(arr);

        ArrayUtilities.printArray(arr);
    }
}
