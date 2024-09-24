package org.fransanchez.exercises.stacksandqueues;

// 2390. Removing Stars From a String
public class RemovingStarsFromString {
    public String removeStars(final String s) {
        final var stack = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            final var character = s.charAt(i);
            if (character == '*') {
                stack.deleteCharAt(stack.length() - 1);
            } else {
                stack.append(character);
            }
        }

        return stack.toString();
    }
}
