package com.anisaha.searching;

public class FibonacciSearch {
    public static int fibonacciSearch(int[] arr, int key) {
        int fib_m2 = 0; // (m-2)th fib no.
        int fib_m1 = 1; // (m-1)th fib no.
        int fib = fib_m1 + fib_m2; // m_th fib no.
        int len = arr.length;

        //Fibonacci Number greater than or equal to n
        while (fib < len) {
            fib_m2 = fib_m1;
            fib_m1 = fib;
            fib = fib_m1 + fib_m2;
        }

        int offset = -1;
        /* we compare arr[fib_m2] with key
        When fib becomes 1, fib_m2 becomes 0 */
        while (fib > 1) {
            int i = Math.min(offset + fib_m2, len - 1);

            // cut the subarray array from offset to i
            if (arr[i] < key) {
                fib = fib_m1;
                fib_m1 = fib_m2;
                fib_m2 = fib - fib_m1;
                offset = i;
            } else if (arr[i] > key) { // cut the subarray after i+1
                fib = fib_m2;
                fib_m1 = fib_m1 - fib_m2;
                fib_m2 = fib - fib_m1;
            } else
                return i;
        }

        if (fib_m1 == 1 && arr[offset + 1] == key)
            return offset + 1;

        return -1;
    }

    public static void main(String[] args) {
        int arr[] = {10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100};
        int result = fibonacciSearch(arr, 10);

        if (result != -1)
            System.out.println("Element found in index: " + result);
        else
            System.out.println("Element not found");
    }
}
