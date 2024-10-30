package org.fransanchez.exercises.arrayandstrings;

// 27. Remove Element
public class RemoveElementArray {
    public int removeElement(int[] nums, int val) {
        var indexToInsert = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[indexToInsert] = nums[i];
                indexToInsert++;
            }
        }

        return indexToInsert;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveElementArray().removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }
}
