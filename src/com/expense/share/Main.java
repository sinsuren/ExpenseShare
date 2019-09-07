package com.expense.share;

import com.expense.share.manager.Manager;
import com.expense.share.pojo.SharePaymentEntity;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        Manager manager = new Manager();
        //Users
        manager.createUser("Abhay", "a@gmail.com");
        manager.createUser("Bhavan", "b@gmail.com");
        manager.createUser("Cati", "c@gmail.com");
        manager.createUser("Devan", "d@gmail.com");

        //First Transaction
        Map<String, Integer> firstTransaction = new HashMap<>();
        firstTransaction.put("a@gmail.com", 1);
        firstTransaction.put("b@gmail.com", 1);
        firstTransaction.put("c@gmail.com", 1);
        firstTransaction.put("d@gmail.com", 1);

        SharePaymentEntity sharePaymentEntity = new SharePaymentEntity(firstTransaction, "a@gmail.com", 40.0,
                ShareType.SHARE);

        manager.recordSharePayment(sharePaymentEntity);

        //-----------Record the first transaction----------------

        //Second Transaction
        Map<String, Integer> secondTransaction = new HashMap<>();
        secondTransaction.put("a@gmail.com", 1);
        secondTransaction.put("d@gmail.com", 1);

        SharePaymentEntity secondPaymentEntity = new SharePaymentEntity(secondTransaction, "d@gmail.com", 20.0,
                ShareType.SHARE);

        manager.recordSharePayment(secondPaymentEntity);
        // Second payment recorded

        //Record a settlement payment
        manager.recordPayment("b@gmail.com", "a@gmail.com", 5.0);
        manager.recordPayment("b@gmail.com", "a@gmail.com", 5.0);

        Map<String, Double> pendingTransaction = manager.getPendingBalancesList("a@gmail.com");
        printPendingStatement("a@gmail.com", pendingTransaction);
        System.out.println("-------------------------------------------------");

        //Get the pending transaction for second user
        pendingTransaction = manager.getPendingBalancesList("d@gmail.com");
        printPendingStatement("d@gmail.com", pendingTransaction);


    }


    private static void printPendingStatement(String fromUser, Map<String, Double> pendingMap) {

        for (String key : pendingMap.keySet()) {
            Double amount = pendingMap.get(key);
            if (amount > 0.0 || amount < 0.0) {
                System.out.println("From: " + fromUser + " To user: " + key + " Amount: " + amount);
            }
        }
    }
}
