package org.fransanchez.exercises.stacksandqueues.stack;

// 844. Backspace String Compare
public class BackspaceStringCompare {

    // "ab#c", "ad#c"
    // ac == ac
    public boolean backspaceCompare(final String s, final String t) {
        return build(s).equals(build(t));
    }

    private String build(final String s) {
        final var sb = new StringBuilder();

        for (char c: s.toCharArray()) {
            if (c != '#') {
                sb.append(c);
            } else if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        final var sut = new BackspaceStringCompare();
        final var result = sut.backspaceCompare("a##b#c", "a##d#c");

        System.out.println(result);
    }
}
