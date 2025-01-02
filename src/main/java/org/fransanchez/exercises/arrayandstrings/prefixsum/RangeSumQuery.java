package org.fransanchez.exercises.arrayandstrings.prefixsum;

// 303. Range Sum Query - Immutable
public class RangeSumQuery {
    private final int[] prefixSum;

    public RangeSumQuery(final int[] nums) {
        prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
    }

    public int sumRange(final int left, final int right) {
        if (left == 0) {
            return prefixSum[right];
        }
        return prefixSum[left - 1] - prefixSum[right];
    }

    public static void main(String[] args) {
        final var rsq = new RangeSumQuery(new int[] {-2,0,3,-5,2,-1});
        System.out.println(rsq.sumRange(0, 2));
        System.out.println(rsq.sumRange(2, 5));
        System.out.println(rsq.sumRange(0, 5));
    }


}
