package com.expense.share.service.impl;

import com.expense.share.datastore.LedgerDataStore;
import com.expense.share.datastore.api.LedgerStoreDao;
import com.expense.share.entity.Ledger;
import com.expense.share.service.LedgerService;

import java.util.List;

/**
 * Created by surender.s on 07/09/19.
 */
public class LegerServiceImpl implements LedgerService {
    private LedgerStoreDao ledgerStoreDao = new LedgerDataStore();

    @Override
    public void addLedger(Ledger ledger) {
        ledgerStoreDao.storeTransaction(ledger);
    }

    @Override
    public void addLedger(List<Ledger> ledgerList) {
        for (Ledger ledger : ledgerList) {
            ledgerStoreDao.storeTransaction(ledger);
        }
    }

    @Override
    public List<Ledger> searchFromTransactions(String emailId) {
        return ledgerStoreDao.getTransactionFromUser(emailId);
    }

    @Override
    public List<Ledger> searchToTransaction(String emailId) {
        return ledgerStoreDao.getTransactionToUser(emailId);
    }
}
