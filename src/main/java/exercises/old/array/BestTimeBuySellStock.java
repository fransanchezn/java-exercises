package exercises.old.array;

public class BestTimeBuySellStock {
    public static void main(String[] args) {
        var prices = new int[] { 500,600,1,700,1 };

        var result = maxProfit2(prices);

        System.out.println(result);
    }

    public static int maxProfit1(int[] prices) {
        var maxProfit = 0;
        if (prices.length < 2) {
            return maxProfit;
        }

        // O(n)
        for (int buy = 0; buy < prices.length; buy++) {
            // O(n)
            for (int sell = buy + 1; sell < prices.length; sell++) {
                var profit = prices[sell] - prices[buy];
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }

    public static int maxProfit2(int[] prices) {
        var minBuyPrice = prices[0];
        var profit = 0;
        for (int price : prices) {
            if (price < minBuyPrice) {
                minBuyPrice = price;
            } else if (price - minBuyPrice > profit) {
                profit = price - minBuyPrice;
            }
        }

        return profit;
    }
}
