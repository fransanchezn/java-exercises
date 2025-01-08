package org.fransanchez.exercises.arrayandstrings.slidingwindow.variable;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BestTimeBuySellStockTest {

    private BestTimeBuySellStock sut = new BestTimeBuySellStock();

    @Test
    void givenArray_whenCallingMethod_returnMaxProfix() {
        final var input = new int[] {7,1,5,3,6,4};

        final var result = sut.maxProfit(input);

        assertThat(result).isEqualTo(5);
    }
}