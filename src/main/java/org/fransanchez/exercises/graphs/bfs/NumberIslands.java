package org.fransanchez.exercises.graphs.bfs;

import java.util.List;

// 200. Number of Islands
public class NumberIslands {
    private static final char LAND = '1';
    private static final char WATER = '0';

    public int numIslands(final char[][] grid) {
        var count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == LAND) {
                    dfs(grid, new Coordinate(row, col));
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(final char[][] grid, final Coordinate initial) {
        final var rows = grid.length;
        final var cols = grid[0].length;

        if (Math.min(initial.row, initial.col) < 0
                || initial.row >= rows || initial.col >= cols
                || grid[initial.row][initial.col] == WATER) {
            return;
        }

        grid[initial.row][initial.col] = WATER;

        for (var dir : initial.directions()) {
            dfs(grid, dir);
        }
    }

    private record Coordinate(int row, int col) {
        public List<Coordinate> directions() {
            return List.of(this.up(), this.down(), this.right(), this.left());
        }

        public Coordinate up() {
            return new Coordinate(row + 1, col);
        }

        public Coordinate down() {
            return new Coordinate(row - 1, col);
        }

        public Coordinate right() {
            return new Coordinate(row, col + 1);
        }

        public Coordinate left() {
            return new Coordinate(row, col - 1);
        }
    }

    public static void main(String[] args) {
        final var sut = new NumberIslands();
        final var result = sut.numIslands(new char[][] {
                        {'1','1','0','0','0'},
                        {'1','1','0','0','0'},
                        {'0','0','1','0','0'},
                        {'0','0','0','1','1'}
        });

        System.out.println(result);
    }
}
