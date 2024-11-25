package org.fransanchez.exercises.backtracking;

import java.util.List;
import org.fransanchez.exercises.backtracking.LetterCombinationsPhoneNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class LetterCombinationsPhoneNumberTest {

    private final LetterCombinationsPhoneNumber sut = new LetterCombinationsPhoneNumber();

    @Test
    public void case1() {
        final var result = sut.letterCombinations("23");

        final var expected = List.of("ad","ae","af","bd","be","bf","cd","ce","cf");
        assertIterableEquals(expected, result);
    }

    @Test
    public void case2() {
        final var result = sut.letterCombinations("");

        final var expected = List.of();
        assertIterableEquals(expected, result);
    }
}