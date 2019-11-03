package com.anisaha.searching;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class InterPolationSearch {
    private static int interpolationSearch(int[] arr, int value) {
        int low = 0, high = arr.length - 1;

        while (low <= high && value >= arr[low] && value <= arr[high]) {
            //uniform distribution is needed in input array
            int pos = low + (((high - low) / (arr[high] - arr[low])) * (value - arr[low]));

            if (arr[pos] == value)
                return pos;

            // If value is larger, value is in upper part
            if (arr[pos] < value)
                low = pos + 1;
            else
                high = pos - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int arr[] = {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24, 33, 35, 42, 47};
        int index = interpolationSearch(arr, 18);

        if (index != -1)
            System.out.println("Element found in index: " + index);
        else
            System.out.println("Element not found");
    }
}