package org.fransanchez.exercises.arrayandstrings.twopointer;

// 2540. Minimum Common Value
public class MinimumCommonValue {
    public int getCommon(final int[] nums1, final int[] nums2) {
        var num1Pointer = 0;
        var num2Pointer = 0;

        while (num1Pointer < nums1.length && num2Pointer < nums2.length) {
            if (nums1[num1Pointer] == nums2[num2Pointer]) {
                return nums1[num1Pointer];
            } else if (nums1[num1Pointer] > nums2[num2Pointer]) {
                num2Pointer++;
            } else {
                num1Pointer++;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        final var sut = new MinimumCommonValue();
        final var result = sut.getCommon(new int[]{ 1,2,3,6 }, new int[]{ 2,3,4,5 });
        System.out.println(result);
    }
}
