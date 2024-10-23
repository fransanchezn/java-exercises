package org.fransanchez.exercises.hashing;

import java.util.ArrayList;
import java.util.HashSet;

// 349. Intersection of Two Arrays
public class IntersectionTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        final var numbers = new HashSet<Integer>();
        final var intersection = new ArrayList<Integer>();

        for (int num: nums1) {
            numbers.add(num);
        }

        for (int num: nums2) {
            if (numbers.remove(num)) {
                intersection.add(num);
            }
        }

        return intersection.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {

    }
}
