package org.fransanchez.concurrency.multithreadingcourse.creation.usecase;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class RobberyMain {

    public static class Vault {
        private final int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(final int password) {
            return this.password == password;
        }
    }

    public static abstract class Hacker extends Thread {
        final Vault vault;

        public Hacker(final Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
        }

        @Override
        public void start() {
            System.out.println("Starting thread: " + this.getName());
            super.start();
        }
    }

    public static class AscendingHacker extends Hacker {
        public AscendingHacker(final Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                if (this.vault.isCorrectPassword(i)) {
                    System.out.println("Thread " + this.getName() + " hacked the vault with password: " + i);
                    System.exit(0);
                }
            }
        }
    }

    public static class DescendingHacker extends Hacker {
        public DescendingHacker(final Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = Integer.MAX_VALUE; i >= 0; i--) {
                if (this.vault.isCorrectPassword(i)) {
                    System.out.println("Thread " + this.getName() + " hacked the vault with password: " + i);
                    System.exit(0);
                }
            }
        }
    }

    public static class Police extends Thread {
        @Override
        public void run() {
            for (int i = 0 ; i < 10; i++) {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    // Do nothing
                }
                System.out.println("Time passed: " + i + " seconds");
            }
            System.out.println("Police found out");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        final var vault = new Vault(RandomGenerator.getDefault().nextInt(0, Integer.MAX_VALUE));
        final var threads = new ArrayList<Thread>();
        threads.add(new AscendingHacker(vault));
        threads.add(new DescendingHacker(vault));
        threads.add(new Police());

        for (var thread: threads) {
            thread.start();
        }
    }
}
