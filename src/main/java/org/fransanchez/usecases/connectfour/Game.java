package org.fransanchez.usecases.connectfour;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Game {
    final Scanner in;

    private Board board;
    private final List<Player> players;
    private final Map<Player, Integer> score;
    private final int scoreLimit;
    private final int connectN = 4;

    public Game(final Board board, final int scoreLimit, final List<Player> players) {
        this.in = new Scanner(System.in);
        this.board = board;
        this.scoreLimit = scoreLimit;
        this.players = players;
        this.score = new HashMap<>();
    }

    private void resetGame() {
        this.board = new Board(board);
    }

    private void increaseScore(final Player player) {
        score.put(player, score.getOrDefault(player, 0) + 1);
    }

    public void play() {
        while (!score.containsValue(scoreLimit)) {
            final var winner = playRound();

            System.out.println("Player " + winner.name() + " is the winner!");
            increaseScore(winner);

            resetGame();
        }

        System.out.println("Total score: " + score);
    }

    private Player playRound() {
        var gameTurn = 0;
        Board.ChipPlacement chipPlacement;
        Player player;

        do {
            final var playerTurn = gameTurn % players.size();
            player = players.get(playerTurn);

            if (board.isFull()) {
                System.out.println("Grid is completed, no winner. Resetting game");
                resetGame();
            }

            Optional<Board.ChipPlacement> chipPlacementOpt;
            var column = -1;
            do {
                System.out.println("Player " + player.name() + " enter column:");
                column = in.nextInt();

                chipPlacementOpt = board.placeChip(column, new Chip(player.color()));
            } while(chipPlacementOpt.isEmpty());

            chipPlacement = chipPlacementOpt.get();
            gameTurn++;
        } while (!board.checkWinningPlay(chipPlacement, connectN));

        return player;
    }

    public static void main(final String[] args) {
        final var in = new Scanner(System.in);

        System.out.println("Enter board size:");
        final var boardSize = in.nextInt();

        System.out.println("Enter score limit:");
        final var scoreLimit = in.nextInt();

        System.out.println("Enter player one name:");
        final var playerOneName = in.next();

        System.out.println("Enter player two name:");
        final var playerTwoName = in.next();

        final var board = new Board(boardSize);
        final var playerOne = new Player(playerOneName, 0);
        final var playerTwo = new Player(playerTwoName, 1);

        final var game = new Game(board, scoreLimit, List.of(playerOne, playerTwo));
        game.play();
    }
}
