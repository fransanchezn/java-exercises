package org.fransanchez.exercises.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(final int n, final int k) {
        final var ans = new ArrayList<List<Integer>>();
        backtrack(new ArrayList<>(), 1, ans, n, k);
        return ans;
    }

    private void backtrack(final List<Integer> curr, final int index, final List<List<Integer>> ans, final int maxNumber, final int size) {
        if (curr.size() == size) {
            ans.add(new ArrayList<>(curr));
        }

        for (int i = index; i <= maxNumber; i++) {
            curr.add(i);
            backtrack(curr, i + 1, ans, maxNumber, size);
            curr.removeLast();
        }
    }
}
