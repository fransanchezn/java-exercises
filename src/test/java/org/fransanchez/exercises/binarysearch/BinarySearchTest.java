package org.fransanchez.exercises.binarysearch;

import org.fransanchez.exercises.binarysearch.BinarySearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    private final BinarySearch sut = new BinarySearch();

    @Test
    public void case1() {
        final var result = sut.search(new int[] {-1,0,3,5,9,12}, 9);
        assertEquals(4, result);
    }

    @Test
    public void case2() {
        final var result = sut.search(new int[] {-1,0,3,5,9,12}, 2);
        assertEquals(-1, result);
    }

    @Test
    public void case3() {
        final var result = sut.search(new int[] {5}, 5);
        assertEquals(0, result);
    }
}