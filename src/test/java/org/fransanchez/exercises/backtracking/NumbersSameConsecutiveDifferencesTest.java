package org.fransanchez.exercises.backtracking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class NumbersSameConsecutiveDifferencesTest {

    private final NumbersSameConsecutiveDifferences sut = new NumbersSameConsecutiveDifferences();

    @Test
    public void case1() {
        final var result = sut.numsSameConsecDiff(3, 7);
        assertArrayEquals(new int[] { 181,292,707,818,929 }, result);
    }
}