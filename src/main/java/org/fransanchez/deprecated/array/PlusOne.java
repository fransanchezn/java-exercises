package org.fransanchez.deprecated.array;

public class PlusOne {

    public static void main(String[] args) {
        int[] arr = {9, 0, 9};
        int[] res = plusOne(arr);

        for (int i : res) {
            System.out.print(i + ", ");
        }
    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            int valRes = (digits[i] + 1) % 10;
            digits[i] = valRes;

            if (valRes != 0) {
                return digits;
            }
        }

        int res[] = new int[digits.length + 1];
        res[0] = 1;

        return res;
    }
}
