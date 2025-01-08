package org.fransanchez.exercises.stacksandqueues;

import org.fransanchez.exercises.stacksandqueues.stack.EvaluateReversePolishNotation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EvaluateReversePolishNotationTest {

    private final EvaluateReversePolishNotation sut = new EvaluateReversePolishNotation();

    @Test
    void test() {
        final var result = sut.evalRPN(new String[] { "10","6","9","3","+","-11","*","/","*","17","+","5","+" });
        assertThat(result).isEqualTo(22);
    }
}