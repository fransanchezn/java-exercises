package org.fransanchez.usecases.triviakata;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import org.fransanchez.usecases.triviakata.original.GameOriginal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    @Test
    public void caracterizationTest() {
        // runs 10.000 "random" games to see the output of old and new code matches
        for (int seed = 1; seed < 10_000; seed++) {
            testSeed(seed, true);
        }
    }

    private void testSeed(int seed, boolean printExpected) {
        String expectedOutput = extractOutput(new Random(seed), new GameOriginal());
        if (printExpected) {
            System.out.println(expectedOutput);
        }
        String actualOutput = extractOutput(new Random(seed), new Game());

        System.out.println("--------");
        System.out.println(actualOutput);

        assertEquals(expectedOutput, actualOutput, "Change detected for seed " + seed +
                ". To breakpoint through it, run this seed alone using the (ignored) test below");
    }

    //    @Test
    //    @Ignore("enable back and set a particular seed to see the output")
    public void oneSeed() {
        testSeed(1, true);
    }

    private String extractOutput(Random rand, IGame aGame) {
        PrintStream old = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream inmemory = new PrintStream(baos)) {
            // WARNING: System.out.println() doesn't work in this try {} as the sysout is captured and recorded in memory.
            System.setOut(inmemory);

            aGame.add("Chet");
            aGame.add("Pat");
            aGame.add("Sue");

            boolean notAWinner = false;
            do {
                aGame.roll(rand.nextInt(5) + 1);

                if (rand.nextInt(9) == 7) {
                    notAWinner = aGame.wrongAnswer();
                } else {
                    notAWinner = aGame.wasCorrectlyAnswered();
                }

            } while (notAWinner);
        } finally {
            System.setOut(old);
        }

        return new String(baos.toByteArray());
    }
}