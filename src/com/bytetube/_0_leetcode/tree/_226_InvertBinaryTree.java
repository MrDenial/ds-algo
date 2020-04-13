package com.bytetube._0_leetcode.tree;

import com.bytetube._0_leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _226_InvertBinaryTree {

    public TreeNode invertTree1(TreeNode root) {//preorder
        if (root == null) return root;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree1(root.left);
        invertTree1(root.right);
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {//Inorder
        if (root == null) return root;

        invertTree2(root.left);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree2(root.left);
        return root;

    }
    public TreeNode invertTree3(TreeNode root) {//Inorder
        if (root == null) return root;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();

                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            return root;

        }

}