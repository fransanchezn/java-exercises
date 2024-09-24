package org.fransanchez.exercises.greedy;

import org.fransanchez.exercises.greedy.PartitionArrayMaximumDifferenceK;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartitionArrayMaximumDifferenceKTest {

    private PartitionArrayMaximumDifferenceK sut = new PartitionArrayMaximumDifferenceK();

    @Test
    public void case1() {
        final var result = sut.partitionArray(new int[] { 3,6,1,2,5 }, 2);

        assertEquals(2, result);
    }

}