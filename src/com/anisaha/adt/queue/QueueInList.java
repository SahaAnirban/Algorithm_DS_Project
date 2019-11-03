package com.anisaha.adt.queue;

import com.anisaha.adt.linkedlist.SLinkedList;

import java.util.NoSuchElementException;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class QueueInList {
    protected SLinkedList list = new SLinkedList();

    public int dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Underflow Exception");

        int headValue = list.getHeadValue();
        list.remove(headValue);
        return headValue;
    }

    public void enqueue(int data) {
        list.addLast(data);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public void printQueue() {
        list.printSLL();
    }
}
