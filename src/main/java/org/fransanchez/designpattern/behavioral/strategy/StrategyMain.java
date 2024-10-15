package org.fransanchez.designpattern.behavioral.strategy;

import java.math.BigDecimal;

public class StrategyMain {

    public static void main(String[] args) {
        final var service = new PaymentService();
        service.processOrder(PaymentMethod.APPLE_PAY, new BigDecimal("2.34"));
        service.processOrder(PaymentMethod.GOOGLE_PAY, new BigDecimal("0.11"));
        service.processOrder(PaymentMethod.CARD, new BigDecimal("1.43"));
    }
}
