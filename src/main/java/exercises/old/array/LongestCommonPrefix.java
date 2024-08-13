package exercises.old.array;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{ "ab", "a" }));
    }

    // Input: strs = ["flower","flow","flight"]
    // Output: "fl"

    private static String longestCommonPrefix(final String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }

        var longestPrefix = new StringBuilder();
        var firstWord = strs[0];
        var isPrefix = true;
        for (int i = 0; i < firstWord.length() && isPrefix; i++) {
            var prefixLetter = firstWord.charAt(i);
            var index = 1;
            while (isPrefix && index < strs.length) {
                var compareWord = strs[index];
                isPrefix = i < compareWord.length() && compareWord.charAt(i) == prefixLetter;
                index++;
            }

            if (isPrefix) {
                longestPrefix.append(prefixLetter);
            }
        }

        return longestPrefix.toString();
    }
}
