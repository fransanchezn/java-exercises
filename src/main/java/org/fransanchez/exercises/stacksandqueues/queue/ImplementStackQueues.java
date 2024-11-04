package org.fransanchez.exercises.stacksandqueues.queue;

import java.util.ArrayDeque;
import java.util.Deque;

// 225. Implement Stack using Queues
public class ImplementStackQueues {
    private final Deque<Integer> queue;

    public ImplementStackQueues() {
        queue = new ArrayDeque<>();
    }

    public void push(final int x) {
        queue.push(x);
    }

    public int pop() {
        return queue.pollFirst();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
