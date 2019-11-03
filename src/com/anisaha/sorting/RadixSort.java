package com.anisaha.sorting;

import com.anisaha.utilities.ArrayUtilities;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class RadixSort {
    private static void count_sort(int[] arr, int n, int multiplier) {
        int[] outArr = new int[n];
        int[] countAux = new int[10]; // k is generally the largest element, k = 10

        for (int i = 0; i < n; i++)
            countAux[(arr[i] / multiplier) % 10]++;

        for (int i = 1; i < 10; i++)
            countAux[i] += countAux[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            outArr[countAux[(arr[i] / multiplier) % 10] - 1] = arr[i];
            countAux[(arr[i] / multiplier) % 10]--;
        }

        System.arraycopy(outArr, 0, arr, 0, outArr.length);
    }

    public static void radix_sort(int[] arr, int n, int maxElement) {
        for (int exp = 1; maxElement / exp > 0; exp *= 10)
            count_sort(arr, n, exp);
    }

    public static void main(String[] args) {
        int arr[] = { 10, 21, 17, 34, 44, 11, 654, 123 };
        int maxElement = Arrays.stream(arr).boxed().max(Comparator.naturalOrder()).get();

        radix_sort(arr, arr.length, maxElement);
        ArrayUtilities.printArray(arr);
    }
}
