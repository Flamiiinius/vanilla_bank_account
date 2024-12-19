package com.flaminius.service;

import com.flaminius.model.BankAccount;
import com.flaminius.model.Transaction;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private static final ConcurrentHashMap<String, BankAccount> accounts = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer, BankAccount> transactions = new ConcurrentHashMap<>();

    public BankService() {
    }

    public BankAccount createBankAccount() {
            String id = UUID.randomUUID().toString();
            BankAccount account =  new BankAccount(id);
           accounts.putIfAbsent(id,account);
           return account;
    }

    public BankAccount getBankAccountById(int id) {
        return accounts.get(id);
    }

    public synchronized boolean processTransaction(Transaction transaction) {
        BankAccount account = accounts.get(transaction.getBankAccountId());
        if (account == null) return false;

        return switch (transaction.getType()) {
            case DEPOSIT:
                account.deposit(transaction.getAmount());
                yield true;
            case WITHDRAW:
                yield account.withdraw(transaction.getAmount());
            default:
                yield false;
        };
    }
}