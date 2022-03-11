package com.algorithm.base.binarytree;

/**
 * @author miclefengzss
 * 2022/3/2 下午2:28
 */
public class SymmetricTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    public boolean isSymmetric(TreeNode l, TreeNode r) {
        if (l == null ^ r == null) {
            return false;
        }

        if (l == null && r == null) {
            return true;
        }

        return l.val == r.val && isSymmetric(l.left, r.right) && isSymmetric(l.right, r.left);
    }
}
