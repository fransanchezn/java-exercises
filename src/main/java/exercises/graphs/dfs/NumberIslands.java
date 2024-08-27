package exercises.graphs.dfs;

public class NumberIslands {
    int rows;
    int columns;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    boolean[][] seen;

    public int numIslands(final char[][] grid) {
        int ans = 0;
        rows = grid.length;
        columns = grid[0].length;
        seen = new boolean[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (grid[row][col] == '1' && !seen[row][col]) {
                    ans++;
                    seen[row][col] = true;
                    dfs(row, col, grid);
                }
            }
        }

        return ans;
    }

    private void dfs(final int row, final int col, final char[][] grid) {
        for (int[] direction: directions) {
            final var nextRow = row + direction[0];
            final var nextCol = col + direction[1];

            if (valid(nextRow, nextCol, grid) && !seen[nextRow][nextCol]) {
                seen[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, grid);
            }
        }
    }

    private boolean valid(int row, int col, char[][] grid) {
        return 0 <= row && row < rows && 0 <= col && col < columns && grid[row][col] == '1';
    }

    public static void main(String[] args) {
        final var edges = new char[][]
                {{'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};

        final var sut = new NumberIslands();
        final var result = sut.numIslands(edges);

        System.out.println(result);
    }
}
