package org.fransanchez.exercises.hashing;

import java.util.HashMap;

// 525. Contiguous Array
public class ContiguousArray {
    public int findMaxLength(final int[] nums) {
        final var counts = new HashMap<Integer, Integer>();
        counts.put(0, -1);

        var currentCount = 0;
        var maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                currentCount--;
            } else {
                currentCount++;
            }

            if (counts.containsKey(currentCount)) {
                maxLength = Math.max(maxLength, i - counts.get(currentCount));
            } else {
                counts.put(currentCount, i);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new ContiguousArray().findMaxLength(new int[] { 0,1,0,1}));
    }
}
