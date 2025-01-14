package org.fransanchez.usecases.bank.infrastructure.transaction;

import org.fransanchez.usecases.bank.domain.transaction.Transaction;
import org.fransanchez.usecases.bank.domain.transaction.TransactionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemTransactionRepository implements TransactionRepository {
    private final Map<String, List<Transaction>> transactions;

    public InMemTransactionRepository() {
        this.transactions = new HashMap<>();
    }

    @Override
    public List<Transaction> get(final String accountId) {
        return transactions.get(accountId);
    }

    @Override
    public Transaction save(final Transaction transaction) {
        final var transactionList = transactions.getOrDefault(transaction.accountId(), new ArrayList<>());
        transactionList.add(transaction);
        transactions.put(transaction.accountId(), transactionList);
        return transaction;
    }
}
