package org.fransanchez.exercises.arrayandstrings.slidingwindow.fix;

import java.util.HashSet;

// 219. Contains Duplicate II
public class ContainsDuplicate {
    public boolean containsNearbyDuplicate(final int[] nums, final int k) {
        final var hash = new HashSet<Integer>();
        int l = 0;

        for (int r = 0; r < nums.length; r++) {
            if (r - l > k) {
                hash.remove(nums[l]);
                l++;
            }

            if (hash.contains(nums[r])) {
                return true;
            }

            hash.add(nums[r]);
        }

        return false;
    }
}
