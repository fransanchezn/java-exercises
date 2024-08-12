package exercises.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ValidateSudoku {
    public static void main(String[] args) {
        //[["8","3","."  ,".","7",".",  ".",".","."],
        // ["6",".","."  ,"1","9","5"  ,".",".","."],
        // [".","9","8"  ,".",".","."  ,".","6","."],

        // ["8",".","."  ,".","6","."  ,".",".","3"],
        // ["4",".","."  ,"8",".","3"  ,".",".","1"],
        // ["7",".","."  ,".","2","."  ,".",".","6"],

        // [".","6","."  ,".",".","."  ,"2","8","."],
        // [".",".","."  ,"4","1","9"  ,".",".","5"],
        // [".",".","."  ,".","8","."  ,".","7","9"]]
        char[][] sudoku = new char[9][9];

        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                sudoku[i][j] = '.';
            }
        }

        sudoku[0][0] = '8';sudoku[0][1] = '3';sudoku[0][4] = '7';
        sudoku[1][0] = '6';sudoku[1][3] = '1';sudoku[1][4] = '9';sudoku[1][5] = '5';
        sudoku[2][1] = '9';sudoku[2][2] = '8';sudoku[2][7] = '6';

        sudoku[3][0] = '8';sudoku[3][4] = '6';sudoku[3][8] = '3';
        sudoku[4][0] = '4';sudoku[4][3] = '8';sudoku[4][5] = '3';sudoku[4][8] = '1';
        sudoku[5][0] = '7';sudoku[5][4] = '2';sudoku[5][8] = '6';

        sudoku[6][1] = '6';sudoku[6][6] = '2';sudoku[6][7] = '8';
        sudoku[7][3] = '4';sudoku[7][4] = '1';sudoku[7][5] = '9';
        sudoku[8][4] = '8';sudoku[8][7] = '7';sudoku[8][8] = '9';

        System.out.println(isValidSudoku(sudoku));

    }


    public static boolean isValidSudoku(char[][] board) {
        HashMap<Integer, Set<Integer>> columns = new HashMap<>();
        HashMap<String, Set<Integer>> squares = new HashMap<>();

        for (int i = 0; i < board.length; i++) {
            final var row = new HashSet<>();
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }

                final var column = columns.getOrDefault(j, new HashSet<>());
                final var squareId = (i / 3) + "," + (j / 3);
                final var square = squares.getOrDefault(squareId, new HashSet<>());

                final var rowValid = row.add(Character.getNumericValue(board[i][j]));
                final var columnValid = column.add(Character.getNumericValue(board[i][j]));
                final var squareValid = square.add(Character.getNumericValue(board[i][j]));

                if (!rowValid || !columnValid || !squareValid) {
                    return false;
                }

                columns.put(j, column);
                squares.put(squareId, square);
            }
        }

        return true;
    }
}
