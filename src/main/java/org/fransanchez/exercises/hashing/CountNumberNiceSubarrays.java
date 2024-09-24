package org.fransanchez.exercises.hashing;

import java.util.HashMap;

// 1248. Count Number of Nice Subarrays
public class CountNumberNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        final var counts = new HashMap<Integer, Integer>();
        counts.put(0, 1); // Base case

        var prefixSum = 0;
        var result = 0;
        for (int num : nums) {
            if (num % 2 != 0) {
                prefixSum++;
            }
            result += counts.getOrDefault(prefixSum - k, 0);


            counts.put(prefixSum, counts.getOrDefault(prefixSum, 0) + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        final var sut = new CountNumberNiceSubarrays();
        final var result = sut.numberOfSubarrays(new int[] { 1,1,2,1,1 }, 3);

        System.out.println(result);
    }
}
