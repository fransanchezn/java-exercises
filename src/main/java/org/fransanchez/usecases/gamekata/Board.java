package org.fransanchez.usecases.gamekata;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<BoardSlots> slots = new ArrayList<>();

    public Board(final int size, final List<QuestionCategory> categories) {
        if (size < 1) {
            throw new IllegalArgumentException("Board cannot be smaller than 1");
        }

        for (int i = 0; i < size; i += categories.size()) {
            final int finalI = i;
            categories.forEach(cat -> {
                if (slots.size() < size) {
                    slots.add(new BoardSlots(cat, finalI));
                }
            });
        }
    }

    public QuestionCategory currentCategory(final int index) {
        return slots.get(index).category();
    }
}
