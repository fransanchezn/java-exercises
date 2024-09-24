package org.fransanchez.exercises.graphs.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

// 752. Open the Lock
public class OpenLock {
    public int openLock(final String[] deadends, final String target) {
        if (target.equals("0000")) {
            return 0;
        }

        final var queue = new LinkedList<Combination>();
        final var seen = new HashSet<>(Arrays.asList(deadends));

        if (seen.contains("0000")) {
            return -1;
        }

        queue.add(new Combination("0000", 0));

        while (!queue.isEmpty()) {
            final var current = queue.poll();
            final var nbs = getNeighbours(current.combination);
            for (var nb : nbs) {
                if (nb.equals(target)) {
                    return current.attempt + 1;
                } else if (!seen.contains(nb)) {
                    seen.add(nb);
                    queue.add(new Combination(nb, current.attempt + 1));
                }
            }

        }

        return -1;
    }

    private List<String> getNeighbours(final String combination) {
        final var nbs = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            final int digit = combination.charAt(i) - '0';
            final int digitPlusOne = (digit + 1 + 10) % 10;
            final int digitMinosOne = (digit - 1 + 10) % 10;

            final var comb1 = combination.substring(0, i) + ("" + digitPlusOne) + combination.substring(i + 1);
            final var comb2 = combination.substring(0, i) + ("" + digitMinosOne) + combination.substring(i + 1);

            nbs.add(comb1);
            nbs.add(comb2);
        }

        return nbs;
    }

    private record Combination(String combination, int attempt) {}

    public static void main(String[] args) {
        final var sut = new OpenLock();
        final var result = sut.openLock(new String[] { "8887","8889","8878","8898","8788","8988","7888","9888" }, "8888");

        System.out.println(result);
    }
}
