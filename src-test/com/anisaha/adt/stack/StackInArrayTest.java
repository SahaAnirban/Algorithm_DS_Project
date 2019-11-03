package com.anisaha.adt.stack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class StackInArrayTest {

    @Test
    public void testPop() {
        StackInArray arrayStack = new StackInArray(3);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);

        assertTrue(4 == arrayStack.pop());
        assertTrue(3 == arrayStack.pop());
        assertTrue(2 == arrayStack.pop());
    }

    @Test
    public void testPush() {
        StackInArray arrayStack = new StackInArray(3);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);

        assertFalse(arrayStack.isEmpty());
    }

    @Test
    public void peek() {
        StackInArray arrayStack = new StackInArray(3);
        arrayStack.push(2);

        assertTrue(arrayStack.peek() == 2 && !arrayStack.isEmpty());
    }

    @Test
    public void isEmpty() {
        StackInArray arrayStack = new StackInArray(3);
        assertTrue(arrayStack.isEmpty());
    }
}