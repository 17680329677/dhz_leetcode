package kind.bfs;

import struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 广度优先搜索相关
 */
public class Solution {

    /**
     * 100. 相同的树（给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。）
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p == null || q == null)
            return false;

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1.val != node2.val)
                return false;
            TreeNode left1 = node1.left, right1 = node1.right, left2 = node2.left, right2 = node2.right;
            if (left1 == null ^ left2 == null) {
                return false;
            }
            if (right1 == null ^ right2 == null) {
                return false;
            }

            if (left1 != null) {
                queue1.offer(left1);
            }
            if (right1 != null) {
                queue1.offer(right1);
            }
            if (left2 != null) {
                queue2.offer(left2);
            }
            if (right2 != null) {
                queue2.offer(right2);
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }

    /**
     * 102. 二叉树的层次遍历
     * 思路： 广度优先搜索
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int curLevelSize = queue.size();

            List<Integer> curLevel = new ArrayList<>();
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode front = queue.poll();
                curLevel.add(front.val);
                if (front.left != null) {
                    queue.offer(front.left);
                }
                if (front.right != null) {
                    queue.offer(front.right);
                }
            }
            res.add(curLevel);
        }
        return res;
    }

    /**
     * 323. 无向图中连通分量的数量（你有一个包含 n 个节点的图。给定一个整数 n 和一个数组edges，其中edges[i] = [ai, bi]表示图中ai和bi之间有一条边。
     * @param n
     * @param edges
     * @return
     */
    public int countComponents(int n, int[][] edges) {
        // 第一步，根据信息构建连通图
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        // 无向图，需要添加双向引用
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // 第二步， 开始广度遍历
        int res = 0;
        boolean[] visted = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visted[i]) {
                bfs(adj, i, visted);
                res++;
            }
        }
        return res;
    }

    private void bfs(List<Integer>[] adj, int root, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        visited[root] = true;

        while (!queue.isEmpty()) {
            Integer front = queue.poll();
            // 获得队首节点的所有后继节点
            List<Integer> successors = adj[front];
            for (int successor : successors) {
                if (!visited[successor]) {
                    visited[successor] = true;
                    queue.offer(successor);
                }
            }
        }
    }

    /**
     * 101. 对称二叉树（判断一棵二叉树是否对称）
     * 方法一：递归
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    /**
     * 101. 对称二叉树（判断一棵二叉树是否对称）
     * 方法二：迭代，一般递归改迭代需借助队列
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        return check2(root, root);
    }

    private boolean check2(TreeNode u, TreeNode v) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(u);
        queue.offer(v);
        while (!queue.isEmpty()) {
            u = queue.poll();
            v = queue.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || u.val != v.val)
                return false;
            queue.offer(u.left);
            queue.offer(v.right);

            queue.offer(u.right);
            queue.offer(v.left);
        }
        return true;
    }
}
