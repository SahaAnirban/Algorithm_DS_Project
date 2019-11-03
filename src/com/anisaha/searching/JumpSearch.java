package com.anisaha.searching;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class JumpSearch {
    private static int jumpSearch(int arr[], int value) {
        int len = arr.length;
        int step = (int) Math.floor(Math.sqrt(len));

        int prev = 0;
        while (arr[Math.min(step, len) - 1] < value) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(len));

            if (prev >= len)
                return -1;
        }

        while (arr[prev] < value) {
            prev++;

            if (prev == Math.min(step, len))
                return -1;
        }

        if (arr[prev] == value)
            return prev;

        return -1;
    }


    public static void main(String[] args) {
        int arr[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610};
        System.out.println("The index of element being searched is : " + jumpSearch(arr, 13));
    }
}
