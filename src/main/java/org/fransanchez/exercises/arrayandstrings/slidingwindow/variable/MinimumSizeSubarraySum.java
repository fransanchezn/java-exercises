package org.fransanchez.exercises.arrayandstrings.slidingwindow.variable;

// 209. Minimum Size Subarray Sum
// Moving sliding window
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        var left = 0;
        var sum = 0;
        var minSubArray = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                minSubArray = Math.min(minSubArray, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        return minSubArray == Integer.MAX_VALUE ? 0 : minSubArray;
    }

    public static void main(final String[] args) {
        final var sut = new MinimumSizeSubarraySum();
        final var result = sut.minSubArrayLen(11, new int[] { 1,1,1,1,1,1,1,1 });
        System.out.println(result);
    }
}
