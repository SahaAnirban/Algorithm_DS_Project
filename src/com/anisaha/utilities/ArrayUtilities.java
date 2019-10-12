package com.anisaha.utilities;

import java.util.Arrays;
import java.util.stream.Stream;

public class ArrayUtilities {
    private ArrayUtilities() { }

    public static void printArray(int[] array) {
        for (int value : array) System.out.print(value + ", ");
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void print2DArray(int[][] array) {
        Stream.of(array).map(Arrays::toString).forEach(System.out::println);
    }
}
