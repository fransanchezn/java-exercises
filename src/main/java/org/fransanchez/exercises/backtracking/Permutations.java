package org.fransanchez.exercises.backtracking;

import java.util.ArrayList;
import java.util.List;

// 46. Permutations
public class Permutations {
    public List<List<Integer>> permute(final int[] nums) {
        final var ans = new ArrayList<List<Integer>>();
        backtrack(new ArrayList<>(), ans, nums);
        return ans;
    }

    private void backtrack(final List<Integer> curr, final List<List<Integer>> ans, final int[] nums) {
        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int num: nums) {
            if (!curr.contains(num)) {
                curr.add(num);
                backtrack(curr, ans, nums);
                curr.removeLast();
            }
        }
    }
}
