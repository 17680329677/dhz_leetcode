package kind.dfs;

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

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] inorder = new int[] {9,3,15,20,7};
        int[] postorder = new int[] {9,15,7,20,3};
        solution.buildTree2(inorder, postorder);
    }
}
