package com.dws.challenge.service;

import com.dws.challenge.model.Account;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransferService {

    private final NotificationService notificationService;
    private final Map<String, Account> accounts;

    public TransferService(NotificationService notificationService) {
        this.notificationService = notificationService;
        this.accounts = new ConcurrentHashMap<>();
    }

    public void transfer(String accountFromId, String accountToId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        synchronized (getLock(accountFromId, accountToId)) {
            Account accountFrom = accounts.get(accountFromId);
            Account accountTo = accounts.get(accountToId);

            if (accountFrom.getBalance() < amount) {
                throw new IllegalArgumentException("Insufficient balance");
            }

            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);

            notificationService.notify(accountFromId, "Transferred " + amount + " to " + accountToId);
            notificationService.notify(accountToId, "Received " + amount + " from " + accountFromId);
        }
    }

    private Object getLock(String accountFromId, String accountToId) {
        return accountFromId.compareTo(accountToId) < 0 ? accountFromId : accountToId;
    }
}
