package org.fransanchez.exercises.hashing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 2248. Intersection of Multiple Arrays
public class IntersectionMultipleArrays {
    public List<Integer> intersection(final int[][] nums) {
        final var occ = new HashMap<Integer, Integer>();
        for (int[] num : nums) {
            for (int i : num) {
                occ.compute(i, (k, v) -> v == null ? 1 : v + 1);
            }
        }

        return occ.entrySet().stream().filter(e -> e.getValue() > nums.length - 1).map(Map.Entry::getKey).sorted().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        final var sut = new IntersectionMultipleArrays();
        final var result = sut.intersection(new int[][] { {3,1,2,4,5}, {1,2,3,4}, {3,4,5,6} });

        System.out.println(result);
    }
}
