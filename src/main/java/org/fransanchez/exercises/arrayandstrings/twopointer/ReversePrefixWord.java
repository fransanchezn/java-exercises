package org.fransanchez.exercises.arrayandstrings.twopointer;

// 2000. Reverse Prefix of Word
public class ReversePrefixWord {
    public String reversePrefix(final String word, final char ch) {
        final var input = word.toCharArray();

        var left = 0;
        var right = word.indexOf(ch);

        while (left < right) {
            var tmp = input[left];
            input[left] = input[right];
            input[right] = tmp;
            left++;
            right--;
        }

        return new String(input);
    }

    public String reversePrefix2(final String word, final char ch) {
        int j = word.indexOf(ch);
        if (j != -1) {
            return new StringBuilder(word.substring(0, j + 1)).reverse() + word.substring(j + 1);
        }
        return word;
    }

    public static void main(String[] args) {
        final var sut = new ReversePrefixWord();
        System.out.println(sut.reversePrefix("abcdefd",'d'));
    }
}
