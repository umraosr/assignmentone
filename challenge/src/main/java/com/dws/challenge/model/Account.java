package com.dws.challenge.model;

public class Account {
    private String accountId;
    private double balance;
    private String accountHolder;

    public Account(String accountId, double balance, String accountHolder) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountHolder = accountHolder;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
}
