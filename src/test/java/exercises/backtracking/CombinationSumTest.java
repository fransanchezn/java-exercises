package exercises.backtracking;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class CombinationSumTest {

    final CombinationSum sut = new CombinationSum();

    @Test
    public void case1() {
        final var result = sut.combinationSum(new int[] { 2,3,6,7 }, 7);
        assertIterableEquals(List.of(List.of(2,2,3), List.of(7)), result);
    }
}