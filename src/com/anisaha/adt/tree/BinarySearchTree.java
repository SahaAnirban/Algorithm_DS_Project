package com.anisaha.adt.tree;

import java.util.Stack;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class BinarySearchTree {
    private int pIndex = 0;

    // Recursive approach
    public TreeNode getBSTFromPreOrder(int[] preorder, int data, int min, int max) {
        int size = preorder.length;
        if (pIndex >= size)
            return null;

        if (preorder[pIndex] > min && preorder[pIndex] < max) {
            TreeNode root = new TreeNode(data);
            pIndex++;
            if (pIndex < size) {
                // node lying between min and key will form the left subtree
                root.left = getBSTFromPreOrder(preorder, preorder[pIndex], min, data);

                // node lying between key and max will form the right subtree
                root.right = getBSTFromPreOrder(preorder, preorder[pIndex], data, max);
            }
            return root;
        }
        return null;
    }

    // Iterative approach
    public TreeNode getBSTUsingStackFromPreOrder(int[] preOrder) {
        int size = preOrder.length;

        TreeNode root = new TreeNode(preOrder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // iterate through the rest of size-1 elements in array
        for (int i = 1; i < size; i++) {
            TreeNode temp = null;

            // keep popping till next element is greater than stack top
            while (!stack.isEmpty() && preOrder[i] > stack.peek().key)
                temp = stack.pop();

            // make the greater value right child and push to stack
            if (temp != null) {
                temp.right = new TreeNode(preOrder[i]);
                stack.push(temp.right);
            } else { // if the value is less than stack top make it left child and push to stack
                temp = stack.peek();
                temp.left = new TreeNode(preOrder[i]);
                stack.push(temp.left);
            }
        }
        return root;
    }

    public static TreeNode search(TreeNode root, int key) {
        if (root == null || root.key == key)
            return root;

        if (key < root.key)
            return search(root.left, key);

        // else key > root.key
        return search(root.right, key);
    }

    public static TreeNode insert(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        if (key < root.key)
            root.left = insert(root.left, key);
        else if (key > root.key)
            root.right = insert(root.right, key);

        return root;
    }

    public static TreeNode delete(TreeNode root, int key) {
        // base case for recursion
        if (root == null)
            return root;

        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {

            // support for above recursion step
            // node with single or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node with 2 children, get minimum from right sub-tree
            // delete the min node
            root.key = getMinValue(root.right);
            root.right = delete(root.right, root.key);
        }
        return root;
    }

    private static int getMinValue(TreeNode tree) {
        int minV = tree.key;
        while (tree.left != null) {
            minV = tree.left.key;
            tree = tree.left;
        }
        return minV;
    }

    public static TreeNode deleteOptimized(TreeNode root, int key) {
        // base case for recursion
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteOptimized(root.left, key);
        else if (key > root.key)
            root.right = deleteOptimized(root.right, key);
        else {
            TreeNode successorParent = root.right;

            TreeNode successor = root.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            successorParent.left = successor.right;
            root.key = successor.key;
        }
        return root;
    }
}
