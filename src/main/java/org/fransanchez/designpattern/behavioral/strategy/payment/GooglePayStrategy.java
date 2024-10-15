package org.fransanchez.designpattern.behavioral.strategy.payment;

import java.math.BigDecimal;
import org.fransanchez.designpattern.behavioral.strategy.PaymentResponse;

public class GooglePayStrategy implements PaymentStrategy {
    @Override
    public PaymentResponse pay(BigDecimal cost) {
        System.out.println("Google Pay strategy - paid: " + cost);
        return new PaymentResponse("GP-123", true);
    }
}
