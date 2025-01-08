package org.fransanchez.exercises.stacksandqueues.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

// 150. Evaluate Reverse Polish Notation
public class EvaluateReversePolishNotation {
    private final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS = new HashMap<>() {{
        put("+", (f,s) -> f + s);
        put("-", (f,s) -> f - s);
        put("*", (f, s) -> f * s);
        put("/", (f, s) -> f / s);
    }};

    public int evalRPN(final String[] tokens) {
        final var stack = new Stack<Integer>();

        for (String token : tokens) {
            if (OPERATIONS.containsKey(token)) {
                final var second = stack.pop();
                final var first = stack.pop();
                final var result = OPERATIONS.get(token).apply(first, second);
                stack.add(result);
            } else {
                stack.add(Integer.parseInt(token));
            }
        }

        return stack.peek();
    }
}
