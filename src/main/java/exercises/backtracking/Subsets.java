package exercises.backtracking;

import java.util.ArrayList;
import java.util.List;

// 78. Subsets
public class Subsets {
    public List<List<Integer>> subsets(final int[] nums) {
        final var ans = new ArrayList<List<Integer>>();
        backtrack(new ArrayList<>(), 0, ans, nums);
        return ans;
    }

    private void backtrack(final List<Integer> curr, final int index, final List<List<Integer>> ans, final int[] nums) {
        ans.add(new ArrayList<>(curr));

        for (int j = index; j < nums.length; j++) {
            curr.add(nums[j]);
            backtrack(curr, j + 1, ans, nums);
            curr.removeLast();
        }
    }
}
