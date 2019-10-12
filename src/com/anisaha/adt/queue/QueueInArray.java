package com.anisaha.adt.queue;

import java.util.NoSuchElementException;

public class QueueInArray {
    private int front, rear, size;
    private int[] arr;
    private int capacity;

    public QueueInArray(int capacity) {
        arr = new int[capacity];
        this.front = this.size = 0;
        this.rear = capacity - 1;
        this.capacity = capacity;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }

    public void enqueue(int ele) {
        if (this.isFull())
            throw new IndexOutOfBoundsException("Overflow exception");

        // circular queue indexing
        rear = (rear + 1) % capacity;
        arr[rear] = ele;
        size++;
    }

    public int dequeue() {
        if (this.isEmpty())
            throw new NoSuchElementException("Underflow exception");

        int item = arr[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    public int size() {
        return size;
    }


    public void printQueue() {
        if (rear >= front) {
            for (int i = front; i <= rear; i++)
                System.out.printf("%d ", arr[i]);
        } else {
            for (int i = front; i < size; i++)
                System.out.printf("%d ", arr[i]);

            for (int i = 0; i <= rear; i++)
                System.out.printf("%d ", arr[i]);
        }
    }
}
