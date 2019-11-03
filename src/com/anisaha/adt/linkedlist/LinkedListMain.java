package com.anisaha.adt.linkedlist;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class LinkedListMain {
    public static void main(String[] args) {
        testSinglyLinkedList();
        testDoubleLinkedList();
    }

    private static void testDoubleLinkedList() {
        DLinkedList dlist = new DLinkedList();
        dlist.addFirst(1);
        dlist.addFirst(2);
        dlist.addLast(3);
        dlist.addAfter(2, 9);
        dlist.remove(1);
        dlist.printDLL();

        System.out.println("\nThe size of list is: " + dlist.size());
    }

    private static void testSinglyLinkedList() {
        SLinkedList list = new SLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addLast(4);
        list.addLast(4);

        System.out.println("The size of list is: " + list.size());
        list.printSLL();

        System.out.println();
        list.remove(2);
        list.printSLL();

        System.out.println("\nThe list contains 6? " + list.contains(6));
    }
}
