package com.java.example.algorithm.tree;

import java.util.Arrays;

/**
 * 二叉树
 */
public class BinaryTreeExample {


    /**
     * 重构二叉树
     *
     * @return:
     * @Date: 2020-09-14
     */
    public static TreeNode reContructBinaryTree(int[] preArr, int[] midArr) {

        if (0 == preArr.length || 0 == midArr.length) {
            return null;
        }

        TreeNode rNode = new TreeNode();
        int rData = preArr[0];
        rNode.setData(rData);

        for (int i = 0; i < midArr.length; i++) {
            if (midArr[i] == rData) {
                int preFrom = 1;
                int preTo = i + 1;
                int midFrom = 0;
                int midTo = i;
                rNode.setLeft(reContructBinaryTree(Arrays.copyOfRange(preArr, preFrom, preTo), Arrays.copyOfRange(midArr, midFrom, midTo)));
                preFrom = i + 1;
                preTo = preArr.length;
                midFrom = i + 1;
                midTo = midArr.length;
                rNode.setRight(reContructBinaryTree(Arrays.copyOfRange(preArr, preFrom, preTo), Arrays.copyOfRange(midArr, midFrom, midTo)));
            }
        }
        return rNode;
    }


    public static void main(String[] args) {
        // 前序遍历
        int[] preArr = {1, 2, 4, 7, 3, 5, 6, 8};
        // 中序遍历
        int[] midArr = {4, 7, 2, 1, 5, 3, 8, 6};

        TreeNode rNode = reContructBinaryTree(preArr, midArr);
        System.out.println(rNode.toString());

    }

}
