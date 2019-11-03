package com.anisaha.adt.linkedlist;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class DLinkedList {
    Node head;
    private int size = 0;

    private class Node {
        Node prev, next;
        int data;

        Node(int data) {
            this.data = data;
        }
    }

    public void addFirst(int data) {
        Node node = new Node(data);

        node.next = head;
        // node.prev = null; by default

        if (head != null)
            head.prev = node;

        head = node;
        size++;
    }

    public void addLast(int data) {
        Node node = new Node(data);
        Node last = head;

        if (head == null) {
            head = node;
            return;
        }

        while (last.next != null)
            last = last.next;

        last.next = node;
        node.prev = last;
        size++;
    }

    public void addAfter(int index, int data) {
        if (index + 1 > size)
            throw new IllegalArgumentException("index greater than size");

        Node node = new Node(data);

        Node h = head;
        for (int i = 0; i < index && h.next != null; i++) {
            h = h.next;
        }

        node.prev = h.prev;
        node.next = h;

        h.prev.next = node;
        h.prev = node;
        size++;
    }

    public void remove(int data) {
        Node dNode = get(data);

        if (head == null || dNode == null)
            return;

        // deleting element is the head
        if (head == dNode)
            head = dNode.next;

        // changing next Node only if node
        // to be deleted is not the last node
        if (dNode.next != null)
            dNode.next.prev = dNode.prev;

        // Changing prev Node only if node
        // to be deleted is not the first node
        if (dNode.prev != null)
            dNode.prev.next = dNode.next;

        size--;
    }

    public int size() {
        return size;
    }

    public Node get(int data) {
        Node h = head;
        while (h != null) {
            if (h.data == data)
                return h;
            else
                h = h.next;
        }
        return null;
    }

    public void printDLL() {
        Node h = head;
        while (h != null) {
            System.out.print(h.data + " ");
            h = h.next;
        }
    }
}
