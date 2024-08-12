package exercises.twopointers;

public class ValidPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("Am"));
    }

    // Input: s = "A man, a plan, a canal: Panama"
    // Output: true
    // Explanation: "amanaplanacanalpanama" is a palindrome.
    private static boolean isPalindrome(final String input) {
        var frontIndex = 0;
        var backIndex = input.length() - 1;
        var isPalindrome = true;

        while (frontIndex < backIndex && isPalindrome) {
            var frontChar = input.charAt(frontIndex);
            var backChar = input.charAt(backIndex);

            var frontAlphanumeric = Character.isLetterOrDigit(frontChar);
            var backAlphanumeric = Character.isLetterOrDigit(backChar);
            if (!frontAlphanumeric && !backAlphanumeric) {
                frontIndex++;
                backIndex--;
            } else if (!frontAlphanumeric) {
                frontIndex++;
            } else if (!backAlphanumeric) {
                backIndex--;
            } else if (Character.toLowerCase(frontChar) != Character.toLowerCase(backChar)) {
                isPalindrome = false;
            } else {
                frontIndex++;
                backIndex--;
            }
        }

        return isPalindrome;
    }
}
