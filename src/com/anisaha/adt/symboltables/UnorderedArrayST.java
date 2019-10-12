package com.anisaha.adt.symboltables;

/* Robert Sedgewick implementation*/
public class UnorderedArrayST<Key, Value> {
    private static final int INITIAL_CAPACITY = 8;

    private Key[] keys;
    private Value[] values;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public UnorderedArrayST() {
        keys = (Key[]) new Object[INITIAL_CAPACITY];
        values = (Value[]) new Object[INITIAL_CAPACITY];
    }

    public void put(Key key, Value value) {
        if (value == null) {
            remove(key);
            return;
        }

        if (size >= values.length)
            resize(2 * size);

        // if key already exist overwrite the value
        for (int i = 0; i < size; i++) {
            if (key.equals(keys[i])) {
                values[i] = value;
                return;
            }
        }

        keys[size] = key;
        values[size] = value;
        size++;
    }

    public Value get(Key key) {
        for (int i = 0; i < size; i++)
            if (keys[i].equals(key))
                return values[i];

        return null;
    }

    public void remove(Key key) {
        // copying the last element to delete index
        // nulling the last value and decreasing the size
        for (int i = 0; i < size; i++) {
            if (key.equals(keys[i])) {
                keys[i] = keys[size - 1];
                values[i] = values[size - 1];

                keys[size - 1] = null;
                values[size - 1] = null;
                size--;
                return;
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize(int toCapacity) {
        Key[] newKeys = (Key[]) new Object[toCapacity];
        Value[] newValues = (Value[]) new Object[toCapacity];

        for (int i = 0; i < size; i++)
            newKeys[i] = keys[i];

        for (int i = 0; i < size; i++)
            newValues[i] = values[i];

        keys = newKeys;
        values = newValues;
    }

}
