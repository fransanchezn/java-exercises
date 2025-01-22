package org.fransanchez.concurrency.multithreadingcourse.creation.test;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {
    final List<Runnable> tasks;

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(final List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        for (Runnable task: tasks) {
            new Thread(task).start();
        }
    }

    public static void main(String[] args) {
        final var runnables = new ArrayList<Runnable>();
        for (int i = 0 ; i < 100; i++) {
            final int finalI = i;
            runnables.add(() -> {
                System.out.println("Runnable" + finalI);
            });
        }

        final var multiExecutor = new MultiExecutor(runnables);
        multiExecutor.executeAll();
    }
}
