package exercises.binarysearch;

// 875. Koko Eating Bananas
public class KokoEatingBananas {

    public int minEatingSpeed(final int[] piles, final int h) {
        int left = 1;
        int right = 0;

        for (int bananas: piles) {
            right = Math.max(bananas, right);
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(mid, piles, h)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // Returning the min is left - Max is right
        return left;
    }

    private boolean check(final int k, final int[] piles, final int limit) {
        int hours = 0;
        for (int bananas: piles) {
            hours += (int) Math.ceil((double) bananas / k);
        }

        return hours <= limit;
    }
}
