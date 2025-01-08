package org.fransanchez.exercises.arrayandstrings;

import org.fransanchez.exercises.hashing.ValidSudoku;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidSudokuTest {

    private final ValidSudoku sut = new ValidSudoku();

    @Test
    void givenASudokuArray_whenCallingMethod_returnIfValid() {
        final var sudoku = new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        final var result = sut.isValidSudoku(sudoku);

        assertThat(result).isTrue();
    }

}