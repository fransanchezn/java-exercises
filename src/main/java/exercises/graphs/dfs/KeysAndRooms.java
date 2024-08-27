package exercises.graphs.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 841. Keys and Rooms
public class KeysAndRooms {
    final Set<Integer> seen = new HashSet<>();

    public boolean canVisitAllRooms(final List<List<Integer>> rooms) {
        seen.add(0);
        dfs(0, rooms);
        return seen.size() == rooms.size();
    }

    private void dfs(final int room, final List<List<Integer>> rooms) {
        final var keys = rooms.get(room);
        for(var key : keys) {
            if (!seen.contains(key)) {
                seen.add(key);
                dfs(key, rooms);
            }
        }
    }

    public static void main(String[] args) {
        final var rooms = new ArrayList<List<Integer>>() {{
            add(List.of(1));
            add(List.of(2));
            add(List.of(1));
            add(List.of());
        }};

        final var sut = new KeysAndRooms();
        final var result = sut.canVisitAllRooms(rooms);

        System.out.println(result);
    }
}
