package org.fransanchez.exercises.arrayandstrings;

import java.util.HashSet;

// 128. Longest Consecutive Sequence
public class LongestConsecutiveSequence {
    // Algorithm needs to be O(n) so no sorting allowed
    public int longestConsecutive(final int[] nums) {
        final var numbers = new HashSet<Integer>();
        var maxSequence = 0;

        // O(n)
        for (var num: nums) {
            numbers.add(num);
        }

        // O(n)
        for (int num : nums) {
            if (!numbers.contains(num - 1)) {
                var sequence = 1;
                while (numbers.contains(num + sequence)) {
                    sequence++;
                }
                maxSequence = Math.max(maxSequence, sequence);
            }
        }

        return maxSequence;
    }
}
