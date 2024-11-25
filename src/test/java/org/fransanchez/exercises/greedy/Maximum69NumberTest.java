package org.fransanchez.exercises.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Maximum69NumberTest {

    final Maximum69Number sut = new Maximum69Number();

    @Test
    public void case1() {
        final var result = sut.maximum69Number(9669);

        assertEquals(9969, result);
    }
}