package org.fransanchez.exercises.binarysearch;

import org.fransanchez.exercises.binarysearch.KokoEatingBananas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KokoEatingBananasTest {

    private final KokoEatingBananas sut = new KokoEatingBananas();

    @Test
    public void case1() {
        final var result = sut.minEatingSpeed(new int[] { 3,6,7,11 }, 8);
        assertEquals(4, result);
    }

    @Test
    public void case2() {
        final var result = sut.minEatingSpeed(new int[] { 30,11,23,4,20 }, 5);
        assertEquals(30, result);
    }

    @Test
    public void case3() {
        final var result = sut.minEatingSpeed(new int[] { 30,11,23,4,20 }, 6);
        assertEquals(23, result);
    }
}