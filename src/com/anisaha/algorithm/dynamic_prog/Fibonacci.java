package com.anisaha.algorithm.dynamic_prog;

/**
 * Demo for fibonacci series coded with dynamic programming
 * @author Anirban Saha (astle.theauthor@gmail.com) 
 */
public class Fibonacci {
    private static int fibonacci(int index) {
        if (index <= 1)
            return index;
        else
            return fibonacci(index - 1) + fibonacci(index - 2);
    }

    private static final int[] lookup = new int[100]; // initialized to 0

    private static int fibonacciTopDown(int fib_index) {
        if (lookup[fib_index] == 0) {
            if (fib_index <= 1)
                lookup[fib_index] = fib_index;
            else
                lookup[fib_index] = fibonacciTopDown(fib_index - 1) + fibonacciTopDown(fib_index - 2);
        }

        return lookup[fib_index];
    }

    private static int fibonacciBottomUp(int fib_index) {
        int[] store = new int[fib_index + 1];
        store[0] = 0;
        store[1] = 1;

        for (int i = 2; i <= fib_index; i++)
            store[i] = store[i - 1] + store[i - 2];

        return store[fib_index];
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(5));
        System.out.println(fibonacciTopDown(5));
        System.out.println(fibonacciBottomUp(5));
    }
}
