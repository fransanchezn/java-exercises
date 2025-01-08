package org.fransanchez.exercises.arrayandstrings.slidingwindow.fix;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PermutationInStringTest {

    final PermutationInString sut = new PermutationInString();

    @Test
    void givenTwoString_whenCallingMethod_thenReturnTrue() {
        final var result = sut.checkInclusion("ab", "asdfbawa");

        assertThat(result).isTrue();
    }
}