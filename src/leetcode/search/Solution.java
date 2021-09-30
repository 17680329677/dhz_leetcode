package leetcode.search;

public class Solution {

    private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
        int lo = start;
        int hi = vertical ? matrix[0].length-1 : matrix.length-1;

        while (hi >= lo) {
            int mid = (lo + hi)/2;
            if (vertical) { // searching a column
                if (matrix[start][mid] < target) {
                    lo = mid + 1;
                } else if (matrix[start][mid] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            } else { // searching a row
                if (matrix[mid][start] < target) {
                    lo = mid + 1;
                } else if (matrix[mid][start] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 题目：编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     *
     * - 每行的元素从左到右升序排列。
     * - 每列的元素从上到下升序排列。
     *
     * 思路1：首先，我们确保矩阵不为空。那么，如果我们迭代矩阵对角线，从当前元素对列和行搜索，我们可以保持从当前 (row,col)(row,col) 对开始的行和列为已排序。 因此，我们总是可以二分搜索这些行和列切片。我们以如下逻辑的方式进行 : 在对角线上迭代，二分搜索行和列，直到对角线的迭代元素用完为止（意味着我们可以返回 false ）或者找到目标（意味着我们可以返回 true ）。binary search 函数的工作原理和普通的二分搜索一样,但需要同时搜索二维数组的行和列。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // an empty matrix obviously does not contain `target`
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        // iterate over matrix diagonals
        int shorterDim = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i < shorterDim; i++) {
            boolean verticalFound = binarySearch(matrix, target, i, true);
            boolean horizontalFound = binarySearch(matrix, target, i, false);
            if (verticalFound || horizontalFound) {
                return true;
            }
        }

        return false;
    }

    /**
     * 思路二：首先，我们初始化一个指向矩阵左下角的 (row，col)(row，col) 指针。然后，直到找到目标并返回 true（或者指针指向矩阵维度之外的 (row，col)(row，col) 为止，我们执行以下操作：如果当前指向的值大于目标值，则可以 “向上” 移动一行。 否则，如果当前指向的值小于目标值，则可以移动一列。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix_2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                col++;
            } else if (matrix[row][col] > target) {
                row--;
            }
        }
        return false;
    }
}
