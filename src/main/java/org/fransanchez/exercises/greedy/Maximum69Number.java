package org.fransanchez.exercises.greedy;

// 1323. Maximum 69 Number
public class Maximum69Number {
    public int maximum69Number (final int num) {
        final var stringNum = new StringBuilder();
        stringNum.append(num);

        int start = 0;
        boolean switched = false;
        while (start < stringNum.length() && !switched) {
            if (stringNum.charAt(start) == '6') {
                stringNum.setCharAt(start, '9');
                switched = true;
            }
            start++;
        }

        return Integer.parseInt(stringNum.toString());
    }
}
