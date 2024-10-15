package org.fransanchez.designpattern.behavioral.strategy.payment;

import java.math.BigDecimal;
import org.fransanchez.designpattern.behavioral.strategy.PaymentResponse;

public class ApplePayStrategy implements PaymentStrategy {
    @Override
    public PaymentResponse pay(BigDecimal cost) {
        System.out.println("Apple Pay strategy - paid: " + cost);
        return new PaymentResponse("AP-a1asdv", true);
    }
}
