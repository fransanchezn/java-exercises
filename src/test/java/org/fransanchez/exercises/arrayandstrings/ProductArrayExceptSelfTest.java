package org.fransanchez.exercises.arrayandstrings;

import org.fransanchez.exercises.arrayandstrings.prefixsum.ProductArrayExceptSelf;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductArrayExceptSelfTest {

    private final ProductArrayExceptSelf sut = new ProductArrayExceptSelf();

    @Test
    void givenAnArray_whenCallingMethod_returnsArray() {
        final var result = sut.productExceptSelf(new int[] {1,2,3,4});
        assertThat(result).containsExactly(24,12,8,6);
    }

    @Test
    void givenAnArrayWithNegative_whenCallingMethod_returnsArray() {
        final var result = sut.productExceptSelf(new int[] {-1,1,0,-3,3});
        assertThat(result).containsExactly(0,0,9,0,0);
    }
}