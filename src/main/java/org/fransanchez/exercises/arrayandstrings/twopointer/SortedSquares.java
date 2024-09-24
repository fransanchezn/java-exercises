package org.fransanchez.exercises.arrayandstrings.twopointer;

import java.util.Arrays;

// 977. Squares of a Sorted Array
public class SortedSquares {
    // return new array
    public int[] sortedSquares(final int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        final var result = new int[nums.length];
        var resultPointer = nums.length - 1;

        while(left <= right) {
            final var squaredLeft = nums[left] * nums[left];
            final var squaredRight = nums[right] * nums[right];

            if (squaredLeft < squaredRight) {
                result[resultPointer] = squaredRight;
                right--;
            } else {
                result[resultPointer] = squaredLeft;
                left++;
            }

            resultPointer--;
        }

        return result;
    }

    // [-4,-1,0,3,10] -> [16,1,0,9,100] -> [0,1,9,16,100]
    // [-4,-1,0,3,100]
    // [3,-1,0,16,100]
    // [3,-1,0,16,100]
    // [0,-1,9,16,100]
    // [0,1,9,16,100]
    // Void modify input
    public void sortedSquaresVoid(final int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        final var result = new int[nums.length];

        while(left <= right) {
            final var squaredLeft = nums[left] * nums[left];
            final var squaredRight = nums[right] * nums[right];

            if (squaredLeft < squaredRight) {
                nums[right] = squaredRight;
                right--;
            } else {
                final var tmp = nums[right];
                nums[right] = squaredLeft;
                nums[left] = tmp;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        final var sot = new SortedSquares();
        final var input = new int[] { -7,-3,2,3,11 };
        final var result = sot.sortedSquares(input);

        System.out.println(Arrays.toString(result));
    }
}
