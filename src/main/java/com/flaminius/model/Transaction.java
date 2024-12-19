package com.flaminius.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    private long id;
    private int bankAccountId;
    private TransactionType type;
    private double amount;

    public Transaction() {
        id = idCounter.getAndIncrement();
    }

    public Transaction(int bankAccountId, TransactionType type, double amount) {
        this.id = idCounter.getAndIncrement();
        this.bankAccountId = bankAccountId;
        this.type = type;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
