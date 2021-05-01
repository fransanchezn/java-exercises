package array;

public class MoveZeros {

    public static void main(String[] args) {
        int[] arr = {0,1};
        moveZeroes2(arr);

        for(int i : arr) {
            System.out.print(i + ", ");
        }

    }

    public static void moveZeroes2(int[] nums) {
        var nonZeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex] = nums[i];
                nonZeroIndex++;
            }
        }

        for (int i = nonZeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void moveZeroes(int[] nums) {
        var length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) {
                shiftLeft(nums, i);
                nums[nums.length - 1] = 0;
                length--;
                i--;
            }
        }
    }

    private static void shiftLeft(final int[] nums, final int k) {
        for (int i = k; i < nums.length - 1; i++) {
            nums[i] = nums[i + 1];
        }
    }
}
