package org.fransanchez.exercises.binarysearch;

import org.fransanchez.exercises.binarysearch.SearchInsertPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchInsertPositionTest {

    private final SearchInsertPosition sut = new SearchInsertPosition();

    @Test
    public void case1() {
        final var result = sut.searchInsert(new int[] { 1,3,5,6 }, 5);
        assertEquals(2, result);
    }

    @Test
    public void case2() {
        final var result = sut.searchInsert(new int[] { 1,3,5,6 }, 2);
        assertEquals(1, result);
    }

    @Test
    public void case3() {
        final var result = sut.searchInsert(new int[] { 1,3,5,6 }, 7);
        assertEquals(4, result);
    }
}