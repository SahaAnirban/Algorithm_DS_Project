package com.anisaha.adt.queue;

public class QueueMain {
    public static void main(String[] args) {
        QueueInArray arrayQueue = new QueueInArray(5);
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);
        arrayQueue.enqueue(4);
        arrayQueue.enqueue(5);

        System.out.println("dequeue-ed: " + arrayQueue.dequeue());
        arrayQueue.printQueue();

        System.out.println();

        QueueInList listQueue = new QueueInList();
        listQueue.enqueue(3);
        listQueue.enqueue(4);
        listQueue.enqueue(5);
        listQueue.enqueue(6);
        listQueue.enqueue(7);

        System.out.println("dequeue-ed: " + listQueue.dequeue());
        listQueue.printQueue();
    }
}
