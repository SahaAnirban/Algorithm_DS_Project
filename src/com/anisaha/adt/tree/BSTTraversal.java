package com.anisaha.adt.tree;

import java.util.*;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class BSTTraversal {
    private BSTTraversal() { }

    public static void printLevelOrder(TreeNode root) {
        int height = BinaryTree.getMaxDepth(root);

        for (int i = 1; i <= height; i++)
            printLevel(root, i);
    }

    private static void printLevel(TreeNode root, int level) {
        if (root == null)
            return;

        if (level == 1)
            System.out.print(root.key + " ");
        else if (level > 1) {
            printLevel(root.left, level - 1);
            printLevel(root.right, level - 1);
        }
    }

    public static void printInOrder(TreeNode root) {
        if (root == null)
            return;

        printInOrder(root.left);
        System.out.print(root.key + " ");
        printInOrder(root.right);
    }

    public static void printPreOrder(TreeNode root) {
        if (root == null)
            return;

        System.out.print(root.key + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }

    public static void printPostOrder(TreeNode root) {
        if (root == null)
            return;

        printPostOrder(root.left);
        printPostOrder(root.right);
        System.out.print(root.key + " ");
    }

    public static void printLevelOrderIterative(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // dequeue operation
            TreeNode node = queue.poll();
            System.out.print(node.key + " ");

            // enqueue left child
            if (node.left != null)
                queue.add(node.left);

            // enqueue right child
            if (node.right != null)
                queue.add(node.right);
        }

    }

    public static void printInOrderIterative(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.empty()) {

            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.key + " ");

            current = current.right;
        }
    }

    public static void printPreOrderIterative(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode top = stack.peek();
            System.out.print(top.key + " ");
            stack.pop();

            // right is pushed before left due to FIFO in stack
            // when we peek, left is printed before right
            if (top.right != null)
                stack.push(top.right);

            if (top.left != null)
                stack.push(top.left);
        }
    }

    public static void printPostOrderIterative(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);

            if (node.left != null)
                stack1.push(node.left);

            if (node.right != null)
                stack1.push(node.right);
        }

        while (!stack2.isEmpty()) {
            TreeNode temp = stack2.pop();
            System.out.print(temp.key + " ");
        }
    }
}
