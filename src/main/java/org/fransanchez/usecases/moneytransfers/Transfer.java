package org.fransanchez.usecases.moneytransfers;

import javax.money.MonetaryAmount;
import java.util.UUID;

public record Transfer(UUID transferId, Account fromAccount, Account toAccount, MonetaryAmount amount) {
    public Transfer(final Account fromAccount, final Account toAccount, final MonetaryAmount amount) {
        this(UUID.randomUUID(), fromAccount, toAccount, amount);
    }
}
