package exercises.backtracking;

import java.util.ArrayList;
import java.util.List;

public class WordSearch {
    private final int[][] directions = new int[][]{
            {0, 1}, // Up
            {1, 0}, // Right
            {0, -1}, // Down
            {-1, 0} // Left
    };

    public boolean exist(final char[][] board, final String word) {
        // Where to start
        final var firstLetter = word.charAt(0);
        var startingPoint = new int[2];
        var found = false;
        for (int i = 0; i < board.length && !found; i++) {
            for (int j = 0; j < board[i].length && !found; j++) {
                if (board[i][j] == firstLetter) {
                    startingPoint[0] = i;
                    startingPoint[1] = j;
                    found = true;
                }
            }
        }

        if (!found) {
            return false;
        }

        if (word.length() == 1) {
            return true;
        }

        final var ans = new ArrayList<String>();
        backtrack(new StringBuilder().append(firstLetter), ans, startingPoint[0], startingPoint[1], board, word);
        return !ans.isEmpty();
    }

    private void backtrack(final StringBuilder curr, final List<String> ans, final int rows, final int cols, final char[][] board, final String word) {
        if (curr.toString().equals(word)) {
            ans.add(curr.toString());
            return;
        }

        for (int[] direction: directions) {
            final var nextRow = rows + direction[0];
            final var nextCol = cols + direction[1];
            if (isValid(nextRow, nextCol, curr, board, word)) {
                curr.append(board[nextRow][nextCol]);
                backtrack(curr, ans, nextRow, nextCol, board, word);
                curr.deleteCharAt(curr.length() - 1);
            }
        }
    }

    private boolean isValid(final int nextRow, final int nextCol, final StringBuilder curr, final char[][] board, final String word) {
        return nextRow >= 0 && nextRow < board.length && nextCol >= 0 && nextCol < board[nextRow].length && board[nextRow][nextCol] == word.charAt(curr.length());
    }
}
