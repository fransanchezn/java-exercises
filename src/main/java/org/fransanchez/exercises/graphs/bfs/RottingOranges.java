package org.fransanchez.exercises.graphs.bfs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;

// 994. Rotting Oranges
public class RottingOranges {
    private final int FRESH = 1;
    private final int ROTTEN = 2;
    private final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int orangesRotting(final int[][] grid) {
        final var queue = new LinkedList<Coordinate>();
        final var fresh = new HashSet<Coordinate>();

        // Find out initial rotten oranges
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == ROTTEN) {
                    queue.add(new Coordinate(row, col));
                } else if (grid[row][col] == FRESH) {
                    fresh.add(new Coordinate(row, col));
                }
            }
        }

        var minutes = 0;
        while (!queue.isEmpty() && !fresh.isEmpty()) {
            final var queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                final var rotten = queue.remove();
                for (var dir: DIRECTIONS) {
                    final var row = rotten.row + dir[0];
                    final var col = rotten.col + dir[1];
                    if (Math.min(row, col) >= 0 && row < grid.length && col < grid[0].length
                            && grid[row][col] == FRESH) {
                        final var freshCoordinate = new Coordinate(row, col);
                        queue.add(freshCoordinate);
                        fresh.remove(freshCoordinate);
                        grid[row][col] = ROTTEN;
                    }
                }
            }
            minutes++;
        }

        return fresh.isEmpty() ? minutes : -1;
    }

    private record Coordinate(int row, int col){}

    public static void main(String[] args) {
        final var sut = new RottingOranges();
        final var result = sut.orangesRotting(new int[][] {{2,1,1},{1,1,0},{0,1,1}});
        System.out.println(result);
    }
}
