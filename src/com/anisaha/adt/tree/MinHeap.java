package com.anisaha.adt.tree;

import com.anisaha.utilities.ArrayUtilities;

public class MinHeap {
    private int[] heapArr;
    private int size;
    private int capacity;

    private static final int FRONT = 1;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        heapArr = new int[this.capacity + 1]; //remove 1 based indexing
        heapArr[0] = Integer.MIN_VALUE;
    }

    private int getParentIndex(int posIndex) {
        return posIndex / 2;
    }

    private int getLeftChildIndex(int posIndex) {
        return (2 * posIndex);
    }

    private int getRightChildIndex(int posIndex) {
        return (2 * posIndex) + 1;
    }

    private boolean isLeafNode(int posIndex) {
        if (posIndex >= (size / 2) && posIndex <= size)
            return true;

        return false;
    }

    public void insert(int element) {
        heapArr[++size] = element;
        int current = size;

        while (heapArr[current] < heapArr[getParentIndex(current)]) {
            ArrayUtilities.swap(heapArr, current, getParentIndex(current));
            current = getParentIndex(current);
        }
    }

    public void buildMinHeap() {
        for (int pos = size / 2; pos >= 1; pos--)
            min_heapify(pos);
    }

    private void min_heapify(int position) {
        if (!isLeafNode(position)) {
            if (heapArr[position] > heapArr[getLeftChildIndex(position)]
                    || heapArr[position] > heapArr[getRightChildIndex(position)]) {
                if (heapArr[getLeftChildIndex(position)] < heapArr[getRightChildIndex(position)]) {
                    ArrayUtilities.swap(heapArr, position, getLeftChildIndex(position));
                    min_heapify(getLeftChildIndex(position));
                } else {
                    ArrayUtilities.swap(heapArr, position, getRightChildIndex(position));
                    min_heapify(getRightChildIndex(position));
                }
            }
        }
    }

    public int extractMin() {
        int popped = heapArr[FRONT];
        heapArr[FRONT] = heapArr[size--];
        min_heapify(FRONT);
        return popped;
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(15);
        minHeap.insert(5);
        minHeap.insert(12);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);
        minHeap.buildMinHeap();

        System.out.println("The min value is : " + minHeap.extractMin());
    }
}
