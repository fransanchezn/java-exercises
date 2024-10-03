package org.fransanchez.usecases.gamekata;

class Player {
    private final String name;
    private int place;
    private int balance;
    private boolean inPenaltyBox;

    Player(final String name) {
        this.name = name;
        this.place = 0;
        this.balance = 0;
        this.inPenaltyBox = false;
    }

    public String name() {
        return name;
    }

    public int place() {
        return place;
    }

    public void move(final int roll) {
        place = (place + roll) % 12;
    }

    public void addToBalance() {
        balance += 1;
    }

    public int balance() {
        return balance;
    }

    public void moveToPenaltyBox() {
        inPenaltyBox = true;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public boolean isWinner() {
        return balance == 6;
    }
}
