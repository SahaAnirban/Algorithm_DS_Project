package com.anisaha.adt.stack;

import java.util.NoSuchElementException;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class StackInArray {
    protected int top, size, len;
    protected int[] arr;

    public StackInArray(int size) {
        this.arr = new int[size];
        this.size = size;
        len = 0;
        top = -1;
    }

    public int pop() {
        if (isEmpty())
            throw new NoSuchElementException("Underflow exception");

        len--;
        return arr[top--];
    }

    public void push(int ele) {
        if (top + 1 >= size)
            throw new IndexOutOfBoundsException("Overflow exception");

        if (top + 1 < size)
            arr[++top] = ele;

        len++;
    }

    public int peek() {
        if (isEmpty())
            throw new NoSuchElementException("Underflow exception");

        return arr[top];
    }

    public boolean isEmpty() {
        return len == 0;
    }

    public String toString() {
        String response = "";
        for (int i = top; i >= 0; i--)
            response = response.concat(arr[i] + " ");

        return response;
    }
}
