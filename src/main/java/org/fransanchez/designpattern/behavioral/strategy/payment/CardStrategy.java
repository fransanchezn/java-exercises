package org.fransanchez.designpattern.behavioral.strategy.payment;

import java.math.BigDecimal;
import org.fransanchez.designpattern.behavioral.strategy.PaymentResponse;

public class CardStrategy implements PaymentStrategy {
    @Override
    public PaymentResponse pay(final BigDecimal cost) {
        System.out.println("Card Strategy - paid: " + cost);
        return new PaymentResponse("123sad112", true);
    }
}
