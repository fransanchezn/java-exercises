package exercises.heaps;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

// 1046. Last Stone Weight
public class LastStoneWeight {
    public int lastStoneWeight(final int[] stones) {
        final var heap = new PriorityQueue<Integer>(Comparator.reverseOrder());

        for (int stone : stones) {  // O(n)
            heap.add(stone);
        }

        while (heap.size() > 1) {  // O(n)
            final var stoneOne = heap.remove(); // O(log n)
            final var stoneTwo = heap.remove();  // O(log n)

            if (!Objects.equals(stoneOne, stoneTwo)) {
                final var newStone = stoneOne - stoneTwo;
                heap.add(newStone); // O(log n)
            }
        }

        final var lastStone = heap.remove(); // O(log n)
        return lastStone == null ? 0 : lastStone;
    }

    public static void main(final String[] args) {
        final var stones = new int[] {2,7,4,1,8,1};

        final var sut = new LastStoneWeight();
        final var result = sut.lastStoneWeight(stones);

        System.out.println(result);
    }
}
