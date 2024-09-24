package org.fransanchez.exercises.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

// 198. House Robber
public class HouseRobber {
    private final Map<Integer, Integer> memoization;

    public HouseRobber() {
        memoization = new HashMap<>();
    }

    public int rob(final int[] nums) {
        return dp(nums.length - 1, nums);
    }

    private int dp(final int i, final int[] nums) {
        if (i < 0) {
            return 0;
        }

        if (memoization.containsKey(i)) {
            return memoization.get(i);
        }

        final var result = Math.max(dp(i - 2, nums) + nums[i], dp(i - 1, nums));
        memoization.put(i, result);

        return result;
    }
}
