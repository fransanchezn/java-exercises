package org.fransanchez.exercises.hashing;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

// 1189. Maximum Number of Balloons
public class MaxNumberBalloons {

    public int maxNumberOfBalloons(final String text) {
        final var balloon = new HashSet<Character>() {{
            add('b');
            add('a');
            add('l');
            add('o');
            add('n');
        }};

        final var counters = new HashMap<Character, Integer>();
        for (Character c: balloon) {
            counters.put(c, 0);
        }

        for (int i = 0; i < text.length(); i++) {
            final var letter = text.charAt(i);
            if (balloon.contains(letter)) {
                counters.put(letter, counters.getOrDefault(letter, 0) + 1);
            }
        }

        return counters.entrySet().stream()
                .map(entry -> entry.getKey() == 'o' || entry.getKey() == 'l' ? entry.getValue() / 2 : entry.getValue())
                .min(Comparator.comparingInt(a -> a)).orElse(0);
    }

    public static void main(String[] args) {
        final var sut = new MaxNumberBalloons();
        final var result = sut.maxNumberOfBalloons("loonbalxballpoon");

        System.out.println(result);
    }
}
