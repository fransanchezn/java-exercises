package exercises.greedy;

import java.util.Arrays;

// 2126. Destroying Asteroids
public class DestroyingAsteroids {
    public boolean asteroidsDestroyed(final int mass, final int[] asteroids) {
        Arrays.sort(asteroids);

        int planetMass = mass;
        for (int asteroid: asteroids) {
            if (asteroid > planetMass) {
                return false;
            }
            planetMass += asteroid;
        }

        return true;
    }

    public static void main(String[] args) {
        final var sut = new DestroyingAsteroids();
        final var result = sut.asteroidsDestroyed(5, new int[] { 4,9,23,4 });

        System.out.println(result);
    }
}
