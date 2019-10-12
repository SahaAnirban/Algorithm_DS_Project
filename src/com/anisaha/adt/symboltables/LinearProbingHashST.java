package com.anisaha.adt.symboltables;

public class LinearProbingHashST<Key, Value> {
    private int size;
    private int capacity;
    private Key[] keys;
    private Value[] values;
    private static final int INITIAL_CAPACITY = 16;

    public LinearProbingHashST() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashST(int capacityParam) {
        this.size = 0;
        this.capacity = capacityParam;
        keys = (Key[]) new Object[capacityParam];
        values = (Value[]) new Object[capacityParam];
    }

    public void put(Key key, Value value) {
        if (size >= capacity / 2)
            resize(2 * capacity);

        // key already exists
        int i = 0;
        for (i = hash(key); keys[i] != null; i = (i + 1) % capacity)
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }

        // add new key
        keys[i] = key;
        values[i] = value;
        size++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % capacity)
            if (keys[i].equals(key))
                return values[i];

        return null;
    }

    public void delete(Key key) {
        if (!contains(key))
            return;

        //find position i of the key
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % capacity;

        //delete key and associated value
        keys[i] = null;
        values[i] = null;

        //re-hash all the keys in same cluster
        i = (i + 1) % capacity;
        while (keys[i] != null) {
            //delete key-value and re-insert
            Key keyToRehash = keys[i];
            Value valueToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            size--;
            put(keyToRehash, valueToRehash);
            i = (i + 1) % capacity;
        }

        size--;

        //half the capacity if its 1/8 full or less
        if (size > 0 && size <= capacity / 8)
            resize(capacity / 2);
    }

    public int size() {
        return size;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private void resize(int toCapacity) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(toCapacity);
        for (int i = 0; i < capacity; i++)
            if (keys[i] != null)
                t.put(keys[i], values[i]);

        keys = t.keys;
        values = t.values;
        capacity = t.capacity;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

}
