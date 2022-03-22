package kind.dfs;

import struct.Node;
import struct.TreeNode;

import java.util.*;

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

    /**
     * 101.判断对称二叉树
     * 思路：递归+深度遍历
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    /**
     * 129.求根节点到叶节点的数字之和
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    /**
     * 129.求根节点到叶节点的数字之和
     * 思路二：广度优先遍历
     * @param root
     * @return
     */
    public int sumNumbersDFS(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valQueue = new LinkedList<>();
        nodeQueue.offer(root);
        valQueue.offer(0);
        int sum = 0;
        while (!nodeQueue.isEmpty()) {
            int curSize = nodeQueue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode front = nodeQueue.poll();
                Integer curVal = valQueue.poll();
                curVal = curVal * 10 + front.val;
                if (front.left == null && front.right == null) {
                    sum += curVal;
                }
                if (front.left != null) {
                    nodeQueue.offer(front.left);
                    valQueue.offer(curVal);
                }
                if (front.right != null) {
                    nodeQueue.offer(front.right);
                    valQueue.offer(curVal);
                }
            }
        }
        return sum;
    }


    /**
     * 235. 二叉搜索树的最近公共祖先
     * 思路：利用二叉搜索树的性质，进行一次遍历或递归
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (ancestor.val > p.val && ancestor.val > q.val) {
                ancestor = ancestor.left;
            } else if (ancestor.val < p.val && ancestor.val < q.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }

    public TreeNode lowestCommonAncestorA(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestorA(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestorA(root.right, p, q);
        } else {
            return root;
        }
    }

    /**
     * 236. 二叉树的最近公共祖先
     * 思路：此处不是二叉搜索树了
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorB(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestorB(root.left, p, q);
        TreeNode right = lowestCommonAncestorB(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    /**
     * 105.从前序和中序遍历构造二叉树
     * @param preorder
     * @param inorder
     * @return
     */

    public HashMap<Integer, Integer> indexMap = null;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = preorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        return mybuildProcess(preorder, inorder, 0, length - 1, 0, length - 1);
    }

    private TreeNode mybuildProcess(int[] pre, int[] in, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight)
            return null;
        // 前序遍历的根节点就是第一个
        int preRoot = pre[preLeft];
        // 定位中序遍历中根节点的位置
        int inRootIndex = indexMap.get(preRoot);

        // 初始化根节点
        TreeNode root = new TreeNode(preRoot);
        // 左子树的数量
        int leftSize = inRootIndex - inLeft;
        // 递归构造左子树
        root.left = mybuildProcess(pre, in,
                preLeft + 1, preLeft + leftSize,
                inLeft, inRootIndex -1);
        // 递归构造右子树
        root.right = mybuildProcess(pre, in,
                preLeft + leftSize + 1, preRight,
                inRootIndex + 1, inRight);
        return root;
    }

    /**
     * 106. 从中序和后续遍历建立二叉树
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        int length = inorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < length; i++){
            indexMap.put(inorder[i], i);
        }
        return mybuildProcess2(inorder, postorder, 0, length - 1, 0, length - 1);
    }

    private TreeNode mybuildProcess2(int[] in, int[] post, int inLeft, int inRight, int postLeft, int postRight) {
        if (postLeft > postRight)
            return null;
        // 后序遍历的根节点就是最后一个
        int postRoot = post[postRight];
        // 定位中序遍历中根节点的位置
        int inRootIndex = indexMap.get(postRoot);

        // 初始化根节点
        TreeNode root = new TreeNode(postRoot);
        // 左子树的数量
        int leftSize = inRootIndex - inLeft;
        // 递归构造左子树
        root.left = mybuildProcess2(in, post,
                inLeft, inRootIndex - 1,
                postLeft, postLeft + leftSize - 1);
        // 递归构造右子树
        root.right = mybuildProcess2(in, post,
                leftSize + 1, inRight,
                postLeft + leftSize, postRight - 1);
        return root;
    }

    /**
     * 1008. 前序遍历构造二叉搜索树
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null)
            return null;
        else
            return bstBuild(preorder, 0, preorder.length - 1);
    }

    private TreeNode bstBuild(int[] preorder, int start, int end) {
        if (start <= end) {
            TreeNode root = new TreeNode(preorder[start]);
            int index = start + 1;
            while (index <= end) {
                if (preorder[index] < preorder[start]) {
                    index++;
                } else {
                    break;
                }
            }
            root.left = bstBuild(preorder, start + 1, index - 1);
            root.right = bstBuild(preorder, index, end);
            return root;
        } else {
            return null;
        }
    }

    /**
     * 144. 二叉树的前序遍历-递归
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    private void preOrder(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    /**
     * 144. 二叉树的前序遍历-迭代
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    /**
     * 94.二叉树的中序遍历-递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    private void inOrder(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

    /**
     * 94.二叉树的中序遍历-迭代
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * 145. 二叉树的后续遍历-迭代
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null)
            return res;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 589. N叉树的前序遍历-递归
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        nPreOder(root, res);
        return res;
    }

    private void nPreOder(Node root, List<Integer> res) {
        if (root == null)
            return;
        res.add(root.val);
        for (Node node : root.children) {
            nPreOder(node, res);
        }
    }

    /**
     * 589. N叉树的前序遍历-迭代
     * @param root
     * @return
     */
    public List<Integer> preorder2(Node root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Map<Node, Integer> map = new HashMap<Node, Integer>();
        Deque<Node> stack = new ArrayDeque<Node>();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                List<Node> children = node.children;
                if (children != null && children.size() > 0) {
                    map.put(node, 0);
                    node = children.get(0);
                } else {
                    node = null;
                }
            }
            node = stack.peek();
            int index = map.getOrDefault(node, -1) + 1;
            List<Node> children = node.children;
            if (children != null && children.size() > index) {
                map.put(node, index);
                node = children.get(index);
            } else {
                stack.pop();
                map.remove(node);
                node = null;
            }
        }
        return res;
    }

    /**
     * 590. n叉树的后序遍历-递归
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        nPostOrder(root, res);
        return res;
    }

    private void nPostOrder(Node node, List<Integer> res) {
        if (node == null)
            return;
        for (Node child : node.children) {
            nPostOrder(child, res);
        }
        res.add(node.val);
    }

    /**
     * 590. n叉树的后序遍历-迭代
     * @param root
     * @return
     */
    public List<Integer> postorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Node, Integer> indexMap = new HashMap<>();
        Deque<Node> stack = new LinkedList<>();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                List<Node> children = node.children;
                if (children != null && children.size() > 0) {
                    indexMap.put(node, 0);
                    node = children.get(0);
                } else {
                    node = null;
                }
            }
            node = stack.peek();
            int index = indexMap.getOrDefault(node, -1) + 1;
            List<Node> children = node.children;
            if (children != null && children.size() > index) {
                indexMap.put(node, index);
                node = children.get(index);
            } else {
                res.add(node.val);
                stack.pop();
                indexMap.remove(node);
                node = null;
            }
        }
        return res;
    }

    /**
     * 323. 无向连通中连通分量的数目-深度优先遍历
     * @param n
     * @param edges
     * @return
     */
    public int countComponents(int n, int[][] edges) {
        // 1. 构建图
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        // 2. 无向图，添加双向索引
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        // 2. 开始深度遍历
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs323(adj, i, visited);
                count++;
            }
        }
        return count;
    }

    private void dfs323(List<Integer>[] adj, int u, boolean[] visited) {
        visited[u] = true;
        List<Integer> successors = adj[u];
        for (int successor : successors) {
            if (!visited[successor]) {
                dfs323(adj, successor, visited);
            }
        }
    }

    /**
     * 684.冗余连接：请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。
     * 如果有多个答案，则返回数组 edges 中最后出现的边。
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();    // 图
        Set<Integer> visited = new HashSet<>();     // 记录访问过的节点

        // 遍历每条边
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (graph.containsKey(u) && graph.containsKey(v)) {
                visited.clear();
                if (hasCircleDFS(graph, visited, u, v)) {
                    return edge;
                }
            }
            addEdge(graph,u, v);
            addEdge(graph,v, u);
        }
        return null;
    }

    /**
     * 采用深度优先遍历，寻找从源节点到目的节点是否存在路径，即环路
     * @param graph
     * @param visited
     * @param source
     * @param target
     * @return
     */
    private boolean hasCircleDFS(Map<Integer, List<Integer>> graph, Set<Integer> visited, int source, int target) {
        if (source == target)
            return true;
        visited.add(source);
        for (int adj : graph.get(source)) {
            if (!visited.contains(adj)) {
                if (hasCircleDFS(graph, visited, adj, target)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void addEdge(Map<Integer, List<Integer>> graph, int u, int v) {
        if (graph.containsKey(u)) {
            graph.get(u).add(v);
            return;
        }
        List<Integer> successors = new ArrayList<>();
        successors.add(v);
        graph.put(u, successors);
    }

    /**
     * 802. 找到最终的安全状态-对于一个起始节点，如果从该节点出发，
     * 无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是安全的。
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        Boolean[] visited = new Boolean[graph.length];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (isNotSafeDFS(visited, graph, i)) {
                continue;
            }
            res.add(i);
        }
        return res;
    }

    /**
     * 深度搜索，看是否有环路
     * @param visited
     * @param graph
     * @param u
     * @return
     */
    private boolean isNotSafeDFS(Boolean[] visited, int[][] graph, int u) {
        if (visited[u] != null)
            return visited[u];
        visited[u] = true;
        for (int successor : graph[u]) {
            if (isNotSafeDFS(visited, graph, successor)) {
                return true;
            }
        }
        visited[u] = false;
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        Node child_2_0 = new Node(5);
        Node child_2_1 = new Node(6);
        List<Node> child_2 = new ArrayList<>();

        child_2.add(child_2_0);
        child_2.add(child_2_1);

        Node child_1_1 = new Node(2);
        Node child_1_2 = new Node(4);
        Node child_1_0 = new Node(3, child_2);
        List<Node> child_1 = new ArrayList<>();
        child_1.add(child_1_0);
        child_1.add(child_1_1);
        child_1.add(child_1_2);
        Node root = new Node(1, child_1);

        solution.preorder2(root);

    }
}
