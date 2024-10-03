package org.fransanchez.usecases.gamekata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game implements IGame {
    private final List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    private final QuestionDictionary questionDictionary;
    private final Board board;

    private boolean isGettingOutOfPenaltyBox;

    public Game() {
        this.questionDictionary = new QuestionDictionary();
        this.board = new Board(12, Arrays.stream(QuestionCategory.values()).toList());
    }

    @Override
    public void add(String playerName) {
        players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    @Override
    public void roll(final int roll) {
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
                System.out.println(players.get(currentPlayerIndex).name() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            currentPlayer().move(roll);

            System.out.println(currentPlayer().name() + "'s new location is " + currentPlayer().place());
            System.out.println("The category is " + board.currentCategory(currentPlayer().place()).label);
            askQuestions();
        }
    }

    @Override
    public boolean wasCorrectlyAnswered() {
        if (currentPlayer().isInPenaltyBox()) {
            if (!isGettingOutOfPenaltyBox) {
                nextPlayer();
                return true;
            }

            currentPlayer().moveOutOfPenaltyBox();
        }

        boolean winner = correctAnswerCheckNotWinner();
        nextPlayer();

        return winner;
    }

    @Override
    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayerIndex).name() + " was sent to the penalty box");
        currentPlayer().moveToPenaltyBox();
        nextPlayer();
        return true;
    }

    private boolean correctAnswerCheckNotWinner() {
        System.out.println("Answer was correct!!!!");
        currentPlayer().addToBalance();
        System.out.println(players.get(currentPlayerIndex).name() + " now has " + currentPlayer().balance() + " Gold Coins.");
        return !currentPlayer().isWinner();
    }

    private void askQuestions() {
        final var question = questionDictionary.extractQuestion(board.currentCategory(currentPlayer().place()));
        System.out.println(question);
    }

    private Player currentPlayer() {
        return players.get(currentPlayerIndex);
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}
