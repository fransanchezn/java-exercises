package exercises.old.dynamicprograming;

public class BestTimeToBuy {

    public static int maxProfit(int[] prices) {
        var maxProfit = 0;
        var cheapestBuy = prices[0];
        for (int i = 0; i < prices.length; i++) {
            cheapestBuy = Math.min(cheapestBuy, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - cheapestBuy);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        // [7,1,5,3,6,4]
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));

        // [7,6,4,3,1]
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
    }
}
