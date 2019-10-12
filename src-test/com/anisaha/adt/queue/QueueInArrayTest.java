package com.anisaha.adt.queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueInArrayTest {

    @Test
    public void testIsEmpty() {
        QueueInArray arrayQueue = new QueueInArray(3);
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testIsFull() {
        try {
            QueueInArray arrayQueue = new QueueInArray(2);
            arrayQueue.enqueue(1);
            arrayQueue.enqueue(2);

            assertTrue(arrayQueue.isFull());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testEnqueue() {
        QueueInArray arrayQueue = new QueueInArray(2);
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);

        assertEquals(arrayQueue.dequeue(), 1);
    }

    @Test
    public void testDequeue() {
        QueueInArray arrayQueue = new QueueInArray(2);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);

        assertEquals(arrayQueue.dequeue(), 2);
    }

    @Test
    public void testSize() {
        QueueInArray arrayQueue = new QueueInArray(2);
        arrayQueue.enqueue(1);

        assertEquals(arrayQueue.size(), 1);
    }
}