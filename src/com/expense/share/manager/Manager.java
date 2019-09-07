package com.expense.share.manager;

import com.expense.share.ShareType;
import com.expense.share.TransactionType;
import com.expense.share.entity.Ledger;
import com.expense.share.pojo.SharePaymentEntity;
import com.expense.share.service.LedgerService;
import com.expense.share.service.UserService;
import com.expense.share.service.impl.LegerServiceImpl;
import com.expense.share.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by surender.s on 07/09/19.
 */
public class Manager {
    private UserService userService = new UserServiceImpl();
    private LedgerService ledgerService = new LegerServiceImpl();


    public void createUser(String name, String emailId) {
        userService.createUser(name, emailId);
    }

    public void recordSharePayment(SharePaymentEntity sharePaymentEntity) throws Exception {
        Map<String, Integer> sharedEntityMap = sharePaymentEntity.getUserIdToShareMap();
        String fromUser = sharePaymentEntity.getPaidBy();
        Double totalAmount = sharePaymentEntity.getTotalAmount();

        Integer totalShare = 0;
        for (Integer share : sharedEntityMap.values()) {
            totalShare += share;
        }

        if (sharePaymentEntity.getShareType().equals(ShareType.PERCENTAGE) && totalShare != 100) {
            throw new Exception("Total Share Percentage Doesn't count to 100");
        }

        //Convert Transaction into List of ledgers
        List<Ledger> ledgerList = new ArrayList<>();

        for (String userId : sharedEntityMap.keySet()) {

            Double amount = (totalAmount * sharedEntityMap.get(userId)) / totalShare;
            TransactionType transactionType = TransactionType.LEND;

            if (userId.equals(fromUser)) {
                transactionType = TransactionType.SELF;
            }
            //LEND TRANSACTION from user have lent to userId
            Ledger firstLedger = new Ledger(fromUser, userId, amount, transactionType);

/*
            //TODO: Check if this solved anything
            if (!transactionType.equals(TransactionType.SELF)) {
                Ledger secondLedger = new Ledger(userId, fromUser, amount, TransactionType.BORROW);
                ledgerList.add(secondLedger);
            }
*/

            ledgerList.add(firstLedger);
        }

        ledgerService.addLedger(ledgerList);
    }

    public Map<String, Double> getPendingBalancesList(String userId) {

        //Transaction in which User lent money to someone
        List<Ledger> lentLedgerList = ledgerService.searchFromTransactions(userId);

        Map<String, Double> pendingMap = new HashMap<>();

        //Storing the value as negative for whom I have paid money
        for (Ledger ledger : lentLedgerList) {
            if (ledger.getTransactionType().equals(TransactionType.LEND)) {
                if (!pendingMap.containsKey(ledger.getToUser())) {
                    pendingMap.put(ledger.getToUser(), -1 * ledger.getAmount());
                } else {
                    pendingMap.put(ledger.getToUser(), pendingMap.get(ledger.getToUser()) - ledger.getAmount());
                }
            }
        }

        List<Ledger> borrowedList = ledgerService.searchToTransaction(userId);

        for (Ledger ledger : borrowedList) {
            if (ledger.getTransactionType().equals(TransactionType.LEND)) {
                if (!pendingMap.containsKey(ledger.getFromUser())) {
                    pendingMap.put(ledger.getFromUser(), ledger.getAmount());
                } else {
                    pendingMap.put(ledger.getFromUser(), pendingMap.get(ledger.getFromUser()) + ledger.getAmount());
                }
            }
        }
        return pendingMap;
    }

    public void recordPayment(String fromUserId, String toUserId, Double amount) {

        Ledger ledger = new Ledger(fromUserId, toUserId, amount, TransactionType.LEND);

        ledgerService.addLedger(ledger);
    }

}
