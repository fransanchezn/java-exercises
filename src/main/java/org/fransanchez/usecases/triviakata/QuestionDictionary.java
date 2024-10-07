package org.fransanchez.usecases.triviakata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionDictionary {
    private final Map<QuestionCategory, List<String>> dictionary = new HashMap<>();

    public QuestionDictionary() {
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            Arrays.stream(QuestionCategory.values()).forEach(cat -> {
                final var questions = dictionary.getOrDefault(cat, new ArrayList<>());
                questions.add(cat.label + " Question " + finalI);
                dictionary.put(cat, questions);
            });
        }
    }

    public String extractQuestion(final QuestionCategory category) {
        return dictionary.get(category).removeFirst();
    }
}
