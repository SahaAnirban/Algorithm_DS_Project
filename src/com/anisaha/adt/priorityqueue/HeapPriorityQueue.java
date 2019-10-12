package com.anisaha.adt.priorityqueue;

import java.util.*;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    protected List<Entry<K, V>> heap = new ArrayList<>();

    public HeapPriorityQueue() {
        super();
    }

    public HeapPriorityQueue(Comparator<V> comp) {
        super(comp);
    }

    public HeapPriorityQueue(K[] keys, V[] values) {
        super();
        for (int j = 0; j < Math.min(keys.length, values.length); j++)
            heap.add(new PQEntity<>(keys[j], values[j]));

        buildHeap();
    }

    private void buildHeap() {
        int startIndex = parent(size() - 1); // start with parent of last entry
        for (int j = startIndex; j >= 0; j--)   // loop till the root
            bubbleDownHeap(j);
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(value);

        Entry<K, V> newEntry = new PQEntity<>(key, value);
        heap.add(newEntry);
        bubbleUpHeap(heap.size() - 1);

        return newEntry;
    }

    @Override
    public Entry<K, V> getMin() {
        if (heap.isEmpty())
            return null;

        return heap.get(0);
    }

    @Override
    public Entry<K, V> extractMin() {
        if (heap.isEmpty())
            return null;

        Entry<K, V> min = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        bubbleDownHeap(0);

        return min;
    }

    protected int parent(int j) {
        return (j - 1) / 2;
    }

    protected int leftChild(int j) {
        return 2 * j + 1;
    }

    protected int rightChild(int j) {
        return 2 * j + 2;
    }

    protected boolean hasLeft(int j) {
        return leftChild(j) < heap.size();
    }

    protected boolean hasRight(int j) {
        return rightChild(j) < heap.size();
    }

    protected void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @Override
    public int size() {
        return heap.size();
    }

    /* Move element higher to maintain the heap property */
    protected void bubbleUpHeap(int j) {
        while (j > 0) { //continue till reaching the root
            int p = parent(j);

            // min-heap property verified
            if (compare(heap.get(j), heap.get(p)) >= 0)
                break;

            swap(j, p);
            j = p;
        }
    }

    /* Move element lower in tree to maintain the heap property */
    protected void bubbleDownHeap(int j) {
        while (hasLeft(j)) { // continue till reaching bottom of tree
            int leftIndex = leftChild(j);
            int smallChildIdx = leftIndex;

            if (hasRight(j)) {
                int rightIndex = rightChild(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                    smallChildIdx = rightIndex;
            }

            if (compare(heap.get(smallChildIdx), heap.get(j)) >= 0)
                break;

            swap(j, smallChildIdx);
            j = smallChildIdx;
        }
    }

    /* check Heap integrity for min-heap property */
    private void checkHeapIntegrity() {
        for (int j = 0; j < heap.size(); j++) {
            int left = leftChild(j);
            int right = rightChild(j);

            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0)
                System.out.println("Left child relationship is invalid");

            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0)
                System.out.println("Right child relationship is invalid");

        }

    }

    public static void main(String[] args) {
        String[] task = new String[]{"a", "b", "c", "d", "e"};
        Integer[] priorities = new Integer[]{2, 3, 4, 5, 6};

        PriorityQueue<String, Integer> queue = new HeapPriorityQueue<>(task, priorities);
        ((HeapPriorityQueue<String, Integer>) queue).checkHeapIntegrity();

        queue.insert("f", 1);
        queue.insert("g", 6);

        System.out.println("The minimum element in the PQ is: " + queue.getMin().getValue());
        queue.extractMin();
        System.out.println("The minimum element in the PQ after removal is: " + queue.getMin().getValue());
    }
}
