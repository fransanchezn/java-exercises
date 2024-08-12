package exercises.string;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        final var r = longestPalindrome("ac");
        System.out.println(r);
    }

    public static String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        var maxPalindrome = s.charAt(0) + "";
        for (int i = 0; i < s.length(); i++) {
            var palindrome = s.charAt(i) + "";
            final var last = s.lastIndexOf(palindrome);
            for (int j = i + 1; j <= last; j++) {
                palindrome += s.charAt(j);
                if (isPalindrome(palindrome)) {
                    maxPalindrome = maxPalindrome.length() < palindrome.length() ? palindrome : maxPalindrome;
                }
            }
        }

        return maxPalindrome;
    }

    private static boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return false;
        }

        final var middle = s.length() / 2;
        for (int i = 0, j = s.length() - 1; i <= middle; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }

        return true;
    }
}
