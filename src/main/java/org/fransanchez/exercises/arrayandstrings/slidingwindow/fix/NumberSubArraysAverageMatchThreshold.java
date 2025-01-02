package org.fransanchez.exercises.arrayandstrings.slidingwindow.fix;

// 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
public class NumberSubArraysAverageMatchThreshold {
    public int numOfSubarrays(final int[] arr, final int k, final int threshold) {
        int l = 0;
        int windowSum = 0;
        int windowAvg = 0;
        int subArrays = 0;

        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        windowAvg = windowSum/k;
        if (windowAvg >= threshold) {
            subArrays++;
        }

        for (int r = k; r < arr.length; r++) {
            windowSum -= arr[l];
            l++;

            windowSum += arr[r];

            windowAvg =  windowSum/k;
            if (windowAvg >= threshold) {
                subArrays++;
            }
        }

        return subArrays;
    }

    public static void main(String[] args) {
        final var sut = new NumberSubArraysAverageMatchThreshold();
        final var result = sut.numOfSubarrays(new int[] { 11,13,17,23,29,31,7,5,2,3 }, 3, 4);
        System.out.println(result);
    }
}