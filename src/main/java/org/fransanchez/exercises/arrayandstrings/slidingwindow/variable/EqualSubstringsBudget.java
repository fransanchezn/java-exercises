package org.fransanchez.exercises.arrayandstrings.slidingwindow.variable;

// 1208. Get Equal Substrings Within Budget
// Moving sliding window
public class EqualSubstringsBudget {
    public int equalSubstring(final String s, final String t, final int maxCost) {
        var left = 0;
        var sum = 0;
        var result = 0;

        for (var right = 0; right < s.length(); right++) {
            sum += Math.abs(s.charAt(right) - t.charAt(right));

            while (sum > maxCost) {
                sum -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        final var sut = new EqualSubstringsBudget();
        final var result = sut.equalSubstring("abcd", "bcdf", 3);

        System.out.println(result);
    }
}
