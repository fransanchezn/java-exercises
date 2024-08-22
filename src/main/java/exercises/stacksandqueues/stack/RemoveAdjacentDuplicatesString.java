package exercises.stacksandqueues.stack;

import java.util.Stack;

// 1047. Remove All Adjacent Duplicates In String
public class RemoveAdjacentDuplicatesString {
    // "abbaca"
    // "ca"
    public String removeDuplicates(final String s) {
        final var stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            final var letter = s.charAt(i);
            if (!stack.empty() && stack.peek() == letter) {
                stack.pop();
            } else {
                stack.push(letter);
            }
        }

        final var sb = new StringBuilder();
        while (!stack.empty()) {
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        final var sut = new RemoveAdjacentDuplicatesString();
        final var result = sut.removeDuplicates("abbaca");

        System.out.println(result);
    }
}
