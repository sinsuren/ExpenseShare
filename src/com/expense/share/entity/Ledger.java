package com.expense.share.entity;

import com.expense.share.TransactionType;

/**
 * Created by surender.s on 07/09/19.
 */
public class Ledger {

    private String fromUser;
    private String toUser;
    private Double amount;
    private TransactionType transactionType;

    public Ledger(String fromUser, String toUser, Double amount, TransactionType transactionType) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public String getFromUser() {
        return fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}
