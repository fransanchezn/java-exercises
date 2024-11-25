package org.fransanchez.exercises.binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class LongestSubsequenceWithLimitedSumTest {

    private final LongestSubsequenceWithLimitedSum sut = new LongestSubsequenceWithLimitedSum();

    @Test
    public void case1() {
        final var result = sut.answerQueries(new int[] { 4,5,2,1 }, new int[] { 3,10,21 });
        assertArrayEquals(new int[] { 2,3,4 }, result);
    }
}