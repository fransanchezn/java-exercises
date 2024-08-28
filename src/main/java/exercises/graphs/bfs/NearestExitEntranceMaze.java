package exercises.graphs.bfs;

import java.util.LinkedList;

// 1926. Nearest Exit from Entrance in Maze
public class NearestExitEntranceMaze {
    private final char WALL = '+';
    private final int[][] DIRECTIONS = new int[][] {{0,1}, {1,0}, {0, -1}, {-1, 0}};

    public int nearestExit(char[][] maze, int[] entrance) {
        final var seen = new boolean[maze.length][maze[0].length];
        final var queue = new LinkedList<Cell>();

        final var entranceCell = maze[entrance[0]][entrance[1]];
        if (entranceCell == WALL) {
            return -1;
        }

        seen[entrance[0]][entrance[1]] = true;
        queue.add(new Cell(entrance[0], entrance[1], 0));

        while (!queue.isEmpty()) {
            final var currentNode = queue.poll();
            for (var direction : DIRECTIONS) {
                final var nextRow = currentNode.row + direction[0];
                final var nextCol = currentNode.col + direction[1];

                if (!isValid(nextRow, nextCol, maze)) {
                    if (currentNode.row != entrance[0] || currentNode.col != entrance[1]) {
                        return currentNode.steps;
                    }
                } else if (!seen[nextRow][nextCol] && maze[nextRow][nextCol] != WALL ) {
                    final var nextStep = currentNode.steps + 1;
                    seen[nextRow][nextCol] = true;
                    queue.add(new Cell(nextRow, nextCol, nextStep));
                }
            }
        }

        return -1;
    }

    private boolean isValid(final int row, final int col, final char[][] maze) {
        return row >= 0 && row < maze.length
                && col >= 0 && col < maze[row].length;
    }

    private record Cell(int row, int col, int steps) {}

    public static void main(String[] args) {
        final var matrix = new char[][] {{'+','+','+'},{'.','.','.'},{'+','+','+'}};

        final var sut = new NearestExitEntranceMaze();
        final var result = sut.nearestExit(matrix, new int[] { 1,0 });

        System.out.println(result);
    }
}
