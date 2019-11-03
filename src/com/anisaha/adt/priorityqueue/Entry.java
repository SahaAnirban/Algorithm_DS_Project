package com.anisaha.adt.priorityqueue;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public interface Entry<K, V> {
    K getKey();

    V getValue();
}
