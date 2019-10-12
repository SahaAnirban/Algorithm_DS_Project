package com.anisaha.adt.priorityqueue;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {
    protected static class PQEntity<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public PQEntity(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        protected void setKey(K key) {
            this.key = key;
        }

        protected void setValue(V value) {
            this.value = value;
        }
    }

    private Comparator<V> comp;

    protected AbstractPriorityQueue(Comparator<V> c) {
        comp = c;
    }

    @SuppressWarnings({"unchecked"})
    protected AbstractPriorityQueue() {
        this((V a, V b) -> ((Comparable<V>) a).compareTo(b));
    }

    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getValue(), b.getValue());
    }

    protected boolean checkKey(V key) throws IllegalArgumentException {
        try {
            return (comp.compare(key, key) == 0);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
