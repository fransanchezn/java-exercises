package exercises.graphs.bfs;

import java.util.LinkedList;

// 542. 01 Matrix
public class DistanceZeroMatrix {
    private final int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[][] updateMatrix(final int[][] mat) {
        final var queue = new LinkedList<State>();
        final var seen = new boolean[mat.length][mat[0].length];

        final var result = new int[mat.length][mat[0].length];

        for (int x = 0; x < mat.length; x++) {
            for (int y = 0; y < mat[x].length; y++) {
                if (mat[x][y] == 0) {
                    seen[x][y] = true;
                    queue.add(new State(x, y, 0));
                }
            }
        }

        while(!queue.isEmpty()) {
            final var node = queue.poll();
            for (var direction: directions) {
                final var nextRow = node.row + direction[0];
                final var nextCol = node.col + direction[1];
                final var nextStep = node.step + 1;

                if (valid(nextRow, nextCol, mat) && !seen[nextRow][nextCol]) {
                    seen[nextRow][nextCol] = true;
                    result[nextRow][nextCol] = nextStep;
                    queue.add(new State(nextRow, nextCol, nextStep));
                }
            }
        }

        return result;
    }

    private boolean valid(int row, int col, int[][] mat) {
        return 0 <= row && row < mat.length && 0 <= col && col < mat[row].length && mat[row][col] == 1;
    }

    private record State(int row, int col, int step) { }

    public static void main(String[] args) {

    }
}
