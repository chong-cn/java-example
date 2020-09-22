package com.java.example.algorithm.tree;

import lombok.Data;

@Data
public class TreeNode {

    private int Data;

    private TreeNode left;

    private TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int data, TreeNode left, TreeNode right) {
        Data = data;
        this.left = left;
        this.right = right;
    }
}
