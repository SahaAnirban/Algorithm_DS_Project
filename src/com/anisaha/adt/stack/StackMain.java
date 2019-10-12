package com.anisaha.adt.stack;

public class StackMain {
    private StackMain() {
    }

    public static void main(String[] args) {
        StackInArray arrayStack = new StackInArray(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);

        System.out.println("Pop-ed: " + arrayStack.pop());
        System.out.println("Peeked-ed: " + arrayStack.peek());
        System.out.println(arrayStack);

        StackInList listStack = new StackInList();
        listStack.push(5);
        listStack.push(6);
        listStack.push(7);
        listStack.push(8);
        listStack.push(9);

        System.out.println("Pop-ed: " + listStack.pop());
        System.out.println("Peeked-ed: " + listStack.peek());
        listStack.printStack();
    }
}
