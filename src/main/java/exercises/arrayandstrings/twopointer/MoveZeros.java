package exercises.arrayandstrings.twopointer;

import java.util.Arrays;

public class MoveZeros {

    // 0,1,0,3,12
    // 1,0,0,3,12
    // 1,0,0,3,12
    // 1,3,0,0,12
    // 1,3,12,0,0
    public void moveZeroes(final int[] nums) {
        var left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                var tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
                left++;
            }
        }
    }

    public static void main(String[] args) {
        final var sut = new MoveZeros();
        var input = new int[]{0,1,0,3,12};
        sut.moveZeroes(input);

        System.out.println(Arrays.toString(input));

    }
}
