package com.expense.share.datastore.api;

import com.expense.share.entity.Ledger;

import java.util.List;

/**
 * Created by surender.s on 07/09/19.
 */
public interface LedgerStoreDao {

    void storeTransaction(Ledger transaction);

    List<Ledger> getTransactionFromUser(String userEmailId);

    List<Ledger> getTransactionToUser(String emailId);
}
