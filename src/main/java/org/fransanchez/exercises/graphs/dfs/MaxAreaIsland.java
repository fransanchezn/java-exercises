package org.fransanchez.exercises.graphs.dfs;

// 695. Max Area of Island
public class MaxAreaIsland {
    final int[][] directions = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};
    boolean[][] seen;

    public int maxAreaOfIsland(final int[][] grid) {
        final var rowSize = grid.length;
        final var colSize = grid[0].length;

        seen = new boolean[rowSize][colSize];

        var maxSize = 0;
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (grid[row][col] == 1 && !seen[row][col]) {
                    var currentSize = 1;
                    seen[row][col] = true;
                    currentSize += dfs(row, col, grid);
                    maxSize = Math.max(currentSize, maxSize);
                }
            }
        }

        return maxSize;
    }

    private int dfs(final int row, final int col, final int[][] grid) {
        var size = 0;
        for (int[] direction: directions) {
            final var nextRow = row + direction[0];
            final var nextCol = col + direction[1];
            if (isValid(nextRow, nextCol, grid) && !seen[nextRow][nextCol]) {
                seen[nextRow][nextCol] = true;
                size++;
                size += dfs(nextRow, nextCol, grid);
            }
        }

        return size;
    }

    private boolean isValid(final int row, final int col, final int[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[row].length && grid[row][col] == 1;
    }

    public static void main(String[] args) {
        final var matrix = new int[][] {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};

        final var sut = new MaxAreaIsland();
        final var result = sut.maxAreaOfIsland(matrix);

        System.out.println(result);
    }
}
