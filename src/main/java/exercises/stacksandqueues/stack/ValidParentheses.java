package exercises.stacksandqueues.stack;

import java.util.HashMap;
import java.util.Stack;

// 20. Valid Parentheses
public class ValidParentheses {
    public boolean isValid(final String s) {
        final var matching = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};

        final var stack = new Stack<Character>();
        for (int i = 0 ; i < s.length(); i++) {
            final var character = s.charAt(i);

            if (matching.containsKey(character)) {
                stack.push(character);
            } else {
                if (stack.empty()) {
                    return false;
                }

                final var openingBracket = stack.pop();
                if (matching.get(openingBracket) != character) {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println((int) '{' + " " + (int) '}');
        System.out.println((int) '[' + " " + (int) ']');
        System.out.println((int) '(' + " " + (int) ')');

        final var sut = new ValidParentheses();
        final var result = sut.isValid("()");

        System.out.println(result);
    }
}
