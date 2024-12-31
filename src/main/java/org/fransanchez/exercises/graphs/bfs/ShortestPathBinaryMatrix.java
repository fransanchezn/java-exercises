package org.fransanchez.exercises.graphs.bfs;

import java.util.LinkedList;

// 1091. Shortest Path in Binary Matrix
public class ShortestPathBinaryMatrix {
    private final int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(final int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }

        final var n = grid.length;
        final boolean[][] visited = new boolean[n][n];
        final var queue = new LinkedList<State>();

        visited[0][0] = true;
        queue.add(new State(0, 0, 1));

        while(!queue.isEmpty()) {
            final var node = queue.poll();
            if (node.row == n - 1 && node.col == n - 1) {
                return node.steps;
            }

            for (var direction: directions) {
                final var nextRow = node.row + direction[0];
                final var nextCol = node.col + direction[1];
                final var nextStep = node.steps + 1;

                if (isValid(nextRow, nextCol, grid) && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.add(new State(nextRow, nextCol, nextStep));
                }
            }
        }

        return -1;
    }

    private boolean isValid(final int row, final int col, final int[][] grid) {
        return 0 <= row && row < grid.length && 0 <= col && col < grid[row].length && grid[row][col] == 0;
    }

    private record State(int row, int col, int steps) { }

    public static void main(String[] args) {
        final var matrix = new int[][] {{0,0,0},{1,1,0},{1,1,0}};

        final var sut = new ShortestPathBinaryMatrix();
        final var result = sut.shortestPathBinaryMatrix(matrix);

        System.out.println(result);
    }
}
