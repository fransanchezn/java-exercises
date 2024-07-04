package hashmap;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
        final var result = twoSum(new int[]{2,7,11,15}, 9);
        System.out.println(Arrays.toString(result));
    }

    // nums = [2,7,11,15], target = 9
    public static int[] twoSum(final int[] nums, final int target) {
        final var differenceHash = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            final var number = nums[i];

            // Difference already calculated?
            final var sum = differenceHash.get(number);
            if (sum != null) {
                return new int[] {sum, i };
            }

            // Save difference and location
            final var difference = target - number;
            differenceHash.put(difference, i);
        }

        return new int[0];
    }
}
