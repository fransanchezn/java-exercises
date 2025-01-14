package org.fransanchez.usecases.bank.domain.transaction;

public abstract class Transaction {
    protected String accountId;
    protected String tellerId;
    protected TransactionType type;

    public Transaction(final String accountId, final String tellerId, final TransactionType type) {
        this.accountId = accountId;
        this.tellerId = tellerId;
        this.type = type;
    }

    public String accountId() {
        return accountId;
    }
}
