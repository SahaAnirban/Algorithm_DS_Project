package com.anisaha.adt.symboltables;

/**
 * Robert Sedgewick implementation, Un-ordered LinkedList based implementation
 * of Symbol Tables
 * 
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class SeparateChainingHashST<Key, Value> {
    private static final int INITIAL_CAPACITY = 4;

    private int size;
    private int capacity;
    private Node[] buckets;

    private static class Node {
        private final Object key;
        private Object value;
        private Node next;

        public Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public SeparateChainingHashST() {
        this(INITIAL_CAPACITY);
    }

    private SeparateChainingHashST(int capacity) {
        this.capacity = capacity;
        this.size = 0;

        buckets = new Node[capacity];
    }

    @SuppressWarnings("unchecked")
    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to get is null");

        int i = getHash(key);
        for (Node x = buckets[i]; x != null; x = x.next)
            if (key.equals(x.key))
                return (Value) x.value;

        return null;
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException("argument to put is null");

        if (value == null) {
            remove(key);
            return;
        }

        if ((double) size / capacity >= 0.75)
            resize(2 * capacity);

        int i = getHash(key);
        for (Node x = buckets[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }

        size++;
        buckets[i] = new Node(key, value, buckets[i]);
    }

    public void remove(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to remove is null");

        int i = getHash(key);
        buckets[i] = remove(buckets[i], key);

        // halve the capacity of the table
        if (capacity > INITIAL_CAPACITY && size <= 2 * capacity)
            resize(capacity / 2);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to get is null");

        return get(key) != null;
    }

    // remove key in linked list beginning node startNode
    private Node remove(Node startNode, Key key) {
        if (startNode == null)
            return null;

        if (key.equals(startNode.key)) {
            size--;
            return startNode.next;
        }

        startNode.next = remove(startNode.next, key);
        return startNode;
    }

    @SuppressWarnings("unchecked")
    private void resize(int toCapacity) {
        SeparateChainingHashST<Key, Value> st = new SeparateChainingHashST<>(toCapacity);
        for (int i = 0; i < capacity; i++) {
            for (Node x = buckets[i]; x != null; x = x.next)
                st.put((Key) x.key, (Value) x.value);
        }

        this.size = st.size;
        this.capacity = st.capacity;
        this.buckets = st.buckets;
    }

    private int getHash(Key key) {
        return key.hashCode() % capacity;
    }
}
