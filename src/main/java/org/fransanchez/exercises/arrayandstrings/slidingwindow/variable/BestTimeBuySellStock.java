package org.fransanchez.exercises.arrayandstrings.slidingwindow.variable;

// 121. Best Time to Buy and Sell Stock
public class BestTimeBuySellStock {
    // [7,2,8,1,6,8]
    public int maxProfit(final int[] prices) {
        int maxProfit = 0;
        int buy = 0;
        int sell = 1;

        while (sell < prices.length) {
            if (prices[sell] > prices[buy]) {
                final var profit = prices[sell] - prices[buy];
                maxProfit = Math.max(maxProfit, profit);
            } else {
                buy = sell;
            }

            sell++;
        }

        return maxProfit;
    }
}
