package org.fransanchez.exercises.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

// 746. Min Cost Climbing Stairs
public class MinCostClimbingStairs {
    private final Map<Integer, Integer> memoization;

    public MinCostClimbingStairs() {
        memoization = new HashMap<>();
        memoization.put(0, 0);
        memoization.put(1, 0);
    }

    public int minCostClimbingStairs(final int[] cost) {
        return dp(cost.length, cost);
    }

    private int dp(final int step, final int[] cost) {
        if (memoization.containsKey(step)) {
            return memoization.get(step);
        }

        final var result = Math.min(dp(step - 1, cost) + cost[step - 1], dp(step - 2, cost)  + cost[step - 2]);
        memoization.put(step, result);

        return result;
    }

    public static void main(final String[] args) {
        final var sut = new MinCostClimbingStairs();
        final var result = sut.minCostClimbingStairs(new int[] { 1,100,1,1,1,100,1,1,100,1 });

        System.out.println(result);
    }
}
