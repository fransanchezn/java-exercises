package dynamicprograming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CoinChange {
    public static void main(String[] args) {
        // [1,2,5], amount = 11
        // Output: 3
        // Explanation: 11 = 5 + 5 + 1

        final var r = coinChange(new int[]{1, 2, 5}, 11);
        System.out.println(r);
    }

    public static int coinChange(int[] coins, int amount) {
        final var dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    // Min between previously resolved [i - coins]
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
