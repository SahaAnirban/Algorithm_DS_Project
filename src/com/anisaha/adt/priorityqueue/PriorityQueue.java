package com.anisaha.adt.priorityqueue;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public interface PriorityQueue<K, V> {
    int size();

    boolean isEmpty();

    Entry<K, V> insert(K key, V value) throws IllegalArgumentException;

    Entry<K, V> getMin();

    Entry<K, V> extractMin();
}
