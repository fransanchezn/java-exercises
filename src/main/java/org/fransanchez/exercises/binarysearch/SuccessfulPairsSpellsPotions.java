package org.fransanchez.exercises.binarysearch;

import java.util.Arrays;

// 2300. Successful Pairs of Spells and Potions
public class SuccessfulPairsSpellsPotions {
    public int[] successfulPairs(final int[] spells, final int[] potions, final long success) {
        Arrays.sort(potions);

        final var result = new int[spells.length];
        for (int i = 0; i < spells.length; i++) {
            final var found = search(potions, (int) Math.ceil((double) success / spells[i]));
            result[i] = found > -1 ? potions.length - found : 0;
        }

        return result;
    }

    private int search(int [] arr, final long target) {
        int left = 0;
        int right = arr.length ;
        while(left < right) {
            final var mid = (right + left) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
