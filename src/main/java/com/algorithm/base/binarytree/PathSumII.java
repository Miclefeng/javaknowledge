package com.algorithm.base.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author miclefengzss
 * 2022/3/13 下午8:51
 */
public class PathSumII {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<Integer> path = new LinkedList<>();
        process(root, 0, targetSum, path, ans);
        return ans;
    }

    private void process(TreeNode root, int preSum, int targetSum, List<Integer> path, List<List<Integer>> ans) {
        if (root.left == null && root.right == null) {
            if (preSum + root.val == targetSum) {
                path.add(root.val);
                ans.add(copy(path));
                path.remove(path.size() - 1);
            }
            return;
        }

        preSum += root.val;
        path.add(root.val);

        if (root.left != null) {
            process(root.left, preSum, targetSum, path, ans);
        }
        if (root.right != null) {
            process(root.right, preSum, targetSum, path, ans);
        }
        path.remove(path.size() - 1);
    }

    public List<Integer> copy(List<Integer> src) {
        return new ArrayList<>(src);
    }
}
