package org.fransanchez.exercises.arrayandstrings.prefixsum;

// 303. Range Sum Query - Immutable
public class RangeSumQuery {
    private final int[] prefixSum;

    public RangeSumQuery(final int[] nums) {
        prefixSum = calculatePrefixSum(nums);
    }

    public int sumRange(final int left, final int right) {
        if (left == 0) {
            return prefixSum[right];
        }

        return prefixSum[right] - prefixSum[left - 1];
    }

    private int[] calculatePrefixSum(final int[] nums) {
        final var prefixSum = new int[nums.length];

        var sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }

        return prefixSum;
    }

    public static void main(String[] args) {
        final var rsq = new RangeSumQuery(new int[] {-2,0,3,-5,2,-1});
        System.out.println(rsq.sumRange(0, 2));
        System.out.println(rsq.sumRange(2, 5));
        System.out.println(rsq.sumRange(0, 5));
    }
}
