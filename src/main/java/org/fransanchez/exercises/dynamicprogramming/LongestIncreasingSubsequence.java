package org.fransanchez.exercises.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

// 300. Longest Increasing Subsequence
public class LongestIncreasingSubsequence {
    private final Map<Integer, Integer> memo = new HashMap<>();

    public int lengthOfLIS(final int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, dp(i, nums));
        }

        return result;
    }

    public int lengthOfLISBottomUp(int[] nums) {
        final var memo = new HashMap<Integer, Integer>();

        int ans = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    memo.put(i, Math.max(memo.getOrDefault(i, 1), memo.getOrDefault(j, 1) + 1));
                    ans = Math.max(ans, memo.get(i));
                }
            }
        }

        return ans;
    }

    private int dp(final int index, final int[] nums) {
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        // Base case
        int result = 1;

        for (int j = 0; j < index; j++) {
            if (nums[index] > nums[j]) {
                result = Math.max(result, dp(j, nums) + 1);
            }
        }

        memo.put(index, result);

        return result;
    }

    public static void main(String[] args) {
        final var sut = new LongestIncreasingSubsequence();
        sut.lengthOfLISBottomUp(new int[] { 10,9,2,5,3,7,101,18 });
    }
}
