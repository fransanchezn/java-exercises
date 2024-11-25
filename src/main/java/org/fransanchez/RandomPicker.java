package org.fransanchez;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.random.RandomGenerator;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

public class RandomPicker {

    final RandomGenerator randomGenerator = new Random();

    public String getRandomExercise(final String packageName) {
        final var classes = findAllClassesUsingClassLoader(packageName);
        System.out.println(classes);

        final var index = randomGenerator.nextInt(classes.size() - 1);
        return classes.get(index);
    }

    private List<String> findAllClassesUsingClassLoader(final String packageName) {
        final var reflections = new Reflections(packageName, Scanners.SubTypes.filterResultsBy(c -> true));

        return reflections.getSubTypesOf(Object.class)
                .stream()
                .map(Class::getSimpleName)
                .filter(Predicate.not(String::isBlank))
                .toList();
    }

    public static void main(final String[] args) {
        final var randomPicker = new RandomPicker();
        final var randomExercise = randomPicker.getRandomExercise("org.fransanchez.exercises");

        System.out.println("Random exercise: " + randomExercise);
    }
}
