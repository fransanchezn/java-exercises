package org.fransanchez.exercises.graphs.bfs;

// 695. Max Area of Island
public class MaxAreaIsland {
    private static final int LAND = 1;
    private static final int WATER = 0;
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {-1, -1}};

    public int maxAreaOfIsland(final int[][] grid) {
        var maxIslandSize = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == LAND) {
                    maxIslandSize = Math.max(maxIslandSize, dfs(grid, row, col));
                }
            }
        }

        return maxIslandSize;
    }

    public int dfs(final int[][] grid, final int row, final int col) {
        if (Math.min(row, col) < 0
                || row >= grid.length
                || col >= grid[0].length
                || grid[row][col] == WATER
        ) {
            return 0;
        }

        grid[row][col] = WATER;
        int res = 1;
        for (var dir : DIRECTIONS) {
            res += dfs(grid, row + dir[0], col + dir[1]);
        }

        return res;
    }

    public static void main(String[] args) {
        final var sut = new MaxAreaIsland();
        final var result = sut.maxAreaOfIsland(new int[][] {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}
        });

        System.out.println(result);
    }
}
