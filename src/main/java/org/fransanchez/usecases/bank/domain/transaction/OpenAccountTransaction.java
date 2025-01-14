package org.fransanchez.usecases.bank.domain.transaction;

public class OpenAccountTransaction extends Transaction {
    private String customerId;

    public OpenAccountTransaction(final String accountId, final String tellerId, final String customerId) {
        super(accountId, tellerId, TransactionType.OPEN_ACCOUNT);
        this.customerId = customerId;
    }
}
