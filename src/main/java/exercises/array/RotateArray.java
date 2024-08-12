package exercises.array;

import java.util.Arrays;

public class RotateArray {

    public static void main(String[] args) {
        var nums = new int[] { 1,2,3,4,5,6,7 };
        rotate(nums, 3);

        System.out.println("nums:" + Arrays.toString(nums));
    }

    /*
    Optimal solution:
in: [1,2,3,4,5,6,7] k=3
1. reverse all: [7,6,5,4,3,2,1]
2. reverse 0-k: [5,6,7,4,3,2,1]
3. reverse k-end: [5,6,7,1,2,3,4]
     */
    public static void rotate(int[] nums, int k) {
        if (nums.length < 2) {
            return;
        }

        var rotations = k%nums.length;
        for (int i = 0; i < rotations; i++) {
            rotateOne(nums);
        }
    }

    private static void rotateOne(int[] nums) {
        var currentNumber = nums[0];
        var nextNumber = nums[1];
        var arrayLength = nums.length;
        for (int i = 0; i < arrayLength; i++) {
            var nextPosition = (i + 1) % arrayLength;
            nextNumber = nums[nextPosition];
            nums[nextPosition] = currentNumber;
            currentNumber = nextNumber;
        }
    }
}

