package org.fransanchez.exercises.hashing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TwoSumTest {

    private final TwoSum sut = new TwoSum();

    @Test
    void givenArrayMatchingSum_whenCallingMethod_thenReturnRightIndeces() {
        final var result = sut.twoSum(new int[] {1, 2, 3, 4}, 3);
        assertThat(result).isEqualTo(new int[] {0, 1});
    }

}