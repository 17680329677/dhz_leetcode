package kind.dfs;

import struct.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 深度优先遍历/搜索（DFS）
 */
public class Solution {

    /**
     * 104. 二叉树的最大深度
     * 思路：深度遍历，分别求得左子树和右子树的高度，取最大+1
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 104. 二叉树的最大深度
     * 思路：广度遍历，记录遍历层次数即可
     * @param root
     * @return
     */
    public int maxDepthBFS(TreeNode root) {
        if (root == null)
            return 0;
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 记录当前层长度
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode front = queue.poll();
                if (front.left != null) {
                    queue.offer(front.left);
                }
                if (front.right != null) {
                    queue.offer(front.right);
                }
            }
            depth++;
        }
        return depth;
    }

    /**
     * 111. 二叉树的最小深度
     * 思路：与上题目类似，但不能递归遍历所有深度，这样求得的是最大深度 会有问题
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        int minDepth = Integer.MAX_VALUE;
        if (root.left != null) {
            minDepth = Math.min(minDepth(root.left), minDepth);
        }
        if (root.right != null) {
            minDepth = Math.min(minDepth(root.right), minDepth);
        }
        return minDepth + 1;
    }

    /**
     * 111. 二叉树的最小深度
     * 思路：广度遍历，找到第一个叶子节点即停止
     * @param root
     * @return
     */
    public int minDepthDFS(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int minDepth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode front = queue.poll();
                if (front.left == null && front.right == null) {
                    return minDepth;
                }
                if (front.left != null) {
                    queue.offer(front.left);
                }
                if (front.right != null) {
                    queue.offer(front.right);
                }
            }
            minDepth++;
        }
        return minDepth;
    }

    /**
     * 112. 路径总和（二叉树中是否存在一条从根节点到叶子节点的路径，路径上的总和为target）
     * 思路一：深度优先遍历 递归 分别递归判断左右子树是否存在路径
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        } else {
            return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
        }
    }

    /**
     * 112. 路径总和（二叉树中是否存在一条从根节点到叶子节点的路径，路径上的总和为target）
     * 思路二：广度优先遍历，同时维护两个队列，一个用于存节点，一个用于存当前的和
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSumDFS(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valQueue = new LinkedList<>();
        nodeQueue.offer(root);
        valQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode front = nodeQueue.poll();
            Integer curVal = valQueue.poll();
            if (front.left == null && front.right == null) {
                if (curVal == targetSum) {
                    return true;
                }
                continue;
            }
            if (front.left != null) {
                nodeQueue.offer(front.left);
                valQueue.offer(curVal + front.left.val);
            }
            if (front.right != null) {
                nodeQueue.offer(front.right);
                valQueue.offer(curVal + front.right.val);
            }
        }
        return false;
    }

    /**
     * 226. 翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 100. 相同的树
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }
}
