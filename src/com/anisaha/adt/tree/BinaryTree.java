package com.anisaha.adt.tree;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class BinaryTree {
    protected TreeNode root;

    public TreeNode getNode(int data) {
        TreeNode node = new TreeNode(data);
        return node;
    }

    public static int getMaxDepth(TreeNode startNode) {
        if (startNode == null)
            return 0;
        else {
            int lDepth = getMaxDepth(startNode.left);
            int rDepth = getMaxDepth(startNode.right);

            if (lDepth > rDepth)
                return lDepth + 1;
            else
                return rDepth + 1;
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = tree.getNode(1);
        tree.root.left = tree.getNode(2);
        tree.root.right = tree.getNode(3);
        tree.root.left.left = tree.getNode(4);

        System.out.println("the max depth of the tree is: " + getMaxDepth(tree.root));
    }
}
