package org.fransanchez.exercises.binarysearch;

import org.fransanchez.exercises.binarysearch.SuccessfulPairsSpellsPotions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SuccessfulPairsSpellsPotionsTest {

    private final SuccessfulPairsSpellsPotions sut = new SuccessfulPairsSpellsPotions();

    @Test
    public void case1() {
        final var result = sut.successfulPairs(new int[] {5,1,3}, new int[] { 1,2,3,4,5 }, 7);
        assertArrayEquals(new int[] {4,0,3}, result);
    }

    @Test
    public void case2() {
        final var result = sut.successfulPairs(new int[] {3,1,2}, new int[] { 8,5,8 }, 16);
        assertArrayEquals(new int[] {2,0,2}, result);
    }
}