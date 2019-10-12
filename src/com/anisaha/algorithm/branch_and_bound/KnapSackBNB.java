package com.anisaha.algorithm.branch_and_bound;

import java.io.*;
import java.util.*;

public class KnapSackBNB {
    private int inputCount, bagCapacity;
    private int maxValue, bagWeight;
    private Item[] items;
    private int[] fixedSizeSolution;
    private PriorityQueue<Node> pq;
    private Node root;

    public KnapSackBNB(List<Item> items, int itemsCount, int bagCapacity) {
        Item[] tempArr = new Item[itemsCount];
        
        this.items = new Item[itemsCount + 1];
        this.pq = new PriorityQueue<>();
        this.inputCount = itemsCount;
        this.bagCapacity = bagCapacity;
        this.maxValue = 0;
        this.bagWeight = 0;

        System.arraycopy(items.toArray(), 0, tempArr, 0, itemsCount);
        Arrays.sort(tempArr, (Item a, Item b) -> {
            double r1 = a.costRatio;
            double r2 = b.costRatio;
            if (r1 == r2)
                return 0;
            else
                return r1 > r2 ? -1 : 1;
        });
        System.arraycopy(tempArr, 0, this.items, 1, itemsCount);
        this.items[0] = null; // root node is empty

        root = new Node();
        root.nodeWeight = 0;
        root.value = 0;
        root.upperBound = bagCapacity * (float) this.items[1].value / (float) this.items[1].itemWeight;
        root.index = 0;
        pq.add(root);
    }

    public void branchAndBound() {
        Node current, left, right;
        while (pq.size() > 0) {
            current = pq.poll();
            if (current.upperBound > maxValue) {
                // including the left node
                left = new Node();
                left.index = current.index + 1;
                left.nodeWeight = current.nodeWeight + items[left.index].itemWeight;
                left.value = current.value + items[left.index].value;
                left.solution = addSolution(current.solution, left.solution, left.index);
                left.upperBound = calculateUpperBound(left);

                // add to solution if greater than maxValue
                if (left.nodeWeight <= bagCapacity && left.value > maxValue) {
                    maxValue = left.value;
                    bagWeight = left.nodeWeight;
                    fixedSizeSolution = left.solution;
                }

                if (left.upperBound > maxValue)
                    pq.offer(left);

                right = new Node();
                right.index = current.index + 1;
                right.nodeWeight = current.nodeWeight;
                right.value = current.value;
                right.solution = createCopy(current.solution);
                right.upperBound = calculateUpperBound(right);
                if (right.upperBound > maxValue)
                    pq.offer(right);
            }
        }

    }

    private int[] createCopy(int[] solution) {
        int[] copy = null;
        if (solution != null) {
            copy = new int[solution.length];
            System.arraycopy(solution, 0, copy, 0, solution.length);
        }

        return copy;
    }

    private float calculateUpperBound(Node node) {
        float ub = node.value;
        int j, currWeight = node.nodeWeight;

        if (node.nodeWeight > bagCapacity)
            return 0; // 0 upper bound will be ignored
        else {
            // max number of remaining elements that can fit
            for (j = node.index + 1; currWeight <= bagCapacity && j < inputCount + 1; j++) {
                currWeight += items[j].itemWeight;
                ub += items[j].value;
            }

            // add maximum value from remaining capacity
            if (j < inputCount + 1)
                ub += (float) (bagCapacity - currWeight) * ((float) items[j].value / (float) items[j].itemWeight);

            return ub;
        }
    }

    private int[] addSolution(int[] source, int[] destination, int index) {
        destination = new int[index + 1];
        destination[index] = 1;
        if (source != null)
            System.arraycopy(source, 0, destination, 0, source.length);

        return destination;
    }

    public void printSolution() {
        List<Item> sortedItems = new ArrayList<>();
        for (int i = 1; i < fixedSizeSolution.length; i++) {
            if (fixedSizeSolution[i] == 1)
                sortedItems.add(items[i]);
        }

        Collections.sort(sortedItems);
        System.out.println("value = " + maxValue + ", weight = " + bagWeight);
        System.out.println(Arrays.toString(sortedItems.toArray()));
    }

    public static void main(String[] args) {
        InputParams inputParams = readItemsFromFile("src/com/anisaha/algorithm/branch_and_bound/ItemsInput.txt");

        KnapSackBNB driver = new KnapSackBNB(inputParams.items, inputParams.items.size(), inputParams.bagCapacity);
        driver.branchAndBound();
        driver.printSolution();
    }

    private static InputParams readItemsFromFile(String fileName) {
        List<Item> items = new ArrayList<>();
        Item tempItem;
        int inputCount;
        InputParams inputParams = null;

        // System.out.println(new File(".").getAbsoluteFile());
        // path where program is checking the file
        try (Scanner fileIn = new Scanner(new FileInputStream(new File(fileName)))) {
            inputCount = fileIn.nextInt();

            for (int i = 0; i < inputCount; i++) {
                tempItem = new Item(fileIn.nextInt(), fileIn.nextInt(), fileIn.nextInt());
                items.add(tempItem);
            }

            int bagCapacity = fileIn.nextInt();
            inputParams = new InputParams(items, bagCapacity);
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " file not found in current directory");
        }
        return inputParams;
    }

    private static class InputParams {
        public int bagCapacity;
        public List<Item> items;

        public InputParams(List<Item> items, int bagCapacity) {
            this.items = items;
            this.bagCapacity = bagCapacity;
        }
    }

    private static class Node implements Comparable<Node> {
        int[] solution;
        int nodeWeight;
        int value;
        int index;
        float upperBound;

        @Override
        public int compareTo(Node other) {
            if (this.upperBound == other.upperBound)
                return 0;
            else if (this.upperBound < other.upperBound)
                return 1;
            else
                return -1;
        }
    }

    private static class Item implements Comparable<Item> {
        public int itemWeight, value, index;
        public float costRatio;

        public Item(int index, int value, int weight) {
            this.index = index;
            this.itemWeight = weight;
            this.value = value;
            this.costRatio = ((float) value / (float) weight);
        }

        @Override
        public int compareTo(Item other) {
            if (this.index == other.index)
                return 0;
            else if (this.index < other.index)
                return -1;
            else
                return 1;
        }

        @Override
        public String toString() {
            return "Item weight: " + itemWeight + ", value: " + value;
        }
    }
}