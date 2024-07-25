package com.dws.challenge.service;

import com.dws.challenge.model.Account;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransferServiceTest {

    @Mock
    private NotificationService notificationService;

    private TransferService transferService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transferService = new TransferService(notificationService);
        // Initialize accounts...
    }

    @Test
    public void testSuccessfulTransfer() {
        Account accountFrom = new Account("account1", 100.0, "John Doe");
        Account accountTo = new Account("account2", 50.0, "Jane Doe");
        transferService.getAccounts().put(accountFrom.getAccountId(), accountFrom);
        transferService.getAccounts().put(accountTo.getAccountId(), accountTo);

        transferService.transfer("account1", "account2", 50.0);

        verify(notificationService).notify("account1", "Transferred 50.0 to account2");
        verify(notificationService).notify("account2", "Received 50.0 from account1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsufficientBalance() {
        Account accountFrom = new Account("account1", 100.0, "John Doe");
        Account accountTo = new Account("account2", 50.0, "Jane Doe");
        transferService.getAccounts().put(accountFrom.getAccountId(), accountFrom);
        transferService.getAccounts().put(accountTo.getAccountId(), accountTo);

        transferService.transfer("account1", "account2", 200.0);
    }

    // More tests...
}
