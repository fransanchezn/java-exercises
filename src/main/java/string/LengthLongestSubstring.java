package string;

import java.util.HashMap;

public class LengthLongestSubstring {

    public static void main(String[] args) {
        // "abcafcbb"
        // 3 - "abc"
        System.out.println(lengthOfLongestSubstring("abba"));
    }

    public static int lengthOfLongestSubstring(String s) {
        final var map = new HashMap<String, Integer>();
        final var chars = s.toCharArray();

        int minIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < chars.length; i++) {
            final var letter = chars[i] + "";

            if (map.containsKey(letter)) {
                minIndex = Math.max(map.get(letter) + 1, minIndex);
            }

            final var length = (i - minIndex) + 1;
            maxLength = Math.max(maxLength, length);

            map.put(letter, i);
        }

        return maxLength;
    }
}
