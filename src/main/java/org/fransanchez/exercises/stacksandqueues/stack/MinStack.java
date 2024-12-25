package org.fransanchez.exercises.stacksandqueues.stack;

import java.util.Stack;

// 155. Min Stack
public class MinStack {
    private final Stack<Entry> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(final int val) {
        final var currentMinValue = stack.isEmpty()
                ? val
                : Math.min(val, stack.peek().minValue);
        stack.push(new Entry(val, currentMinValue));
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().value;
    }

    public int getMin() {
        return stack.peek().minValue;
    }

    public static class Entry {
        Integer value;
        Integer minValue;

        public Entry(Integer value, Integer minValue) {
            this.value = value;
            this.minValue = minValue;
        }
    }

    public static void main(String[] args) {
        final var minStack = new MinStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.push(2147483647);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.push(-2147483648);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());

    }
}
