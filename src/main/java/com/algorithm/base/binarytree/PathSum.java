package com.algorithm.base.binarytree;

/**
 * @author miclefengzss
 * 2022/3/13 下午8:30
 */
public class PathSum {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    boolean isSum = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
//        return process(root, targetSum);
        isSum = false;
        process(root, 0, targetSum);
        return isSum;
    }

    private void process(TreeNode root, int preSum, int targetSum) {
        if (root.left == null && root.right == null) {
            if (preSum + root.val == targetSum) {
                isSum = true;
            }
        }

        preSum += root.val;
        if (root.left != null) {
            process(root.left, preSum, targetSum);
        }
        if (root.right != null) {
            process(root.right, preSum, targetSum);
        }
    }

//    private boolean process(TreeNode root, int targetSum) {
//        if (root.left == null && root.right == null) {
//            return root.val == targetSum;
//        }
//
//        boolean ans = root.left != null && process(root.left, targetSum - root.val);
//        ans |= root.right != null && process(root.right, targetSum - root.val);
//        return ans;
//    }
}
