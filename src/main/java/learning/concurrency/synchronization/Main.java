package learning.concurrency.synchronization;

public class Main {
    public static void main(String[] args) {
        final var stack = new Stack(5);

        System.out.println("Main start!");

        new Thread(() -> {
            int counter = 0;
            while(++counter < 10) {
                System.out.println("Pushed[" + counter + "]: " + stack.push(100));
            }
        }).start();

        new Thread(() -> {
            int counter = 0;
            while(++counter < 10) {
                System.out.println("Popped[" + counter + "]: " + stack.pop());
            }
        }).start();

        System.out.println("Main end!");
    }
}
