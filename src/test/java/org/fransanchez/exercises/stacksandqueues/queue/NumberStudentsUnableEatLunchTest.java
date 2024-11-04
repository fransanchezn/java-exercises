package org.fransanchez.exercises.stacksandqueues.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberStudentsUnableEatLunchTest {
    final NumberStudentsUnableEatLunch sut = new NumberStudentsUnableEatLunch();

    @Test
    public void givenStudentAndSandwiches_whenAllStudentsTryToEat_returnNonEatenStudents() {
        final var result = sut.countStudents(new int[]{ 1,1,1,0,0,1 }, new int[] { 1,0,0,0,1,1 });
        Assertions.assertEquals(3, result);
    }

}