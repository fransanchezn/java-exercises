package org.fransanchez.exercises.backtracking;

import java.util.ArrayList;
import java.util.List;

// 967. Numbers With Same Consecutive Differences
public class NumbersSameConsecutiveDifferences {
    public int[] numsSameConsecDiff(final int n, final int k) {
        final var ans = new ArrayList<Integer>();
        for(int i = 1; i < 10; i++) {
            backtracking(new StringBuilder().append(i), ans, n, k);
        }

        return ans.stream().mapToInt(i -> i).toArray();
    }

    private void backtracking(final StringBuilder curr, final List<Integer> ans, final int size, final int maxDiff) {
        if (curr.length() == size) {
            ans.add(Integer.valueOf(curr.toString()));
            return;
        }

        final int lastDigit = curr.charAt(curr.length() - 1) - '0';
        final var upper = lastDigit + maxDiff;
        final var lower = lastDigit - maxDiff;

        if (upper < 10) {
            curr.append(upper);
            backtracking(curr, ans, size, maxDiff);
            curr.deleteCharAt(curr.length() - 1);
        }

        if (lower != upper && lower >= 0) {
            curr.append(lower);
            backtracking(curr, ans, size, maxDiff);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}