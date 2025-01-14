package org.fransanchez.usecases.bank.domain.transaction;

import javax.money.MonetaryAmount;

public class WithdrawTransaction extends Transaction {
    private MonetaryAmount amount;

    public WithdrawTransaction(final String accountId, final String tellerId, final MonetaryAmount amount) {
        super(accountId, tellerId, TransactionType.WITHDRAW);
        this.amount = amount;
    }
}
