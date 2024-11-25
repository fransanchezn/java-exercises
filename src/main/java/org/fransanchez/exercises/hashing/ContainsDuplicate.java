package org.fransanchez.exercises.hashing;

import java.util.HashSet;

// 217. Contains Duplicate
public class ContainsDuplicate {
    public boolean containsDuplicate(final int[] nums) {
        final var set = new HashSet<Integer>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }

        return false;
    }
}
