package org.fransanchez.deprecated.string;

public class ReverseInt {
    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));

    }

    public static int reverse(int x) {
        final var string = String.valueOf(x);
        var result = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            if (string.charAt(i) != '-') {
                result += string.charAt(i);
            }
        }

        final var longParse = Long.parseLong(result);
        final var longResult = x < 0 ? longParse * -1 : longParse;
        if (longResult > Integer.MAX_VALUE || longResult < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) longResult;
    }
}
