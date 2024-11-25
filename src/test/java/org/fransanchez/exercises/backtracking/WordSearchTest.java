package org.fransanchez.exercises.backtracking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WordSearchTest {

    private final WordSearch sut = new WordSearch();

    @Test
    public void case1() {
        final var result = sut.exist(new char[][] {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}}, "ABCCED");
        assertTrue(result);
    }
}