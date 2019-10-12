package com.anisaha.adt.symboltables;

public class OrderedArrayST<Key extends Comparable<Key>, Value> {
    private static final int INITIAL_CAPACITY = 8;

    private Key[] keys;
    private Value[] values;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public OrderedArrayST() {
        keys = (Key[]) new Comparable[INITIAL_CAPACITY];
        values = (Value[]) new Object[INITIAL_CAPACITY];
    }

    public void put(Key key, Value value) {
        if (size > values.length)
            resize(2 * size);

        //key already exists
        if (contains(key)) {
            int index = binarySearch(key);
            values[index] = value;
            return;
        }

        int i = size;
        while (i > 0 && (key.compareTo(keys[i - 1]) < 0)) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
            i--;
        }
        values[i] = value;
        keys[i] = key;
        size++;
    }

    public Value get(Key key) {
        int index = binarySearch(key);
        if (index == -1)
            return null;

        return values[index];
    }

    private boolean contains(Key key) {
        int index = binarySearch(key);
        return index >= 0;
    }

    private int binarySearch(Key key) {
        int low = 0, high = size - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comp = key.compareTo(keys[mid]);
            if (comp < 0)
                high = mid - 1;
            else if (comp > 0)
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    private void resize(int toCapacity) {
        Key[] tempK = (Key[]) new Comparable[toCapacity];
        Value[] tempV = (Value[]) new Object[toCapacity];

        for (int i = 0; i < size; i++) {
            tempK[i] = keys[i];
            tempV[i] = values[i];
        }

        keys = tempK;
        values = tempV;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
