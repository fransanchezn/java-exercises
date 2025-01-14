package org.fransanchez.usecases.bank.domain.transaction;

import javax.money.MonetaryAmount;

public class DepositTransaction extends Transaction {
    private MonetaryAmount amount;

    public DepositTransaction(final String accountId, final String tellerId, final MonetaryAmount amount) {
        super(accountId, tellerId, TransactionType.DEPOSIT);
        this.amount = amount;
    }
}
