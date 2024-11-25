package org.fransanchez.exercises.hashing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContainsDuplicateTest {

    private final ContainsDuplicate sut = new ContainsDuplicate();

    @Test
    void givenDuplicates_whenCallingMethod_returnsTrue() {
        final var result = sut.containsDuplicate(new int[] {1, 1, 1});
        assertThat(result).isTrue();
    }

    @Test
    void givenNonDuplicates_whenCallingMethod_returnstrue() {
        final var result = sut.containsDuplicate(new int[] {1, 2, 3});
        assertThat(result).isFalse();
    }
}