package org.fransanchez.usecases.connectfour;

import java.util.Optional;

public class Board {
    private final Chip[][] grid;
    private int totalChips;

    public Board(final int size) {
        this.grid = new Chip[size][size];
    }

    public Board(final Board board) {
        this.grid = new Chip[board.grid.length][board.grid[0].length];
        this.totalChips = 0;
    }

    public Optional<ChipPlacement> placeChip(final int column, final Chip chip) {
        for (int i = grid.length - 1; i >= 0; i--) {
            if (grid[i][column] == null) {
                grid[i][column] = chip;
                totalChips++;
                return Optional.of(new ChipPlacement(i, column));
            }
        }

        return Optional.empty();
    }

    public boolean isFull() {
        return totalChips >= grid.length * grid[0].length;
    }

    public boolean checkWinningPlay(final ChipPlacement chipPlacement, final int target) {
        final var row = chipPlacement.row;
        final var col = chipPlacement.column;
        final var color = grid[row][col].color();

        // Check Horizontally
        int count = 0;
        for (int i = 0; i < grid[0].length; i++) {
          if (grid[row][i] != null && grid[row][i].color() == color) {
           count++;
          } else {
              count = 0;
          }

          if (count >= target) {
              return true;
          }
        }

        // Check Vertically
        count = 0;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col] != null && grid[i][col].color() == color) {
                count++;
            } else {
                count = 0;
            }

            if (count >= target) {
                return true;
            }
        }

        return false;
    }

    public record ChipPlacement(int row, int column) {}
}


