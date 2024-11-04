package org.fransanchez.exercises.binarysearch;

// 74. Search a 2D Matrix
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        var left = 0;
        var right = (matrix.length * matrix[0].length) - 1;
        while (left <= right) {
            final var middle = (left + right) / 2;
            final var col = middle / matrix[0].length;
            final var row = middle % matrix[0].length;
            final var middleNumber = matrix[col][row];
            if (middleNumber > target) {
                right = middle - 1;
            } else if (middleNumber < target) {
                left = middle + 1;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Search2DMatrix().searchMatrix(new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3));
    }
}
