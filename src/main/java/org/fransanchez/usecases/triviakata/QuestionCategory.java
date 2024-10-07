package org.fransanchez.usecases.triviakata;

public enum QuestionCategory {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    public final String label;

    QuestionCategory(String label) {
        this.label = label;
    }
}
