package exercises.old.array;

public class BestTimeBuySellStockAdvanced {
    public static void main(String[] args) {
        var prices = new int[] { 7,1,5,6,6,4 };

        var result = maxProfit1(prices);

        System.out.println(result);
    }

    public static int maxProfit1(int[] prices) {
        var buyPrice = prices[0];
        var accumulatedProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            var currentPrice = prices[i];
            if (currentPrice > buyPrice) {
                // Sell
                accumulatedProfit += currentPrice - buyPrice;
            }
            buyPrice = currentPrice;
        }

        return accumulatedProfit;
    }
}
