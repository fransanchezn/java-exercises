package org.fransanchez.designpattern.behavioral.strategy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.fransanchez.designpattern.behavioral.strategy.payment.ApplePayStrategy;
import org.fransanchez.designpattern.behavioral.strategy.payment.CardStrategy;
import org.fransanchez.designpattern.behavioral.strategy.payment.GooglePayStrategy;
import org.fransanchez.designpattern.behavioral.strategy.payment.PaymentStrategy;

public class PaymentService {
    private static final Map<PaymentMethod, PaymentStrategy> strategies;

    static {
        strategies = new HashMap<>();
        strategies.put(PaymentMethod.CARD, new CardStrategy());
        strategies.put(PaymentMethod.APPLE_PAY, new ApplePayStrategy());
        strategies.put(PaymentMethod.GOOGLE_PAY, new GooglePayStrategy());
    }

    public boolean processOrder(final PaymentMethod paymentMethod, final BigDecimal cost) {
        return strategies.get(paymentMethod).pay(cost).success();
    }
}
