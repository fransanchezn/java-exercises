package org.fransanchez.designpattern.behavioral.strategy.payment;

import java.math.BigDecimal;
import org.fransanchez.designpattern.behavioral.strategy.PaymentResponse;

public interface PaymentStrategy {
    PaymentResponse pay(final BigDecimal cost);
}
