package org.fransanchez.exercises.hashing;

import java.util.HashMap;
import java.util.Map;

// 1. Two Sum
public class TwoSum {
    private final Map<Integer, Integer> differences = new HashMap<>();

    // O(1)
    public int[] twoSum(final int[] nums, final int target) {
        for (int i = 0; i < nums.length; i++) { // O(n)
            final var curr = nums[i];
            if (differences.get(target - curr) != null) { // O(1)
                return new int[] {differences.get(target - curr), i };
            }

            differences.put(nums[i], i); // O(1)
        }

        return new int[] {-1, -1};
    }
}
