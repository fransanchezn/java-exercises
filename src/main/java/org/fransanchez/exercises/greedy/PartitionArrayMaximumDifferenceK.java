package org.fransanchez.exercises.greedy;

import java.util.Arrays;

// 2294. Partition Array Such That Maximum Difference Is K
public class PartitionArrayMaximumDifferenceK {
    public int partitionArray(final int[] nums, final int k) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        var result = 1;
        var prevMin = nums[0];
        for (int num : nums) {
            if (Math.abs(prevMin - num) > k) {
                prevMin = num;
                result++;
            }
        }

        return result;
    }
}
