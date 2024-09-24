package org.fransanchez.exercises.hashing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 2225. Find Players With Zero or One Losses
public class FindPlayersZeroOneLosses {
    public List<List<Integer>> findWinners(final int[][] matches) {
        final var playerLosses = new HashMap<Integer, Integer>();

        for (int[] match: matches) {
            // Winner
            playerLosses.putIfAbsent(match[0], 0);

            // Loser
            playerLosses.put(match[1], playerLosses.getOrDefault(match[1], 0) + 1);
        }

        final var winnerList = playerLosses.entrySet().stream().filter(e -> e.getValue() == 0).map(Map.Entry::getKey).sorted().collect(Collectors.toList());
        final var loserList = playerLosses.entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).sorted().collect(Collectors.toList());

        return List.of(winnerList, loserList);
    }

    public static void main(String[] args) {
        final var sut = new FindPlayersZeroOneLosses();
        final var result = sut.findWinners(new int[][] {{1,3},{2,3},{3,6},{5,6},{5,7},{4,5},{4,8},{4,9},{10,4},{10,9}});

        System.out.println(result);
    }
}
