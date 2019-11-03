package com.anisaha.adt.tree;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class BSTMain {
    private BSTMain() {
    }

    public static void main(String[] args) {
        BinarySearchTree btree = new BinarySearchTree();
        int[] preOrder = { 20, 10, 5, 1, 7, 15, 30, 25, 35, 32, 40 };
        TreeNode root = btree.getBSTFromPreOrder(preOrder, preOrder[0], Integer.MIN_VALUE, Integer.MAX_VALUE);

        System.out.println("Inorder Traversal of BST: ");
        BSTTraversal.printInOrderIterative(root);

        System.out.println("\nLevel-order Traversal of BST");
        BSTTraversal.printLevelOrderIterative(root);

        TreeNode tree2 = btree.getBSTUsingStackFromPreOrder(preOrder);
        System.out.println("\nInorder Traversal of BST, algo 2: ");
        BSTTraversal.printInOrder(tree2);

        System.out.println("\nInsert test in a BST:");
        int[] preOrderTest = { 8, 3, 1, 6, 4, 7, 10, 14, 13 };
        TreeNode tree3 = btree.getBSTUsingStackFromPreOrder(preOrderTest);
        BinarySearchTree.insert(tree3, 2); // becomes right of node.value = 1
        BSTTraversal.printInOrder(tree3);

        System.out.println("\nDelete test in a BST:");
        BinarySearchTree.delete(tree3, 3);
        BSTTraversal.printInOrder(tree3);

        System.out.println("\nSearch test in a BST:");
        TreeNode searchNode = BinarySearchTree.search(tree3, 10);
        System.out.println("The search node value: " + searchNode.key);
    }
}
