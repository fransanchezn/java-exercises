package org.fransanchez.exercises.arrayandstrings;

import java.util.ArrayList;
import java.util.List;

// Encode and Decode Strings
public class EncodeDecodeStrings {
    private static final char DELIMITER = '#';

    public String encode(final List<String> strs) {
        final var sb = new StringBuilder();
        for (var word : strs) {
            sb.append(word.length()).append(DELIMITER)
                    .append(word);
        }

        return sb.toString();
    }

    public List<String> decode(final String str) {
        final var decodedStrings = new ArrayList<String>();
        var index = 0;
        while (index < str.length()) {
            final var size = new StringBuilder();
            while (str.charAt(index) != DELIMITER) {
                size.append(str.charAt(index));
                index++;
            }

            // Skip delimiter
            index++;

            final var word = new StringBuilder();
            final var wordSize = Integer.parseInt(size.toString());
            for (int i = 0 ; i < wordSize; i++) {
                word.append(str.charAt(index));
                index++;
            }
            decodedStrings.add(word.toString());
        }

        return decodedStrings;
    }

    public static void main(String[] args) {
        final var sut = new EncodeDecodeStrings();
        final var encoded = sut.encode(List.of("asd", "42sa"));
        System.out.println(encoded);

        final var strings = sut.decode(encoded);
        System.out.println(strings);
    }
}
