package com.anisaha.adt.linkedlist;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class SLinkedList {
    protected Node head;
    private int size = 0;

    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    // insert element at the front of linked list
    public void addFirst(int data) {
        Node node = new Node(data);

        node.next = head;
        head = node;
        size++;
    }

    // insert element at the end of the linked list
    public void addLast(int data) {
        Node node = new Node(data);

        if (head == null) {
            head = node;
            return;
        }

        Node last = head;
        while (last.next != null)
            last = last.next;

        last.next = node;
        // initialized to null by default
        // node.next = null;
        size++;
    }

    public void remove(int data) {
        Node dNode = get(data);

        // head node contains the data
        if (dNode == head) {
            head = dNode.next;
            size--;
            return;
        }

        // get before node
        Node temp = head, before = null;
        while (temp != null && temp.data != data) {
            before = temp;
            temp = temp.next;
        }

        // if temp is null data not present in linked list
        if (temp == null)
            return;

        before.next = temp.next;
        size--;
    }

    public int size() {
        return size;
    }

    public boolean contains(int data) {
        boolean contains = false;

        if (get(data) != null)
            contains = true;

        return contains;
    }

    private Node get(int data) {
        Node h = head;
        while (h != null) {
            if (h.data == data)
                return h;

            h = h.next;
        }
        return null;
    }

    public void printSLL() {
        Node h = head;
        while (h != null) {
            System.out.print(h.data + " ");
            h = h.next;
        }
    }

    // non-standard method to get head of linked list
    public int getHeadValue() {
        return head.data;
    }
}
