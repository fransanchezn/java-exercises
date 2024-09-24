package org.fransanchez.deprecated.dynamicprograming;

public class HouseRobber {

    public static int rob(final int[] nums) {
        var prevNo = 0;// 0, 2, 2, 3
        var prevYes = 0; // 2, 1, 3, 4
        for (int n : nums) {
            var temp = prevNo;
            prevNo = Math.max(prevNo, prevYes);
            prevYes = n + temp;
        }
        return Math.max(prevNo, prevYes);
    }

    public static void main(final String[] args) {
        // [1,2,3,1]
        final var result1 = rob(new int[]{1, 2, 3, 1});
        System.out.println(result1);

        final var result2 = rob(new int[]{2, 7, 9, 3, 1});
        System.out.println(result2);

        // [2,1,1,2]
        final var result3 = rob(new int[]{2,1,1,2});
        System.out.println(result3);
    }
}
