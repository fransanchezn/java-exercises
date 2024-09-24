package org.fransanchez.exercises.greedy;

import org.fransanchez.exercises.greedy.ReduceArraySizeHalf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReduceArraySizeHalfTest {

    private final ReduceArraySizeHalf sut = new ReduceArraySizeHalf();

    @Test
    public void case1() {
        final var result = sut.minSetSize(new int[] { 3,3,3,3,5,5,5,2,2,7 });
        assertEquals(2, result);
    }

    @Test
    public void case2() {
        final var result = sut.minSetSize(new int[] { 55,58,60,66,99,90,92,54,11,47,43,12,94,2,83,31,81,1,55,86,51,75,2,23,51,18,5,84,94,5,11,31,27,81,33,34,1,42,79,80,3,22,85,53,21,47,10,35,77,100,9,3,28,93 });
        assertEquals(16, result);
    }
}