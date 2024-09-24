package org.fransanchez.exercises.arrayandstrings.twopointer;

// 557. Reverse Words in a String III
public class ReverseWordsString3 {
    public String reverseWords(final String s) {
        final var splitInput = s.split(" ");
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < splitInput.length; i++) {
            final var word = splitInput[i];
            for (int j = word.length() - 1; j >= 0; j--) {
                sb.append(word.charAt(j));
            }

            if (i < splitInput.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        final var sut = new ReverseWordsString3();
        final var result = sut.reverseWords("Let's take LeetCode contest");

        System.out.println(result);
    }
}
