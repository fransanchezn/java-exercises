package exercises.stacks;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    private static final Map<Character, Character> PARENTHESIS = new HashMap<>() {{
        put(')', '(');
        put(']', '[');
        put('}', '{');
    }};

    public static void main(final String[] args) {
        System.out.println(isValid("()"));
    }

    public static boolean isValid(final String s) {
        final var stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            final var character = s.charAt(i);
            final var closingParenthesis = PARENTHESIS.get(character);
            if (closingParenthesis == null) {
                stack.push(character);
            } else if (stack.isEmpty() || stack.peek() != closingParenthesis) {
                return false;
            } else {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
