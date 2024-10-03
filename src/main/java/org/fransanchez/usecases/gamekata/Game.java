package org.fransanchez.usecases.gamekata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game implements IGame {
    private final List<Player> players = new ArrayList<>();

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;
    private final QuestionDictionary questionDictionary;
    private final Board board;

    public Game() {
        this.questionDictionary = new QuestionDictionary();
        this.board = new Board(12, Arrays.stream(QuestionCategory.values()).toList());
    }

    public void add(String playerName) {
        players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public void roll(int roll) {
        System.out.println(currentPlayer().name() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer().isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer().name() + " is getting out of the penalty box");
                currentPlayer().move(roll);

                System.out.println(currentPlayer().name() + "'s new location is " + currentPlayer().place());
                System.out.println("The category is " + board.currentCategory(currentPlayer().place()).label);
                askQuestions();
            } else {
                System.out.println(players.get(currentPlayer).name() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            currentPlayer().move(roll);

            System.out.println(currentPlayer().name() + "'s new location is " + currentPlayer().place());
            System.out.println("The category is " + board.currentCategory(currentPlayer().place()).label);
            askQuestions();
        }
    }

    private void askQuestions() {
        final var question = questionDictionary.extractQuestion(board.currentCategory(currentPlayer().place()));
        System.out.println(question);
    }

    private Player currentPlayer() {
        return players.get(currentPlayer);
    }

    public boolean wasCorrectlyAnswered() {
        if (currentPlayer().isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                currentPlayer().addToBalance();
                System.out.println(players.get(currentPlayer).name()
                        + " now has "
                        + currentPlayer().balance()
                        + " Gold Coins.");

                boolean winner = currentPlayer().isWinner();
                nextPlayer();

                return winner;
            } else {
                nextPlayer();
                return true;
            }
        } else {
            System.out.println("Answer was correct!!!!");
            currentPlayer().addToBalance();
            System.out.println(players.get(currentPlayer).name()
                    + " now has "
                    + currentPlayer().balance()
                    + " Gold Coins.");

            boolean winner = currentPlayer().isWinner();
            nextPlayer();

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer).name() + " was sent to the penalty box");
        currentPlayer().moveToPenaltyBox();

        nextPlayer();
        return true;
    }

    private void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }
}
