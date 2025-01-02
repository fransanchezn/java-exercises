package org.fransanchez.exercises.arrayandstrings.prefixsum;

public class RangeRUmQuery2 {
    private final int[] prefix;

    public RangeRUmQuery2(final int[] nums) {
        prefix = new int[nums.length];
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
    }

    public int sumRange(final int left, final int right) {
        return prefix[Math.max(0, left - 1)] - prefix[right];
    }
}
