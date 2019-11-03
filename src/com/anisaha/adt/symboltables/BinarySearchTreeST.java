package com.anisaha.adt.symboltables;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class BinarySearchTreeST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument is null");

        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null)
            return null;

        int compare = key.compareTo(node.key);
        if (compare < 0)
            return get(node.left, key);
        else if (compare > 0)
            return get(node.right, key);
        else
            return node.value;
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException("argument to put is null");

        if (value == null) {
            delete(key);
            return;
        }

        root = put(root, key, value);
        // validate integrity of a BST rules in maintained - omitted here
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null)
            return new Node(key, value, 1);

        int compare = key.compareTo(node.key);
        if (compare < 0)
            node.left = put(node.left, key, value);
        else if (compare > 0)
            node.right = put(node.right, key, value);
        else
            node.value = value; // override the existing value

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to delete is null");

        root = delete(root, key);
        // validate integrity of a BST rules in maintained - omitted here
    }

    private Node delete(Node node, Key key) {
        if (node == null)
            return null;

        int compare = key.compareTo(node.key);
        if (compare < 0)
            node.left = delete(node.left, key);
        else if (compare > 0)
            node.right = delete(node.right, key);
        else {
            if (node.right == null)
                return node.left;

            if (node.left == null)
                return node.right;

            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException("symbol table is underflow");

        root = deleteMin(root);
        // assert check integrity of BST - code omitted
    }

    private Node deleteMin(Node node) {
        if (node.left == null)
            return node.right;

        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    // public void deleteMax() - similar to deleteMin

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("table is empty");

        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null)
            return node;
        else
            return min(node.left);
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("table is empty");

        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null)
            return node;
        else
            return max(node.right);
    }

    public Key floor(Key key) {
        if (key == null)
            throw new IllegalArgumentException("parameter to floor function is null");

        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");

        Node node = floor(root, key);
        if (node == null)
            return null;
        else
            return node.key;
    }

    /**
     * Returns the largest key in the symbol table less than or equal to
     * {@code key}.
     */
    private Node floor(Node node, Key key) {
        if (node == null)
            return null;

        int compare = key.compareTo(node.key);
        if (compare == 0)
            return node;

        if (compare < 0)
            return floor(node.left, key);

        Node t = floor(node.right, key);
        if (t != null)
            return t;
        else
            return node;
    }

    public Key ceiling(Key key) {
        if (key == null)
            throw new IllegalArgumentException("parameter to ceiling function is null");

        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");

        Node node = ceiling(root, key);
        if (node == null)
            return null;
        else
            return node.key;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to
     * {@code key}.
     */
    private Node ceiling(Node node, Key key) {
        if (node == null)
            return null;

        int compare = key.compareTo(node.key);
        if (compare == 0)
            return node;

        if (compare < 0) {
            Node t = ceiling(node.left, key);
            if (t != null)
                return t;
            else
                return node;
        }
        return ceiling(node.right, key);
    }

    /**
     * Return the kth smallest key in the symbol table.
     */
    public Key select(int k) {
        if (k < 0 || k >= size())
            throw new IllegalArgumentException("Invalid k index in select");

        Node node = select(root, k);
        return node.key;
    }

    private Node select(Node node, int k) {
        if (node == null)
            return null;

        int t = size(node.left);
        if (t > k)
            return select(node.left, k);
        else if (t < k)
            return select(node.right, k - t - 1);
        else
            return node;
    }

    public int rank(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to rank is null");

        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null)
            return 0;

        int compare = key.compareTo(node.key);
        if (compare < 0)
            return rank(node.left, key);
        else if (compare > 0)
            return 1 + size(node.left) + rank(node.right, key);
        else
            return size(node.left);
    }

    public Iterable<Key> keys() {
        return rangeSearch(min(), max());
    }

    public Iterable<Key> rangeSearch(Key low, Key high) {
        if (low == null)
            throw new IllegalArgumentException("first argument to rangeSearch is null");

        if (high == null)
            throw new IllegalArgumentException("second argument to rangeSearch is null");

        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, low, high);
        return queue;
    }

    private void keys(Node node, Queue<Key> queue, Key low, Key high) {
        if (node == null)
            return;

        int compareLow = low.compareTo(node.key);
        int compareHigh = high.compareTo(node.key);

        if (compareLow < 0)
            keys(node.left, queue, low, high);

        if (compareLow <= 0 && compareHigh >= 0)
            queue.add(node.key);

        if (compareHigh > 0)
            keys(node.right, queue, low, high);
    }

    public int rangeCount(Key low, Key high) {
        if (low == null)
            throw new IllegalArgumentException("first argument to rangeCount is null");

        if (high == null)
            throw new IllegalArgumentException("second argument to rangeCount is null");

        if (low.compareTo(high) > 0)
            return 0;

        if (contains(high))
            return rank(high) + rank(low) + 1;
        else
            return rank(high) + rank(low);

    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if (root == null)
            return -1;

        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument is null");

        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        else
            return node.size;
    }

    public int size(Key low, Key high) {
        if (low == null)
            throw new IllegalArgumentException("low argument is null");

        if (high == null)
            throw new IllegalArgumentException("high argument is null");

        if (low.compareTo(high) > 0)
            return 0;

        if (contains(high))
            return rank(high) - rank(low) + 1;
        else
            return rank(high) - rank(low);
    }
}
