package leetcode.array;

public class ZeroMove {

    public void moveZeroes(int[] nums) {

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        ZeroMove zeroMove = new ZeroMove();
        int[] nums = {0,1,0,3,12};
        zeroMove.moveZeroes(nums);
        System.out.println(nums.toString());
    }
}
