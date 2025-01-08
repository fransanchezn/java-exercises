package org.fransanchez.exercises.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// 36. Valid Sudoku
public class ValidSudoku {
    public boolean isValidSudoku(final char[][] board) {
        final var rows = new HashMap<Integer, Set<Integer>>();
        final var cols = new HashMap<Integer, Set<Integer>>();
        final var squares = new HashMap<String, Set<Integer>>();

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {

                if (!Character.isDigit(board[row][column])) {
                    continue;
                }

                final var number = board[row][column] - '0';

                final var seenRows = rows.getOrDefault(row, new HashSet<>());
                final var seenCols = cols.getOrDefault(column, new HashSet<>());

                final var initRow = Math.floorDiv(row, 3) * 3;
                final var initColumn = Math.floorDiv(column, 3) * 3;
                final var squareKey = initRow + "," + initColumn;
                final var seenSquares = squares.getOrDefault(squareKey, new HashSet<>());

                if (seenRows.contains(number) || seenCols.contains(number) || seenSquares.contains(number)) {
                    return false;
                }

                seenRows.add(number);
                rows.put(row, seenRows);

                seenCols.add(number);
                cols.put(column, seenCols);

                seenSquares.add(number);
                squares.put(squareKey, seenSquares);
            }
        }

        return true;
    }
}
