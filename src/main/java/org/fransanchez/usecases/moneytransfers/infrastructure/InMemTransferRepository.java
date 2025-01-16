package org.fransanchez.usecases.moneytransfers.infrastructure;

import org.fransanchez.usecases.moneytransfers.Transfer;
import org.fransanchez.usecases.moneytransfers.TransferRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemTransferRepository implements TransferRepository {
    public final List<Transfer> transfers;

    public InMemTransferRepository() {
        this.transfers = new ArrayList<>();
    }

    @Override
    public Transfer insert(final Transfer transfer) {
        transfers.add(transfer);
        return transfer;
    }
}
