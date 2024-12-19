package com.flaminius.model;

public class BankAccount {
    String id;
    double balance;
    public BankAccount() {
    }

    public BankAccount(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        this.balance += amount;
    }

    public synchronized boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}