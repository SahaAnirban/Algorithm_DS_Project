package com.anisaha.searching;

import java.util.Arrays;

public class ExponentialSearch {
    public static int exponentialSearch(int arr[], int key) {
        int len = arr.length;

        if (arr[0] == key)
            return 0;

        int bound = 1;
        while (bound < len && arr[bound] <= key)
            bound *= 2;

        return Arrays.binarySearch(arr, bound / 2, Math.min(bound, len), key);
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, 4, 10, 40};
        int result = exponentialSearch(arr, 1);

        if (result > 0)
            System.out.println("Element found in index: " + result);
        else
            System.out.println("Element not found, suitable index can be: " + Math.abs(result + 1));

    }
}
