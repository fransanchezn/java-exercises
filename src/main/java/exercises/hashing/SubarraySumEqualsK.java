package exercises.hashing;

import java.util.HashMap;

// 560. Subarray Sum Equals K
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        var prefixSum = 0;
        final var counts = new HashMap<Integer, Integer>();
        counts.put(0, 1);
        var result = 0;

        for (int num : nums) {
            prefixSum += num;
            final var found = counts.getOrDefault(prefixSum - k, 0);
            result += found;

            counts.put(prefixSum, counts.getOrDefault(prefixSum, 0) + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        final var sut = new SubarraySumEqualsK();
        final var result = sut.subarraySum(new int[] { 1,1,1 }, 2);

        System.out.println(result);
    }
}
