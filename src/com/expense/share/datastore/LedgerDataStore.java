package com.expense.share.datastore;

import com.expense.share.datastore.api.LedgerStoreDao;
import com.expense.share.entity.Ledger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by surender.s on 07/09/19.
 */
public class LedgerDataStore implements LedgerStoreDao {

    private final List<Ledger> transactionList = new ArrayList<>();

    @Override
    public void storeTransaction(Ledger transaction) {
        transactionList.add(transaction);
    }

    @Override
    public List<Ledger> getTransactionFromUser(String userEmailId) {
        List<Ledger> ledgerList = transactionList.stream().filter(ledger -> ledger.getFromUser().equals(userEmailId)).collect(Collectors.toList());

        return ledgerList;
    }

    @Override
    public List<Ledger> getTransactionToUser(String emailId) {

        List<Ledger> toUser = transactionList.stream().filter(ledger -> ledger.getToUser().equals(emailId)).collect(Collectors.toList());

        return toUser;
    }
}
