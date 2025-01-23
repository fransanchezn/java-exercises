package org.fransanchez.concurrency.multithreadingcourse.fLocking;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMain {

    public static void main(String[] args) {
        final var pricesContainer = new PricesContainer();
        final var dashboard = new PriceDashboard(pricesContainer);
        final var updated = new PriceUpdater(pricesContainer);

        updated.start();
        dashboard.start();
    }

    public static class PricesContainer {
        private final Lock lock;

        private double bitcoinPrice;
        private double etherPrice;
        private double litecoinPrice;
        private double bitcoinCashPrice;
        private double ripplePrice;

        public PricesContainer() {
            this.lock = new ReentrantLock();
        }

        public double getBitcoinPrice() {
            return bitcoinPrice;
        }

        public double getEtherPrice() {
            return etherPrice;
        }

        public double getLitecoinPrice() {
            return litecoinPrice;
        }

        public double getBitcoinCashPrice() {
            return bitcoinCashPrice;
        }

        public double getRipplePrice() {
            return ripplePrice;
        }

        public void setBitcoinPrice(double bitcoinPrice) {
            this.bitcoinPrice = bitcoinPrice;
        }

        public void setEtherPrice(double etherPrice) {
            this.etherPrice = etherPrice;
        }

        public void setLitecoinPrice(double litecoinPrice) {
            this.litecoinPrice = litecoinPrice;
        }

        public void setBitcoinCashPrice(double bitcoinCashPrice) {
            this.bitcoinCashPrice = bitcoinCashPrice;
        }

        public void setRipplePrice(double ripplePrice) {
            this.ripplePrice = ripplePrice;
        }
    }

    public static class PriceDashboard extends Thread {
        private final PricesContainer pricesContainer;

        public PriceDashboard(PricesContainer pricesContainer) {
            this.pricesContainer = pricesContainer;
        }

        @Override
        public void run() {
            while (true) {
                if (pricesContainer.lock.tryLock()) {
                    try {
                        System.out.printf(
                                """
                                ########### PRICES ########### 
                                Bitcoin: %s
                                Ether: %s
                                Litecoin: %s
                                Bitcoin cash: %s
                                Ripple: %s
                                #############################
                                """, pricesContainer.getBitcoinPrice(),
                                pricesContainer.getEtherPrice(),
                                pricesContainer.getLitecoinPrice(),
                                pricesContainer.getBitcoinCashPrice(),
                                pricesContainer.getRipplePrice()
                        );
                    } finally {
                        pricesContainer.lock.unlock();
                    }
                }
            }
        }
    }

    public static class PriceUpdater extends Thread {
        private final PricesContainer pricesContainer;
        private final Random random;

        public PriceUpdater(final PricesContainer pricesContainer) {
            this.pricesContainer = pricesContainer;
            this.random = new Random();
        }

        @Override
        public void run() {
            while (true) {
                pricesContainer.lock.lock();
                try {
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    pricesContainer.setBitcoinPrice(random.nextInt(20000));
                    pricesContainer.setEtherPrice(random.nextInt(2000));
                    pricesContainer.setLitecoinPrice(random.nextInt(500));
                    pricesContainer.setBitcoinCashPrice(random.nextInt(5000));
                    pricesContainer.setRipplePrice(random.nextDouble());
                } finally {
                    pricesContainer.lock.unlock();
                }
            }
        }
    }
}
