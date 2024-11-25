package org.fransanchez.exercises.backtracking;

import java.util.ArrayList;
import java.util.List;

// 78. Subsets
public class Subsets {
    public List<List<Integer>> subsets(final int[] nums) {
        final var ans = new ArrayList<List<Integer>>();
        backtrack(new ArrayList<>(), 0, nums, ans);
        return ans;
    }

    private void backtrack(final List<Integer> curr, final int index, final int[] nums, final List<List<Integer>> ans) {
        ans.add(new ArrayList<>(curr));

        for (int j = index; j < nums.length; j++) {
            curr.add(nums[j]);
            backtrack(curr, j + 1, nums, ans);
            curr.removeLast();
        }
    }

    private void backtrack2(final List<Integer> curr, final int index, final int[] nums, final List<List<Integer>> ans) {
        if (index >= nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        // Decision make: take number
        curr.add(nums[index]);
        backtrack2(curr, index + 1, nums, ans);

        // Decision make: Not take number
        curr.removeLast();
        backtrack2(curr, index + 1, nums, ans);
    }
}
