package com.anisaha.sorting;

import com.anisaha.utilities.ArrayUtilities;

public class MergeSortV2 {
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;

            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);

            merge(arr, l, mid, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int len1 = m - l + 1;
        int len2 = r - m;

        int[] l_arr = new int[len1];
        int[] r_arr = new int[len2];

        for (int i = 0; i < len1; i++)
            l_arr[i] = arr[l + i];

        for (int j = 0; j < len2; j++)
            r_arr[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;

        while (i < len1 && j < len2) {
            if (l_arr[i] <= r_arr[j]) {
                arr[k] = l_arr[i];
                i++;
            } else {
                arr[k] = r_arr[j];
                j++;
            }
            k++;
        }

        while (i < len1) {
            arr[k] = l_arr[i];
            i++;
            k++;
        }

        while (j < len2) {
            arr[k] = r_arr[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7, 2, 6};

        mergeSort(arr, 0, arr.length - 1);
        ArrayUtilities.printArray(arr);
    }
}
