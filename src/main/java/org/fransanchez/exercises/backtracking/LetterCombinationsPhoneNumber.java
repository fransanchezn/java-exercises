package org.fransanchez.exercises.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsPhoneNumber {
    private final Map<String, String> KEYBOARD = new HashMap<>() {{
        put("0", "");
        put("1", "");
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public List<String> letterCombinations(final String digits) {
        final var numbers = digits.split("");
        final var ans = new ArrayList<String>();
        backtracking(new StringBuilder(), ans, 0, numbers);
        return ans;
    }

    private void backtracking(final StringBuilder curr, final List<String> ans, final int index, final String[] digits) {
        if (curr.length() == digits.length) {
            ans.add(curr.toString());
            return;
        }

        final var values = KEYBOARD.getOrDefault(digits[index], "");
        for (int i = 0; i < values.length(); i++) {
            curr.append(values.charAt(i));
            backtracking(curr, ans, index + 1, digits);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}
