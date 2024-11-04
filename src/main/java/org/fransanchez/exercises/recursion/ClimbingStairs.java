package org.fransanchez.exercises.recursion;

import java.util.HashMap;
import java.util.Map;

// 70. Climbing Stairs
public class ClimbingStairs {
    private final Map<Integer, Integer> cache = new HashMap<>();

    public int climbStairs(final int n) {
        return dfs(n, 0);
    }

    private int dfs(final int n, final int current) {
        if (current >= n) {
            return n == current ? 1 : 0;
        }

        if (cache.containsKey(current)) {
            return cache.get(current);
        }

        final var nextStep = dfs(n, current + 1) + dfs(n, current + 2);
        cache.put(current, nextStep);
        return nextStep;
    }

    private long optimized(final int n) {
        final int[] minCache = new int[2];
        minCache[0] = 1;
        minCache[1] = 1;

        for (int i = 0; i < n; i++) {
            final var next = minCache[0] + minCache[1];
            minCache[1] = minCache[0];
            minCache[0] = next;
        }

        return minCache[1];
    }

    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().optimized(45));
    }

}
