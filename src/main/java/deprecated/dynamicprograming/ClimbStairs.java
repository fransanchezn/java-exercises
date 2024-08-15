package deprecated.dynamicprograming;

import java.util.HashMap;
import java.util.Map;

public class ClimbStairs {
    final Map<Integer, Integer> cache = new HashMap();

    public int climbStairs(final int n) {
        if (n < 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        int steps = climbStairs(n - 1) + climbStairs(n - 2);
        cache.put(n, steps);

        return steps;
    }

    public int climbStairs2(final int n) {
        final var dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 0 ; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
