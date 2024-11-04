package org.fransanchez.exercises.stacksandqueues.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ImplementStackQueuesTest {

    @Test
    public void asd() {
        final var stack = new ImplementStackQueues();
        stack.push(1);
        stack.push(2);

        Assertions.assertEquals(2, stack.pop());
        Assertions.assertEquals(1, stack.pop());
        Assertions.assertTrue(stack.empty());
    }

}