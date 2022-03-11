package com.algorithm.base.binarytree;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * @author miclefengzss
 * 2022/3/10 下午10:28
 */
public class BinarySearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {

        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return process(root).isBST;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int max = root.val;
        int min = root.val;

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }

        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }

        boolean isBST = false;
        boolean leftIsBst = leftInfo == null || leftInfo.isBST;
        boolean rightIsBst = rightInfo == null || rightInfo.isBST;
        boolean leftMaxLess = leftInfo == null || leftInfo.max < root.val;
        boolean rightMinMore = rightInfo == null || rightInfo.min > root.val;

        if (leftIsBst && rightIsBst && leftMaxLess && rightMinMore) {
            isBST = true;
        }

        return new Info(isBST, max, min);
    }
}
