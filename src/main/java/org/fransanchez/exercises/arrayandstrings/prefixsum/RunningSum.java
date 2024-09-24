package org.fransanchez.exercises.arrayandstrings.prefixsum;

import java.util.Arrays;

// 1480. Running Sum of 1d Array
public class RunningSum {
    // Input: nums = [1,2,3,4]
    // Output: [1,3,6,10]
    // Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
    public int[] runningSum(int[] nums) {
        final var rsum = new int[nums.length];
        var sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            rsum[i] = sum;
        }

        return rsum;
    }

    public static void main(String[] args) {
        final var rsum = new RunningSum();
        final var result = rsum.runningSum(new int[] { 1,2,3,4 });

        System.out.println(Arrays.toString(result));
    }
}
