package org.fransanchez.exercises.stacksandqueues.queue;

import java.util.HashMap;

// 1700. Number of Students Unable to Eat Lunch
public class NumberStudentsUnableEatLunch {
    private final HashMap<Integer, Integer> typeToStudents = new HashMap<>();

    public int countStudents(final int[] students, final int[] sandwiches) {
        for (int student : students) {
            typeToStudents.put(student, typeToStudents.getOrDefault(student, 0) + 1);
        }

        var counter = students.length;
        for (int sandwich: sandwiches) {
            var amount = typeToStudents.getOrDefault(sandwich, 0);
            if (amount == 0) {
                return counter;
            }

            typeToStudents.put(sandwich, --amount);
            counter--;
        }

        return counter;
    }
}
