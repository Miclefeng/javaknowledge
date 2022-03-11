package com.algorithm.base.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/submissions/
 *
 * @author miclefengzss
 * 2022/3/2 下午3:29
 */
public class ConstructBinaryTreeFromPreOrderAndInOrderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            countMap.put(in[i], i);
        }
        return g(pre, 0, pre.length - 1, in, 0, in.length - 1, countMap);
    }

    public TreeNode g(int[] pre, int pl, int pr, int[] in, int il, int ir, Map<Integer, Integer> countMap) {
        if (pl > pr) {
            return null;
        }
        // 建立头结点
        TreeNode head = new TreeNode(pre[pl]);
        // 说明到最后一个元素
        if (pl == pr) {
            return head;
        }
        int find = countMap.get(pre[pl]);
        head.left = g(pre, pl + 1, pl + find - il, in, il, find - 1, countMap);
        head.right = g(pre, pl + find - il + 1, pr, in, find + 1, ir, countMap);
        return head;
    }

//    public TreeNode buildTree(int[] pre, int[] in) {
//        if (pre == null || in == null || pre.length != in.length) {
//            return null;
//        }
//
//        Map<Integer, Integer> inMap = new HashMap<>();
//        for (int i = 0; i < in.length; i++) {
//            inMap.put(in[i], i);
//        }
//
//        return g(pre, 0, pre.length - 1, in, 0, in.length - 1, inMap);
//    }
//
//    private TreeNode g(int[] pre, int pl, int pr, int[] in, int il, int ir, Map<Integer, Integer> inMap) {
//        if (pl > pr) {
//            return null;
//        }
//        // 建立头结点
//        TreeNode head = new TreeNode(pre[pl]);
//
//        if (pl == pr) {
//            return head;
//        }
//
//        int find = inMap.get(pre[pl]);
//
//        head.left = g(pre, pl + 1, pl + find - il, in, il, find - 1, inMap);
//        head.right = g(pre, pl + find - il + 1, pr, in, find + 1, ir, inMap);
//        return head;
//    }


}
