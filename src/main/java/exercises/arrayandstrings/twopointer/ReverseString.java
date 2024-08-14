package exercises.arrayandstrings.twopointer;

// 344. Reverse String
public class ReverseString {
    public void reverseString(final char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            final var leftChar = s[left];
            s[left] = s[right];
            s[right] = leftChar;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        final var sot = new ReverseString();
        final var input = "hello".toCharArray();
        sot.reverseString(input);

        System.out.println(input);
    }
}
