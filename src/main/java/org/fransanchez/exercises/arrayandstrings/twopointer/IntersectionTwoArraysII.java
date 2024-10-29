package org.fransanchez.exercises.arrayandstrings.twopointer;

import java.util.ArrayList;
import java.util.HashMap;

public class IntersectionTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        final var hash = new HashMap<Integer, Integer>();
        final var result = new ArrayList<Integer>();

        for (var num: nums1) {
            hash.put(num, hash.getOrDefault(num, 0) + 1);
        }

        for (var num: nums2) {
            final var intersect = hash.get(num);
            if (intersect != null && intersect > 0) {
                result.add(num);
                hash.put(num, intersect - 1);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        new IntersectionTwoArraysII().intersect(new int[] {4,9,5}, new int[] { 9,4,9,8,4 });
    }
}
