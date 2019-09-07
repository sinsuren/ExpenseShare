package com.expense.share.service;

import com.expense.share.entity.Ledger;

import java.util.List;

/**
 * Created by surender.s on 07/09/19.
 */
public interface LedgerService {
    void addLedger(Ledger ledger);

    void addLedger(List<Ledger> ledgerList);

    List<Ledger> searchFromTransactions(String emailId);

    List<Ledger> searchToTransaction(String emailId);
}
