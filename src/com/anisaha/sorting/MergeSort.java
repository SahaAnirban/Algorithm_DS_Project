package com.anisaha.sorting;

import com.anisaha.utilities.ArrayUtilities;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class MergeSort {
    public static void mergeSort(int[] arr) {
        mergeSort(arr, arr.length);
    }

    private static void mergeSort(int[] arr, int n) {
        if (n < 2)
            return;

        int mid = n / 2;
        int[] leftArr = new int[mid];
        int[] rightArr = new int[n - mid];

        //System.arraycopy(arr,0, leftArr, 0, mid);
        //System.arraycopy(arr, mid, rightArr, 0, n-mid);

        for (int i = 0; i < mid; i++)
            leftArr[i] = arr[i];

        for (int j = mid; j < n; j++)
            rightArr[j - mid] = arr[j];


        mergeSort(leftArr, mid);
        mergeSort(rightArr, n - mid);
        merge(leftArr, rightArr, arr, mid, n - mid);
    }

    private static void merge(int[] l_arr, int[] r_arr, int[] arr,
                              int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l_arr[i] < r_arr[j]) {
                arr[k] = l_arr[i];
                i++;
            } else {
                arr[k] = r_arr[j];
                j++;
            }
            k++;
        }

        //if right array runs out and
        // there are element left in left array
        while (i < left) {
            arr[k] = l_arr[i];
            i++;
            k++;
        }

        //if left array runs out and
        // there are element left in right array
        while (j < right) {
            arr[k] = r_arr[j];
            j++;
            k++;
        }


    }

    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7, 2, 6};

        mergeSort(arr);
        ArrayUtilities.printArray(arr);
    }
}
