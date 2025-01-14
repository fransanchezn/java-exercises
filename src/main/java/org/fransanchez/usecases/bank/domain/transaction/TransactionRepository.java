package org.fransanchez.usecases.bank.domain.transaction;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> get(final String accountId);
    Transaction save(final Transaction transaction);
}
