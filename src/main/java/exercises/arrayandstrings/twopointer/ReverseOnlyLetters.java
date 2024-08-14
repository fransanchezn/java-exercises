package exercises.arrayandstrings.twopointer;

// 917. Reverse Only Letters
public class ReverseOnlyLetters {

    // "Test1ng-Leet=code-Q!"
    // "Qedo1ct-eeLg=ntse-T!"
    public String reverseOnlyLetters(final String s) {
        final var input = s.toCharArray();
        int left = 0;
        int right = input.length - 1;

        while (left < right) {
            final var charLeft = input[left];
            final var charRight = input[right];

            if (isLetter(charLeft) && isLetter(charRight)) {
                input[right] = charLeft;
                input[left] = charRight;
                left++;
                right--;
            } else if (isLetter(charLeft)) {
                right--;
            } else {
                left++;
            }
        }

        return new String(input);
    }

    private boolean isLetter(final char c) {
        return Character.isLetter(c);
    }

    public static void main(String[] args) {
        final var sut = new ReverseOnlyLetters();
        final var result = sut.reverseOnlyLetters("Test1ng-Leet=code-Q!");

        System.out.println(result);
    }
}
