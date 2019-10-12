package com.anisaha.adt.priorityqueue;

public interface Entry<K, V> {
    K getKey();

    V getValue();
}
