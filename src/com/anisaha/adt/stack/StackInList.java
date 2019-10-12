package com.anisaha.adt.stack;

import com.anisaha.adt.linkedlist.SLinkedList;

import java.util.NoSuchElementException;

public class StackInList {
    protected SLinkedList list = new SLinkedList();

    public int pop() {
        if (isEmpty())
            throw new NoSuchElementException("Underflow Exception");

        int topValue = list.getHeadValue();
        list.remove(topValue);
        return topValue;
    }

    public int peek() {
        if (isEmpty())
            throw new NoSuchElementException("Underflow Exception");

        return list.getHeadValue();
    }

    public void push(int ele) {
        list.addFirst(ele);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public void printStack() {
        list.printSLL();
    }

}
