package exercises.hashing;

import java.util.HashMap;

// 3. Longest Substring Without Repeating Characters
public class LongestSubstringNoRepeatingCharacters {
    public int lengthOfLongestSubstring(final String s) {
        // final var counters = new int[26]; // if only letters
        final var counters = new HashMap<Character, Integer>();
        var left = 0;
        var currCount = 0;
        var result = 0;

        for (int right = 0; right < s.length(); right++) {
            // Increase Right
            final var rightLetter = s.charAt(right);
            final var rightCount = counters.getOrDefault(rightLetter, 0) + 1;
            counters.put(rightLetter, rightCount);
            if (rightCount > 1) {
                currCount++;
            }

            // Increase Left
            while(currCount > 0) {
                final var leftLetter = s.charAt(left);
                final var leftCount = counters.getOrDefault(leftLetter, 0) - 1;
                counters.put(leftLetter, leftCount);
                if (leftCount == 1) {
                    currCount--;
                }
                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }

    public static void main(final String[] args) {
        final var sut = new LongestSubstringNoRepeatingCharacters();
        final var result = sut.lengthOfLongestSubstring("pwwkew");

        System.out.println(result);
    }
}
