package deprecated.twopointers;

public class Subsequence {
    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "ahbgdc"));
    }

    // Input: s = "abc", t = "ahbgdc"
    // Output: true
    private static boolean isSubsequence(final String subsequence, final String text) {
        var textIndex = 0;
        var sequenceIndex = 0;
        while (sequenceIndex < subsequence.length() && textIndex < text.length()) {
            if (subsequence.charAt(sequenceIndex) == text.charAt(textIndex)) {
                sequenceIndex++;
            }

            textIndex++;
        }

        return sequenceIndex == subsequence.length();
    }
}
